package miniSSL;

import java.net.*;
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManager;
import javax.net.ssl.X509KeyManager;
import java.security.cert.X509Certificate;

public class miniSSLs {
    private static String msg_history = new String();
    private static byte [] master_secret = new byte [48];
    private static byte [] premaster_secret = new byte [48];
    private static byte [] client_random = new byte [32];
    private static byte [] server_random = new byte [32];
    private static byte [] key_block = new byte [16*10];
    private static BigInteger sec_exp;
    private static BigInteger pub_mod;

    private static byte [] string2byte(String from){
        int i;
        byte [] to = new byte [from.length()/2];
        for (i=0; i<from.length()/2; i++){
            to[i] = (byte) (Integer.parseInt(from.substring(2*i, 2*(i+1)), 16) & 0xFF);
        }
        return to;
    }
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

    private static String ServerHelloCertDone(BufferedOutputStream outstream, byte [] server_random){
        String hs_header = new String("16"+"0300"+"0290");
        // ServerHello
        String body = new String("02"+"000046"+"0300");
        Date date = new Date();
        Random r = new Random();
        r.nextBytes(server_random);
        String gmt_unix_time = Long.toHexString(date.getTime()/1000);
        while (gmt_unix_time.length()<8)
            gmt_unix_time = "0" + gmt_unix_time;
        System.arraycopy(string2byte(gmt_unix_time),0,server_random,0,4);
        body += byte2string(server_random);
        body += "20";
        byte [] sessID = new byte [32];
        r.nextBytes(sessID);
        body += byte2string(sessID);
        body += "002F"+"00";
        // Certificate
        body += "0B"+"00023E"+"00023B"+"000238";
        try{
            String keyStore = new String("miniSSL/serverKeystore");
            KeyStore ks = KeyStore.getInstance("JKS");
            char [] keyStorePass = "lkjhgfdsa".toCharArray();
            ks.load( new FileInputStream( keyStore ), keyStorePass);
            X509Certificate cert = (X509Certificate) ks.getCertificate("server");
            body += byte2string(cert.getEncoded());

            RSAPrivateKey pk = (RSAPrivateKey) ks.getKey("server","lkjhgfdsa".toCharArray());
            sec_exp = pk.getPrivateExponent();
            pub_mod = pk.getModulus();
        }catch(Exception e){
            System.out.println(e);
        }

        // ServerHelloDone
        body += "0E000000";
        try{
            outstream.write(string2byte(hs_header+body));
            outstream.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        return body;
    }

    private static String readClientKey(BigInteger pubexp,
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
            tmp = r.nextInt(256);
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

    private static String analyse_cfinished(String text, String msg_history, byte [] key_block){
        String msg_decrypted = new String();
        String calculated = new String();
        try{
            byte [] ctext = string2byte(text.substring(5*2,text.length()));
            byte [] enc_key = new byte [16];
            byte [] enc_iv = new byte [16];
            System.arraycopy(key_block, 40, enc_key, 0, 16);
            System.arraycopy(key_block, 72, enc_iv , 0, 16);
            msg_decrypted = byte2string(aes_decode(ctext, enc_key, enc_iv));
            msg_decrypted = msg_decrypted.substring(0, msg_decrypted.length()-8);
            calculated = calcHashMac(msg_history, key_block, "client");
            if (msg_decrypted.indexOf(calculated) < 0){
                System.out.println("Client Finished NG");
                System.out.println("decrypted message from client : "+msg_decrypted);
                System.out.println("internally calculated : "+calculated);
                System.out.println("msg_history : "+msg_history);
                System.exit(1);
            }else{
                System.out.println("Client Finished OK");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        int mac_len = 20*2;
        return msg_decrypted.substring(0, msg_decrypted.length()-mac_len);
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


    private static void ServerFinished(BufferedOutputStream outstream,
            String msg_history, byte [] key_block){
        String msg = new String();
        try{
            int i;
            String fs_header = ("16"+"0300"+"0040");
            String body_plain = calcHashMac(msg_history, key_block, "server");
            byte [] enc_key = new byte [16];
            byte [] enc_iv = new byte [16];
            System.arraycopy(key_block, 56, enc_key, 0, 16);
            System.arraycopy(key_block, 88, enc_iv , 0, 16);
            String body_encrypted =
                byte2string(aes_encode(string2byte(body_plain), enc_key, enc_iv));
            msg = fs_header + body_encrypted;
            outstream.write(string2byte(msg));
            outstream.flush();
        }catch(Exception e){
            System.out.println(e);
        }
        return;
    }

    private static String read_message(BufferedInputStream bufreader){
        String msg = new String();
        try{
            int data;
            byte [] buf = new byte [5];
            bufreader.read(buf, 0, 5);
            msg = byte2string(buf);
            int remaining = (buf[3]&0xFF)*256+buf[4]&0xFF;
            while( remaining > 0 ){
                data= bufreader.read() & 0xFF;
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


    private static byte[] aes_encode(byte [] plain, byte []  key, byte [] iv) {
        try {
            //Cipher cipher = Cipher.getInstance("AES/CBC/SSL3Padding");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), ivspec);

            iv = cipher.getIV();
            String padding = new String();
            String pad = "00";
            int padding_len = 0;
            if (plain.length%16!=15){
                padding_len = 15 - (plain.length%16);
                pad = Integer.toHexString(padding_len);
                if (pad.length()%2==1) pad = "0" + pad;
                for (int i=0; i<padding_len; i++)
                    padding += pad;
            }
            padding += pad;
            byte [] in = string2byte(byte2string(plain)+padding);

            byte[] enc = cipher.doFinal(in);
            byte[] ret = new byte[enc.length];
            System.arraycopy(enc,0,ret,0,enc.length);

            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), ivspec);
            byte[] dec = cipher.doFinal(enc);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] aes_decode(byte [] ctext, byte []  key, byte [] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), ivspec);
            byte[] dec = cipher.doFinal(ctext);
            return dec;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        msg_history = new String();
        String line = new String();
        try{
            // setting
            int PORT = 443;

            // open port
            ServerSocket srvsocket = new ServerSocket(PORT);
            System.out.println("Listening on port " + PORT);
            Socket socket = srvsocket.accept();

            BufferedOutputStream bufwriter =
                new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream bufreader =
                new BufferedInputStream(socket.getInputStream());

            // receive ClientHello
            line = read_message(bufreader);
            System.out.println("ClientHello from client");
            System.arraycopy(string2byte(line),11,client_random,0,32);
            msg_history += line.substring(5*2);

            // send ServerHello & Certificate & ServerDone
            msg_history += ServerHelloCertDone(bufwriter,server_random);

            // receive ClientKeyExchange
            line = read_message(bufreader);
            System.out.println("ClientKeyExchange from client");
            msg_history += line.substring(5*2);
            //BigInteger ctext = new BigInteger(string2byte(line.substring(9*2, (9+128)*2)));
            BigInteger ctext = new BigInteger(line.substring(9*2, (9+128)*2),16);
            byte [] ptext = ctext.modPow(sec_exp,pub_mod).toByteArray();
            System.arraycopy(ptext, ptext.length-48, premaster_secret, 0, 48);
            
            // calc_mastersecret , key_block
            master_secret = calc_master_secret(premaster_secret, client_random, server_random);
            key_block  = calc_key_block(master_secret, server_random, client_random);

            // receive ChangeCipherSpec
            line = read_message(bufreader);
            System.out.println("ChangeCipherSpec from client");

            // Finished
            line = read_message(bufreader);
            System.out.println("Finished form client");
            msg_history += analyse_cfinished(line, msg_history, key_block);

            // send ChangeCipherSpec
            ChangeCipherSpec(bufwriter);

            // send Finished
            ServerFinished(bufwriter, msg_history, key_block);

            // close socket
            bufwriter.close();
            bufreader.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}


