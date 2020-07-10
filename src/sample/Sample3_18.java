package sample;

public class Sample3_18 {

	public static void main(String[] args) {
		int a = 10;
		/*if(a > 0) {
			System.out.println("aは正の値です。");
			if(a % 2 == 0) {
				System.out.println("aは偶数です。");
			}
		}else {
		 if(a == 0) {
			 System.out.println("aは0です。");
		 }else {
			 System.out.println("aは負の値です。");
		 }

		}*/
		String str = null;
	str =	a > 0 ? a % 2 == 0 ? "aは偶数です。":"aは正の値です。"  : a == 0 ? "aは0です。":"aは負の値です。";
		System.out.println(str);
	}

}
