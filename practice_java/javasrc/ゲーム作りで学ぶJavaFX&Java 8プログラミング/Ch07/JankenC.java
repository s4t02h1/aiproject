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
        // try-with-resource���Ń��\�[�X���Ō�ɉ������悤�ɂ���
        try (Socket sock = new Socket()) {
            sock.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT), TIMEOUT);
            sock.setSoTimeout(TIMEOUT);
            try (Reader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                Writer out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))){
                 // ���[�U�[�̓��͂��擾����
                 System.out.print("\n���Ȃ��̎�(G,C,P)==>");    // �v�����v�g
                 InputStreamReader isr = new InputStreamReader( System.in );
                 BufferedReader br = new BufferedReader( isr );
                 String sndMsg = "";
                 try {
                      sndMsg = br.readLine();
                 } catch (IOException e) {
                      e.printStackTrace();
                 }
                 // �擪�̕������擾����
                 String sc = sndMsg.substring(0,1).toLowerCase();
                 TE myTe = TE.GUU;
                 if (sc.equals("g"))
                     System.out.println("���Ȃ��̓O�[");
                 if (sc.equals("c")) {
                     System.out.println("���Ȃ��̓`���L");
                     myTe = TE.CHOKI;
                 }
                 if (sc.equals("p")){
                     System.out.println("���Ȃ��̓p�[");
                     myTe = TE.PAH;
                 }
                 out.write(sndMsg);            // �f�[�^���T�[�o�[�ɑ��o����
                 out.write(DATA_END);        // �G���h�}�[�N
                 out.flush();

                 // �T�[�o�[���瑗����f�[�^����M����
                 char[] buf = new char[4096];
                 int len = 0;
                 while( (len = in.read(buf)) == -1)
                      ;
                 String s = String.valueOf(buf).substring(0, len);
                 System.out.println("�󂯎�����f�[�^�F" + s);
                 TE serverTe = TE.GUU;
                 if (serverTe == TE.GUU)
                     System.out.println("�T�[�o�[�̓O�[");
                 if (serverTe == TE.CHOKI){
                     System.out.println("�T�[�o�[�̓`���L");
                     serverTe = TE.CHOKI;
                 }
                 if (serverTe == TE.PAH) {
                     System.out.println("�T�[�o�[�̓p�[");
                     serverTe = TE.PAH;
                 }
                 if (serverTe == myTe)
                     System.out.println("��������");
                 if (serverTe == TE.GUU && myTe == TE.PAH)
                     System.out.println("���Ȃ��̏���");
                 if (serverTe == TE.CHOKI && myTe == TE.GUU)
                     System.out.println("���Ȃ��̏���");
                 if (serverTe == TE.PAH && myTe == TE.CHOKI)
                     System.out.println("���Ȃ��̏���");
                 if (serverTe == TE.GUU && myTe == TE.CHOKI)
                     System.out.println("�T�[�o�[�̏���");
                 if (serverTe == TE.CHOKI && myTe == TE.GUU)
                     System.out.println("�T�[�o�[�̏���");
                 if (serverTe == TE.PAH && myTe == TE.PAH)
                     System.out.println("�T�[�o�[�̏���");
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
        System.out.println("�X�^�[�g");
        Client my = new Client();
        my.exec();
        System.out.println("�I��");
    }
}
