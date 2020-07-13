package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataIdentify {

		public static void main(String[] args) {

			String[] bk_id = {"B018","B015","B014","B011","B009","B003","B999","B987"};
			String[] bk_nm = {"漫画	君たちはどう生きるか","火花","長生きしたけりゃふくらはぎをもみなさい",
					"謎解きはディナーのあとで","1Q84","バカの壁","五体不満足","サラダ記念日"};
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	        System.out.println("ご希望の書籍のIDを入力してください");

	        String str = null;

	        try {
	            str = br.readLine(); //コンソール入力

	            int num = Integer.parseInt(str);
//	            System.out.println( num + " 名ですね。");
//	            int val = (int)(num * 850 * 0.7);
	            for(int num = 0; num < 9; num++) {
	            	switch (num) {
	            	 case
		            	System.out.println("おととい来てください！");
		            }
		            else if (num < 5) {
		            	System.out.println( num * 850 + "円になります。");
		            }
		            else if (num >= 5 ) {
		            	System.out.println( val + "円になります。");
	    			System.out.print(bk_nm[num]);
	    		}

	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		}

	}

