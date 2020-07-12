package SSLtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;



public class SSLClient {
	public static void main(String[] args) throws Exception{
        // 接続先サーバー名,ポート番号
        String serverHost = "localhost";
	    int port = 443;
		SSLServerSocket server = null;
		
        // サーバー証明書の設定
		String trustStore = "SSLtest/client/clientTruststore";
		System.setProperty("javax.net.ssl.trustStore" , trustStore );
		System.setProperty("javax.net.ssl.trustStorePassword", "asdfghjkl");
		
		SSLSocket       s    = null;
		PrintWriter     out  = null;
		BufferedReader  in   = null;
  		
		try{
			KeyStore ks = KeyStore.getInstance ( "JKS" );
			KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
			kmf.init( null , null  );
			SSLContext ctx = SSLContext.getInstance ( "SSLv3" );
			ctx.init( kmf.getKeyManagers() , null , null );
			
			// SSLSocket生成
			SSLSocketFactory factory  = ctx.getSocketFactory();
			s = (SSLSocket)factory.createSocket( serverHost , port );

			// ハンドシェイク
			s.startHandshake();
			
			// メッセージ送信，サーバーからの入力を読み込み，標準出力に出力
			out = new PrintWriter( s.getOutputStream() );
			in  = new BufferedReader( new InputStreamReader(s.getInputStream() ) );
			
			out.write( "Hello Server\n" );
			out.flush();
			String msg = in.readLine();
			System.out.println("Serverからのメッセージ：" + msg );
			
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try {
				if ( s   != null ) s.close();
				if ( out != null ) out.close();
				if ( in  != null ) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    
}
