// JankenS.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.Instant;
import java.util.Random;

class Server {
    private static final int PORT = 56789;
    private static final int SERVER_TIMEOUT = 60000;    // 60秒
    private static final char DATA_END = '#';
    private static final int TIMEOUT = 10000;    // 10秒

    public void run() {
        // try-with-resource文でリソースを最後に解放するようにする
        try (ServerSocket ssock = new ServerSocket(PORT)) {
            ssock.setSoTimeout(SERVER_TIMEOUT);
            Socket sock = ssock.accept();
            System.out.println("接続しました。");
            // try-with-resource文でリソースを最後に解放するようにする
            try (Reader in = new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
                     Writer out = new BufferedWriter (
                            new OutputStreamWriter(sock.getOutputStream()))){
                StringBuilder sb = new StringBuilder(4096);
                int c;
                while((c =in.read()) != -1) {
                    if (c == DATA_END)
                        break;
                    sb.append((char)c);
                }
                String s = sb.toString();
                System.out.println("受け取ったデータ=" + s);  //　受け取ったデータ

                // サーバー側の手を生成する
                Random rnd = new Random( Instant.now().getNano());
                Integer te = rnd.nextInt(3);  // サーバーの手
                // tes  0:サーバーの手はグー, 1:チョキ, 2:パー
                s = Integer.toString(te);
                out.write(s);
                out.flush();
                System.out.println("出力データ：" + s);     // 出力したデータ
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (SocketTimeoutException e) {
            System.out.println("タイムアウトです。");
        }catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class JankenS {

    public static void main(String... args) {
        System.out.println("スタート");
        Server prog = new Server();
        prog.run();
        System.out.println("終了");
    }
}
