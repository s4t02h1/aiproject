package miniSSL;

import java.net.*;
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.KeyStore;

public class miniSSLc {
    private static String msg_history = new String();
    private static byte [] master_secret = new byte [48];
    private static byte [] premaster_secret = new byte [48];
    private static byte [] client_random = new byte [32];
    private static byte [] server_random = new byte [32];
    private static byte [] key_block = new byte [16*10];

    private static String byte2string(byte [] from){
        int i, tmp;
        String to = new String();
        for (i=0; i<from.length; i++){
             tmp = (int) from[i] & 0xFF;
             if (tmp < 16)
                 to = to + "0" + Integer.toHexString(tmp);
             else
                 to = to + Integer.toHexString(tmp);
        }
        return to;
    }
    private static byte [] string2byte(String from){
        int i;
        byte [] to = new byte [from.length()/2];
        for (i=0; i<from.length()/2; i++){
            to[i] = (byte) (Integer.parseInt(from.substring(2*i, 2*(i+1)), 16) & 0xFF);
        }
        return to;
    }

    private static String ClientHello(BufferedOutputStream outstream, byte [] client_random){
        String hs_header = new String("16"+"0300"+"002D");
        String body = new String("01"+"000029"+"0300");
        Date date = new Date();
        Random r = new Random();
        r.nextBytes(client_random);
        String gmt_unix_time = Long.toHexString(date.getTime()/1000);
        while (gmt_unix_time.length()<8)
            gmt_unix_time = "0" + gmt_unix_time;
        System.arraycopy(string2byte(gmt_unix_time),0,client_random,0,4);
        body += byte2string(client_random);
        body += ("00"+"0002"+"002F"+"0100");
        try{
            outstream.write(string2byte(hs_header+body));
            outstream.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        return body;
    }

    private static String read_message(BufferedInputStream bufreader){
        String msg = new String();
        try{
            int data;
            byte [] buf = new byte [5];
            bufreader.read(buf, 0, 5);
            msg = byte2string(buf);
            int remaining = (buf[3]&0xFF)*256+(buf[4]&0xFF);
            while( remaining > 0 ){
                data = bufreader.read() & 0xFF;
                if (data < 16)
                    msg += "0" + Integer.toHexString(data);
                else
                    msg += Integer.toHexString(data);
                remaining -= 1;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return msg;
    }

    private static String ClientKeyExchange(BigInteger pubexp,
                   BigInteger pubmod, BufferedOutputStream outstream,
                   byte [] premaster_secret){
        String msg = new String();
        String hs_header = new String("16"+"0300"+"0084");

        String body = new String("10"+"000080");
        Random r = new Random();
        r.nextBytes(premaster_secret);
        premaster_secret[0] = 0x03;
        premaster_secret[1] = 0x00;

        // RSA encryption using public exponent PKCS1 padded
        String padded_premas = new String("0002");
        int tmp;
        for (int i=0; i<128-3-premaster_secret.length; i++){
            tmp = r.nextInt(255)+1; // avoid ZERO
            if (tmp <16)
                padded_premas += "0" + Integer.toHexString(tmp);
            else
                padded_premas += Integer.toHexString(tmp);
        }
        padded_premas += "00"+byte2string(premaster_secret);

        BigInteger y = new BigInteger(padded_premas,16);
        y = y.modPow(pubexp, pubmod);
        int i;
        for (i=0; i<pubmod.toString(16).length()-y.toString(16).length();i++)
            body += "0";
        if (pubmod.toString(16).length()%2==1) body += "0";
        body += y.toString(16);
        try{
            outstream.write(string2byte(hs_header + body));
            outstream.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        return body;
    }

    private static byte [] calc_master_secret(byte [] premaster_secret,
                    byte [] client_random, byte [] server_random){
        String m_secret_str = new String();
        try{
            int i,j;
            MessageDigest md_md5 = MessageDigest.getInstance("MD5");
            MessageDigest md_sha = MessageDigest.getInstance("SHA");

            String cst = new String();
            for (i=0; i<3; i++){
                cst = "";
                for (j=0; j<=i; j++)
                    cst += Integer.toHexString(65+i);

                byte [] hash_target = string2byte(cst
                        + byte2string(premaster_secret) + byte2string(client_random)
                        + byte2string(server_random));
                byte [] SHA = md_sha.digest(hash_target);
                hash_target = string2byte(byte2string(premaster_secret)
                        + byte2string(SHA));
                byte [] MD5 = md_md5.digest(hash_target);
                m_secret_str += byte2string(MD5);
            }    
        }catch(Exception e){
            System.out.println(e);
        }
        return string2byte(m_secret_str);
    } 

    private static byte [] calc_key_block(byte [] master_secret,
                    byte [] server_random, byte [] client_random){
        String key_block = new String();
        try{
            int i,j;
            MessageDigest md_md5 = MessageDigest.getInstance("MD5");
            MessageDigest md_sha = MessageDigest.getInstance("SHA");

            String cst = new String();
            for (i=0; i<8; i++){
                cst = "";
                for (j=0; j<=i; j++)
                    cst += Integer.toHexString('A'+i);
                byte[] hash_target = string2byte(cst
                        + byte2string(master_secret) + byte2string(server_random)
                        + byte2string(client_random));
                byte[] SHA = md_sha.digest(hash_target);
                hash_target = string2byte(byte2string(master_secret)
                        + byte2string(SHA));
                byte[] MD5 = md_md5.digest(hash_target);
                key_block += byte2string(MD5);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return string2byte(key_block);
    }

    private static void ChangeCipherSpec(BufferedOutputStream outstream){
        String msg = new String();
        try{
            msg = "14"+"0300"+"0001"+"01";
            byte [] msg_byte = string2byte(msg);
            outstream.write(msg_byte);
            outstream.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        return;
    }

    private static String ClientFinished(BufferedOutputStream outstream,
            String msg_history, byte [] key_block){
        String msg = new String();
        String fs_header = new String();
        String body_plain = new String();
        String body_encrypted = new String();
        try{
            int i;
            fs_header = ("16"+"0300"+"0040");
            body_plain = calcHashMac(msg_history, key_block, "client");
            byte [] enc_key = new byte [16];
            byte [] enc_iv = new byte [16];
            System.arraycopy(key_block, 40, enc_key, 0, 16);
            System.arraycopy(key_block, 72, enc_iv , 0, 16);
            body_encrypted = 
                byte2string(aes_encode(string2byte(body_plain), enc_key, enc_iv));
            msg = fs_header + body_encrypted;
            outstream.write(string2byte(msg));
            outstream.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        int mac_len = 20*2;
        return body_plain.substring(0, body_plain.length()-mac_len);
    }

    private static void analyse_sfinished(String msg, String msg_history, byte [] key_block){
        String msg_decrypted = new String();
        String calculated = new String();
        try{
            byte []ctext = string2byte(msg.substring(5*2,msg.length()));
            byte []enc_key = new byte [16];
            byte []enc_iv = new byte [16];
            System.arraycopy(key_block, 56, enc_key, 0, 16);
            System.arraycopy(key_block, 88, enc_iv, 0, 16);
            msg_decrypted=byte2string(aes_decode(ctext, enc_key, enc_iv));
            calculated=calcHashMac(msg_history, key_block, "server");
            if (msg_decrypted.indexOf(calculated) < 0){
                System.out.println("Server Finished NG");
                System.out.println("decrypted message from server : "+msg_decrypted);
                System.out.println("internally calcurated : "+calculated);
                System.exit(1);
            }else{
                System.out.println("Server Finished OK");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return;
    }

    private static String calc_MAC(String fragment, 
            byte [] MAC_write_secret, byte [] seq_num, String alg){
        String digest = new String();
        try{
            MessageDigest md = MessageDigest.getInstance(alg);
            String pad1 = new String();
            String pad2 = new String();
            for (int i=0; i<40; i++){
                pad1 += "36";
                pad2 += "5C";
            }
            String compressed_type = "16";
            String compressed_len  = Integer.toHexString(fragment.length()/2);
            while (compressed_len.length()<4) compressed_len = "0" + compressed_len;

            String inner = byte2string(md.digest(
                        string2byte(byte2string(MAC_write_secret) + pad1
                            + byte2string(seq_num) + compressed_type
                            + compressed_len + fragment)
                        ));
            digest = byte2string(md.digest(
                        string2byte(byte2string(MAC_write_secret) + pad2 + inner)
                        ));
        }catch(Exception e){
            System.out.println(e);
        }
        return digest;
    }

    private static String calcHashMac(String message, byte [] key_block, String sender){
        String body_plain = new String();
        try{
            int i;
            byte [] mac_secret = new byte [20];
            if (sender == "client"){
                sender = "434C4E54";
                System.arraycopy(key_block, 0, mac_secret, 0, 20);
            }else if (sender == "server"){
                sender = "53525652";
                System.arraycopy(key_block, 20, mac_secret, 0, 20);
            }else{
                System.exit(1);
            }
            String pad1 = new String();
            String pad2 = new String();
            for (i=0; i<48; i++){
                pad1 += "36";
                pad2 += "5C";
            }
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte [] hash_target = string2byte(msg_history + sender
                    + byte2string(master_secret) + pad1);
            hash_target = string2byte(byte2string(master_secret) + pad2
                    + byte2string(md5.digest(hash_target)));
            String md5hash = byte2string(md5.digest(hash_target));
            pad1 = pad1.substring(0,40*2);
            pad2 = pad2.substring(0,40*2);
            MessageDigest sha = MessageDigest.getInstance("SHA");
            hash_target = string2byte(msg_history + sender
                    + byte2string(master_secret) + pad1);
            hash_target = string2byte(byte2string(master_secret) + pad2
                    + byte2string(sha.digest(hash_target)));
            String shahash = byte2string(sha.digest(hash_target));

            String MAC_target = "14"+"000024" + md5hash + shahash;
            byte [] seq_num = {0,0,0,0,0,0,0,0};
            String MAC = calc_MAC(MAC_target, mac_secret, seq_num, "SHA");

            body_plain = MAC_target + MAC;
        }catch(Exception e){
            System.out.println(e);
        }
        return body_plain;
    }

    private static byte [] aes_encode(byte [] plain, byte [] key, byte [] iv){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), ivspec);
            String padding = new String();
            String pad = new String();
            int padding_len;
            if (plain.length%16 != 15){
                padding_len = 15 - (plain.length%16);
                pad = Integer.toHexString(padding_len);
                if (pad.length()%2 == 1) pad = "0" + pad;
                for (int i=0; i<padding_len; i++)
                    padding += pad;
            }
            padding += pad;
            byte [] in = string2byte(byte2string(plain)+padding);
            byte [] enc = cipher.doFinal(in);

            return enc;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static byte [] aes_decode(byte [] ctext, byte [] key, byte [] iv){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), ivspec);
            byte [] dec = cipher.doFinal(ctext);
            return dec;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args){
        msg_history = new String();
        try{
            // setting
            String HOST = "localhost";
            int PORT = 443;

            // open port
            Socket socket = new Socket (HOST, PORT);
            BufferedOutputStream bufwriter =
                new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream bufreader =
                new BufferedInputStream(socket.getInputStream());

            // ClientHello
            msg_history += ClientHello(bufwriter,client_random);
            System.out.println("ClientHello");

            // ServerHello & Certificate & ServerDone
            String line = read_message(bufreader);
            msg_history += line.substring(5*2);
            System.arraycopy(string2byte(line.substring(11*2,(11+32)*2)), 0, server_random, 0, 32);
            int cert_len = Integer.parseInt(line.substring(86*2,89*2),16);
            byte [] certData = string2byte(line.substring(89*2,(89+cert_len)*2));

            InputStream cd = new ByteArrayInputStream(certData);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate x509cert = (X509Certificate)cf.generateCertificate(cd);
            RSAPublicKey pubkey = (RSAPublicKey) x509cert.getPublicKey();
            System.out.println("ServerHello");

            KeyStore ks = KeyStore.getInstance ( "JKS" );
            String cert_file = "miniSSL/clientTrustStore";
            char [] password = "asdfghjkl".toCharArray();
            String dest_name = "server";
            FileInputStream fis = new FileInputStream(cert_file);
            ks.load(fis, password);
            RSAPublicKey tpubkey = (RSAPublicKey)ks.getCertificate(dest_name).getPublicKey();
            BigInteger ca_pubexp = tpubkey.getPublicExponent();
            BigInteger ca_pubmod = tpubkey.getModulus();
            byte [] certdata = x509cert.getTBSCertificate();
            byte [] signature = x509cert.getSignature();
            BigInteger bsig = new BigInteger(signature);
            String srv_sig = bsig.modPow(ca_pubexp, ca_pubmod).toString(16);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String calc_sig = byte2string(md5.digest(certdata));
            if (srv_sig.indexOf(calc_sig)<0){
                System.out.println("Invalid Certificate");
                System.exit(1);
            }else{
                System.out.println("ServerCertificate");
            }
            System.out.println("ServerDone");

            // ClientKeyExchange
            BigInteger pubexp = pubkey.getPublicExponent();
            BigInteger pubmod = pubkey.getModulus();

            msg_history += ClientKeyExchange(pubexp, pubmod, bufwriter, premaster_secret);
            System.out.println("ClientKeyExchange");

            master_secret = calc_master_secret(premaster_secret, client_random, server_random);
            key_block  = calc_key_block(master_secret, server_random, client_random);

            // ChangeCipherSpec
            ChangeCipherSpec(bufwriter);
            System.out.println("ChangeCipherSpec");

            // Finished
            msg_history += ClientFinished(bufwriter, msg_history, key_block);
            System.out.println("ClientFinished");

            // ServerKeyExchange
            Thread.sleep(1000);
            line = read_message(bufreader);
            if (line.equals("140300000101")){
                System.out.println("ChangeCipherSpec from Server");
            }

            // Check ServerFinished
            line = read_message(bufreader);
            analyse_sfinished(line, msg_history, key_block);

            // close socket
            bufwriter.close();
            bufreader.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}


