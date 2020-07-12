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
        // ポート番号
	    int port = 443;
		SSLServerSocket server = null;
		
		// サーバー・キーストア
		String keyStore = "SSLtest/server/serverKeystore";
		
		BufferedWriter out = null;
		BufferedReader in  = null;
		try {
			// KeyStoreのロード
			KeyStore ks = KeyStore.getInstance( "JKS" );
			
			char[] keystorePass = "lkjhgfdsa".toCharArray();
			ks.load( new FileInputStream( keyStore ) , keystorePass );

			KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
			kmf.init( ks, keystorePass );
			
			SSLContext sslContext = SSLContext.getInstance( "SSLv3" );
			sslContext.init( kmf.getKeyManagers() , null , null );
			SSLServerSocketFactory ssf = sslContext.getServerSocketFactory();
			// サーバーソケット生成
			SSLServerSocket srvSocket  = (SSLServerSocket)ssf.createServerSocket( port );

			// 無限ループで待機
			while( true ){
			    System.out.println( "SSL接続を待機しています。" );
				Socket client = srvSocket.accept(); // クライアントからの接続待ちの状態に入る
				System.out.println( "Clientから接続されました。" );
				
				in  = new BufferedReader( new InputStreamReader ( client.getInputStream() ) );
				out = new BufferedWriter( new OutputStreamWriter( client.getOutputStream() ) );
				
				String msg = in.readLine();
				
				System.out.println("Clientからのメッセージ:" + msg );
				out.write( "Hello Client\n" );  // クライアントに文字列送信
				out.flush();
                closeReader( in );
                closeWriter( out );
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}	    
	}
}

