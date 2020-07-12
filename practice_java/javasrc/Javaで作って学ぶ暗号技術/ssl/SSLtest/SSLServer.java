package SSLtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

import javax.net.ssl.*;

public class SSLServer {
	public static void main(String[] args) throws Exception{
        // �|�[�g�ԍ�
	    int port = 443;
		SSLServerSocket server = null;
		
		// �T�[�o�[�E�L�[�X�g�A
		String keyStore = "SSLtest/server/serverKeystore";
		
		BufferedWriter out = null;
		BufferedReader in  = null;
		try {
			// KeyStore�̃��[�h
			KeyStore ks = KeyStore.getInstance( "JKS" );
			
			char[] keystorePass = "lkjhgfdsa".toCharArray();
			ks.load( new FileInputStream( keyStore ) , keystorePass );

			KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
			kmf.init( ks, keystorePass );
			
			SSLContext sslContext = SSLContext.getInstance( "SSLv3" );
			sslContext.init( kmf.getKeyManagers() , null , null );
			SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
			// �T�[�o�[�\�P�b�g����
			SSLServerSocket srvSocket  = (SSLServerSocket)ssf.createServerSocket( port );

			// �������[�v�őҋ@
			while( true ){
			    System.out.println( "SSL�ڑ���ҋ@���Ă��܂��B" );
				Socket client = srvSocket.accept(); // �N���C�A���g����̐ڑ��҂��̏�Ԃɓ���
				System.out.println( "Client����ڑ�����܂����B" );
				
				in  = new BufferedReader( new InputStreamReader ( client.getInputStream() ) );
				out = new BufferedWriter( new OutputStreamWriter( client.getOutputStream() ) );
				
				String msg = in.readLine();
				
				System.out.println("Client����̃��b�Z�[�W:" + msg );
				out.write( "Hello Client\n" );  // �N���C�A���g�ɕ����񑗐M
				out.flush();
                closeReader( in );
                closeWriter( out );
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}	    
	}
}

