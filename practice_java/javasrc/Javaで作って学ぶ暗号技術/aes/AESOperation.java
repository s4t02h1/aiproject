package aes;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import junit.framework.Assert;

import org.junit.Test;

public class AESOperation {
    public byte[] encryptECB(byte[] data, byte[] key) {
        if (data == null || data.length % 16 != 0 || key == null
                || (key.length != 16 && key.length != 24 && key.length != 32))
            throw new IllegalArgumentException();

        byte[] buf = new byte[data.length];
        AESAlgorithm aes = new AESAlgorithm();
        aes.setKey(key);
        for (int i = 0; i < data.length; i += 16) {
            aes.setBlock(data, i);
            aes.encryptBlock();
            aes.getBlock(buf, i);
        }
        return buf;
    }

    public byte[] decryptECB(byte[] data, byte[] key) {
        if (data == null || data.length % 16 != 0 || key == null
                || (key.length != 16 && key.length != 24 && key.length != 32))
            throw new IllegalArgumentException();

        byte[] buf = new byte[data.length];
        AESAlgorithm aes = new AESAlgorithm();
        aes.setKey(key);
        for (int i = 0; i < data.length; i += 16) {
            aes.setBlock(data, i);
            aes.decryptBlock();
            aes.getBlock(buf, i);
        }
        return buf;
    }

    public byte[] encryptCBC(byte[] data, byte[] key, byte[] iv) {
        if (data == null || data.length % 16 != 0 || key == null
                || (key.length != 16 && key.length != 24 && key.length != 32)
                || iv == null || iv.length != 16)
            throw new IllegalArgumentException();

        byte[] buf = new byte[data.length];
        byte[] tmp = new byte[16];
        byte[] v = iv;
        int voffset = 0;
        AESAlgorithm aes = new AESAlgorithm();
        aes.setKey(key);
        for (int i = 0; i < data.length; i += 16) {
            for (int j = 0; j < 16; j++)
                tmp[j] = (byte) (data[i + j] ^ v[voffset + j]);
            aes.setBlock(tmp, 0);
            aes.encryptBlock();
            aes.getBlock(buf, i);
            v = buf;
            voffset = i;
        }
        return buf;
    }

    public byte[] decryptCBC(byte[] data, byte[] key, byte[] iv) {
        if (data == null || data.length % 16 != 0 || key == null
                || (key.length != 16 && key.length != 24 && key.length != 32)
                || iv == null || iv.length != 16)
            throw new IllegalArgumentException();

        byte[] buf = new byte[data.length];
        byte[] v = iv;
        int voffset = 0;
        AESAlgorithm aes = new AESAlgorithm();
        aes.setKey(key);
        for (int i = 0; i < data.length; i += 16) {
            aes.setBlock(data, i);
            aes.decryptBlock();
            aes.getBlock(buf, i);
            for (int j = 0; j < 16; j++)
                buf[i + j] ^= v[voffset + j];
            v = data;
            voffset = i;
        }

        return buf;
    }

    public byte[] encryptDecryptCTR(byte[] data, byte[] key, BigInteger t) {
        if (data == null || data.length % 16 != 0 || key == null
                || (key.length != 16 && key.length != 24 && key.length != 32)
                || t == null || t.signum() == -1)
            throw new IllegalArgumentException();

        byte[] buf = new byte[data.length];
        byte[] tmp = new byte[16];
        AESAlgorithm aes = new AESAlgorithm();
        aes.setKey(key);
        for (int i = 0; i < data.length; i += 16) {
            byte[] bytesoft = t.toByteArray();
            for (int j = 0; j < 16; j++)
                tmp[16 - j - 1] = j < bytesoft.length ? bytesoft[bytesoft.length - j - 1]
                        : (byte) 0;
            aes.setBlock(tmp, 0);
            aes.encryptBlock();
            aes.getBlock(buf, i);
            for (int j = 0; j < 16; j++)
                buf[i + j] ^= data[i + j];
            t = t.add(BigInteger.ONE);
        }
        return buf;
    }

    @Test
    public void ecbTest() {
        byte[] data = new byte[256];
        byte[] key = new byte[16];
        AESOperation aes = new AESOperation();

        for (int i = 0; i < 256; i++) {
            Random r = new Random(System.currentTimeMillis());
            r.nextBytes(key);
            r.nextBytes(data);

            byte[] enc1 = aes.encryptECB(data, key);
            Assert.assertEquals(data.length, enc1.length);

            byte[] dec1 = aes.decryptECB(enc1, key);
            Assert.assertEquals(data.length, dec1.length);

            Assert.assertEquals(data.length, enc1.length);
            Assert.assertEquals(enc1.length, dec1.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(data[j], dec1[j]);
            
            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher;
            byte[] enc2 = null;
            byte[] dec2 = null;
            try {
                cipher = Cipher.getInstance("AES/ECB/NOPADDING", "SunJCE");
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                enc2 = cipher.doFinal(data);
                
                cipher.init(Cipher.DECRYPT_MODE, sks);
                dec2 = cipher.doFinal(enc2);
            } catch (InvalidKeyException e) {
                Assert.fail();
            } catch (NoSuchAlgorithmException e) {
                Assert.fail();
            } catch (NoSuchPaddingException e) {
                Assert.fail();
            } catch (IllegalBlockSizeException e) {
                Assert.fail();
            } catch (BadPaddingException e) {
                Assert.fail();
            } catch (NoSuchProviderException e) {
                Assert.fail();
                e.printStackTrace();
            }
            
            Assert.assertEquals(enc1.length, 256);
            Assert.assertEquals(enc1.length, enc2.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(enc1[j], enc2[j]);

            Assert.assertEquals(dec1.length, 256);
            Assert.assertEquals(dec1.length, dec2.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(dec1[j], dec2[j]);
        }
    }

    @Test
    public void cbcTest() {
        byte[] data = new byte[256];
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        AESOperation aes = new AESOperation();

        for (int i = 0; i < 256; i++) {
            Random r = new Random(System.currentTimeMillis());
            r.nextBytes(key);
            r.nextBytes(data);
            r.nextBytes(iv);

            byte[] enc1 = aes.encryptCBC(data, key, iv);
            Assert.assertEquals(data.length, enc1.length);

            byte[] dec1 = aes.decryptCBC(enc1, key, iv);
            Assert.assertEquals(data.length, dec1.length);

            Assert.assertEquals(data.length, enc1.length);
            Assert.assertEquals(enc1.length, dec1.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(data[j], dec1[j]);

            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher;
            byte[] enc2 = null;
            byte[] dec2 = null;
            try {
                IvParameterSpec ivps = new IvParameterSpec(iv);

                cipher = Cipher.getInstance("AES/CBC/NOPADDING", "SunJCE");
                cipher.init(Cipher.ENCRYPT_MODE, sks, ivps);
                enc2 = cipher.doFinal(data);
                
                cipher.init(Cipher.DECRYPT_MODE, sks, ivps);
                dec2 = cipher.doFinal(enc2);
            } catch (InvalidKeyException e) {
                Assert.fail();
            } catch (NoSuchAlgorithmException e) {
                Assert.fail();
            } catch (NoSuchPaddingException e) {
                Assert.fail();
            } catch (IllegalBlockSizeException e) {
                Assert.fail();
            } catch (BadPaddingException e) {
                Assert.fail();
            } catch (NoSuchProviderException e) {
                Assert.fail();
            } catch (InvalidAlgorithmParameterException e) {
                Assert.fail();
            }
            
            Assert.assertEquals(enc1.length, 256);
            Assert.assertEquals(enc1.length, enc2.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(enc1[j], enc2[j]);

            Assert.assertEquals(dec1.length, 256);
            Assert.assertEquals(dec1.length, dec2.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(dec1[j], dec2[j]);            
        }
    }

    @Test
    public void ctrTest() {
        byte[] data = new byte[256];
        byte[] key = new byte[16];
        BigInteger t;
        AESOperation aes = new AESOperation();

        for (int i = 0; i < 256; i++) {
            Random r = new Random(System.currentTimeMillis());
            r.nextBytes(key);
            r.nextBytes(data);
            t = new BigInteger(128, r);

            byte[] enc1 = aes.encryptDecryptCTR(data, key, t);
            Assert.assertEquals(data.length, enc1.length);

            byte[] dec1 = aes.encryptDecryptCTR(enc1, key, t);
            Assert.assertEquals(data.length, dec1.length);

            Assert.assertEquals(data.length, enc1.length);
            Assert.assertEquals(enc1.length, dec1.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(data[j], dec1[j]);

            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher;
            byte[] enc2 = null;
            byte[] dec2 = null;
            
            byte[] vec = new byte[16];
            byte[] bytesoft = t.toByteArray();
            for(int j = 0; j < 16; j++) {
                vec[16 - j - 1] = j < bytesoft.length ? bytesoft[bytesoft.length - j - 1] : (byte)0;
            }
                
            try {
                IvParameterSpec ivps = new IvParameterSpec(vec);

                cipher = Cipher.getInstance("AES/CTR/NOPADDING", "SunJCE");
                cipher.init(Cipher.ENCRYPT_MODE, sks, ivps);
                enc2 = cipher.doFinal(data);
                
                cipher.init(Cipher.DECRYPT_MODE, sks, ivps);
                dec2 = cipher.doFinal(enc2);
            } catch (InvalidKeyException e) {
                Assert.fail();
            } catch (NoSuchAlgorithmException e) {
                Assert.fail();
            } catch (NoSuchPaddingException e) {
                Assert.fail();
            } catch (IllegalBlockSizeException e) {
                Assert.fail();
            } catch (BadPaddingException e) {
                Assert.fail();
            } catch (NoSuchProviderException e) {
                Assert.fail();
            } catch (InvalidAlgorithmParameterException e) {
                Assert.fail();
            }
            
            Assert.assertEquals(enc1.length, 256);
            Assert.assertEquals(enc1.length, enc2.length);
            
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(enc1[j], enc2[j]);

            Assert.assertEquals(dec1.length, 256);
            Assert.assertEquals(dec1.length, dec2.length);
            for (int j = 0; j < 256; j++)
                Assert.assertEquals(dec1[j], dec2[j]);            
        }
    }
}