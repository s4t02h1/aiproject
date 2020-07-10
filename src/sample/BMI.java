package sample;

		import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

		public class BMI {

			public static void main(String[] args) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		        System.out.println("身長(m)を入力してください。");
	        	System.out.println("体重(kg)を入力してください。");
		        String str1 = null;
		        String str2 = null;

		        try {
		            str1 = br.readLine(); //コンソール入力
		            str2 = br.readLine(); //コンソール入力

		            int num1 = Integer.parseInt(str1);
		            System.out.println( "身長" + num1 + "m");
		            int num2 = Integer.parseInt(str2);
		            System.out.println( "体重" + num2 + "kg");
		            System.out.println("ですね。");
		            int bmi = (int)(num2 / (num1 * num1));

		            if (bmi == 22) {
		            	System.out.println("普通の体形です。！");
		            }
		            else if (bmi <= 22) {
		            	System.out.println( "やや痩せ気味です");
		            }
		            else if (bmi >= 22 ) {
		            	System.out.println( "太り気味です。");
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

			}

	}
