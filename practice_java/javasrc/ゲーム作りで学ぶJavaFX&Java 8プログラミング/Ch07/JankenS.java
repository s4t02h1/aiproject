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
    private static final int SERVER_TIMEOUT = 60000;    // 60�b
    private static final char DATA_END = '#';
    private static final int TIMEOUT = 10000;    // 10�b

    public void run() {
        // try-with-resource���Ń��\�[�X���Ō�ɉ������悤�ɂ���
        try (ServerSocket ssock = new ServerSocket(PORT)) {
            ssock.setSoTimeout(SERVER_TIMEOUT);
            Socket sock = ssock.accept();
            System.out.println("�ڑ����܂����B");
            // try-with-resource���Ń��\�[�X���Ō�ɉ������悤�ɂ���
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
                System.out.println("�󂯎�����f�[�^=" + s);  //�@�󂯎�����f�[�^

                // �T�[�o�[���̎�𐶐�����
                Random rnd = new Random( Instant.now().getNano());
                Integer te = rnd.nextInt(3);  // �T�[�o�[�̎�
                // tes  0:�T�[�o�[�̎�̓O�[, 1:�`���L, 2:�p�[
                s = Integer.toString(te);
                out.write(s);
                out.flush();
                System.out.println("�o�̓f�[�^�F" + s);     // �o�͂����f�[�^
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (SocketTimeoutException e) {
            System.out.println("�^�C���A�E�g�ł��B");
        }catch (SocketException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class JankenS {

    public static void main(String... args) {
        System.out.println("�X�^�[�g");
        Server prog = new Server();
        prog.run();
        System.out.println("�I��");
    }
}
