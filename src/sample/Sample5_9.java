package sample;

class Who{
	static {
		System.out.println("Whoクラス：static イニシャライザ");
	}
	{
		System.out.println("Whoクラス：イニシャライザ");
	}
	Who(){
		System.out.println("Whoクラス：コンストラクタ");
	}
}

public class Sample5_9 {
	static {
		System.out.println("Sample5_9クラス：static イニシャライザ");
	}

	public static void main(String[] args) {
		System.out.println("Sample5_9クラス：main()メソッド");
		Who f = new Who();
		Who f2 = new Who();
		Who f3 = new Who();
	}

}
