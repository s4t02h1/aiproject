// JankenC.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

enum TE {
	GUU(0), CHOKI(1), PAH(2);
	private final int value;
	TE(int value) {this.value = value;}
}

class Client {
    // private static final String SERVER_HOST = "localhost";
    private static final String SERVER_HOST = "192.168.11.8";
    private static final int SERVER_PORT = 56789;
    private static final char DATA_END = '#';
    private static final int TIMEOUT = 5000;

    void exec(){
        // try-with-resource文でリソースを最後に解放するようにする
        try (Socket sock = new Socket()) {
            sock.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT), TIMEOUT);
            sock.setSoTimeout(TIMEOUT);
            try (Reader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                Writer out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))){
                 // ユーザーの入力を取得する
                 System.out.print("\nあなたの手(G,C,P)==>");    // プロンプト
                 InputStreamReader isr = new InputStreamReader( System.in );
                 BufferedReader br = new BufferedReader( isr );
                 String sndMsg = "";
                 try {
                      sndMsg = br.readLine();
                 } catch (IOException e) {
                      e.printStackTrace();
                 }
                 // 先頭の文字を取得する
                 String sc = sndMsg.substring(0,1).toLowerCase();
                 TE myTe = TE.GUU;
                 if (sc.equals("g"))
                     System.out.println("あなたはグー");
                 if (sc.equals("c")) {
                     System.out.println("あなたはチョキ");
                     myTe = TE.CHOKI;
                 }
                 if (sc.equals("p")){
                     System.out.println("あなたはパー");
                     myTe = TE.PAH;
                 }
                 out.write(sndMsg);            // データをサーバーに送出する
                 out.write(DATA_END);        // エンドマーク
                 out.flush();

                 // サーバーから送られるデータを受信する
                 char[] buf = new char[4096];
                 int len = 0;
                 while( (len = in.read(buf)) == -1)
                      ;
                 String s = String.valueOf(buf).substring(0, len);
                 System.out.println("受け取ったデータ：" + s);
                 TE serverTe = TE.GUU;
                 if (serverTe == TE.GUU)
                     System.out.println("サーバーはグー");
                 if (serverTe == TE.CHOKI){
                     System.out.println("サーバーはチョキ");
                     serverTe = TE.CHOKI;
                 }
                 if (serverTe == TE.PAH) {
                     System.out.println("サーバーはパー");
                     serverTe = TE.PAH;
                 }
                 if (serverTe == myTe)
                     System.out.println("引き分け");
                 if (serverTe == TE.GUU && myTe == TE.PAH)
                     System.out.println("あなたの勝ち");
                 if (serverTe == TE.CHOKI && myTe == TE.GUU)
                     System.out.println("あなたの勝ち");
                 if (serverTe == TE.PAH && myTe == TE.CHOKI)
                     System.out.println("あなたの勝ち");
                 if (serverTe == TE.GUU && myTe == TE.CHOKI)
                     System.out.println("サーバーの勝ち");
                 if (serverTe == TE.CHOKI && myTe == TE.GUU)
                     System.out.println("サーバーの勝ち");
                 if (serverTe == TE.PAH && myTe == TE.PAH)
                     System.out.println("サーバーの勝ち");
             }catch (IOException e ) {
                 e.printStackTrace();
             }
             sock.close();
        } catch (SocketTimeoutException e ) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}

public class JankenC {
    public static void main(String... args) {
        System.out.println("スタート");
        Client my = new Client();
        my.exec();
        System.out.println("終了");
    }
}
