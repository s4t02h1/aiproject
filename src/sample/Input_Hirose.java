package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input_Hirose {

        public static void main(String[] args) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("人数を半角数字で入力してください");

                String str = null;
                try {
                        str = br.readLine(); //コンソール入力

                } catch (IOException e) {
                        e.printStackTrace();
                }

                System.out.println("入力された文字は " + str + "です");

                int NYUKAN_RYO = 850;
                int DANTAI_WARI_MIN_NIN = 5;
                float DANTAI_WARI_RATE = 0.7f;

                int ninzu = Integer.parseInt(str);
                int ryokin = 0;

                if (ninzu <= 0) {
                } else if (ninzu >= DANTAI_WARI_MIN_NIN) {
                        ryokin = (int)(ninzu * NYUKAN_RYO * DANTAI_WARI_RATE);
                } else {
                        ryokin = ninzu * NYUKAN_RYO;
                }

                System.out.println("入場料は" + ninzu + "人で" + ryokin + "円です。");

        }
}
