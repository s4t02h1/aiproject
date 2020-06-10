package sample;

public class Sample4_6 {

	public static void main(String[] args) {
		int count1 = 0;

		for(; count1 < 5 ; count1++) { //式1を省略した例
			System.out.println(count1);
		}
		System.out.println(); //改行
		for (int count2 = 0 ; count2 < 5 ;) {
			System.out.println(count2++); //処理文内でカウンタ変数の更新
		}

	}

}
