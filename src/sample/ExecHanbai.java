package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class ExecHanbai extends Hanbai {

	public ExecHanbai(int kosu, int tanka, int sougaku) {
		super(kosu, tanka, sougaku);
	}
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("［個数］＞");
        String str = null;

        try {
            str = br.readLine(); //コンソール入力
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("［単価］＞");
        String str2 = null;
        try {
            str2 = br2.readLine(); //コンソール入力
        } catch (IOException e) {
            e.printStackTrace();
        }
        int kosu = Integer.parseInt(str);
        int tanka = Integer.parseInt(str2);
        int sougaku = kosu * tanka;
        Hanbai hb = new Hanbai(kosu, tanka, sougaku);
        double nebiki = hb.nebikiRitu();
        hb.print(sougaku, nebiki);
	}

}
