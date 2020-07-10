package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Renshu2_3 {

	public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("好きな月を入力してください。");

        String str = null;

        try {
            str = br.readLine(); //コンソール入力

            int num = Integer.parseInt(str);
            System.out.println( num + " 月ですね。");

            if (num >= 3 && num <= 5) {
            	int val_spring = (int)(num);
            	System.out.println("春です。");
            }else if (num >= 6 && num <= 8) {
            	int val_summer = (int)(num);
            	System.out.println("夏です。");
            }else if (num >= 9 && num <= 11) {
            	int val_fall = (int)(num);
            	System.out.println("秋です。");
            }else if (num >= 1 && num <= 2) {
            	int val_winter = (int)(num);
            	System.out.println("冬です。");
            }else
            	System.out.println("月が入力されていません。");

//            switch (num) {
//            	case val_spring:
//            		System.out.println("春です。");
//            		break;
//            	case val_sumer:
//            		System.out.println("夏です。");
//            		break;
//            	case val_fall:
//            		System.out.println("秋です。");
//            		break;
//            	case val_winter:
//            		System.out.println("冬です。");
//            		break;
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
