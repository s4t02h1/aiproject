package sample;

class Pandemic {

		//インスタンス変数にprivate修飾子を指定
		private int id;

		//コンストラクタにpublic修飾子を指定
		public Pandemic(int i) {
			id = i;
}

		//メソッドにpublic修飾子を指定
		public int getId() {
			return id;
		}
}

public class Sample5_10{

	public static void main(String[] args) {

		Pandemic pdm = new Pandemic(10000);

		//private指定されたメンバは、他クラスからアクセス不可
		//System.out.println(
			// "private指定のインスタンス変数へアクセス : " + pdm.id);
		//public指定されたメンバは、他クラスからアクセス可
		System.out.println(
				"public指定のメソッドへアクセス : " + pdm.getId());

	}
}
