package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

	public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("当美術館の入館人数を入力してください");

        String str = null;

        try {
            str = br.readLine(); //コンソール入力

            int num = Integer.parseInt(str);
            System.out.println( num + " 名ですね。");
            int val = (int)(num * 850 * 0.7);

            if (num <=0) {
            	System.out.println("おととい来てください！");
            }
            else if (num < 5) {
            	System.out.println( num * 850 + "円になります。");
            }
            else if (num >= 5 ) {
            	System.out.println( val + "円になります。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
