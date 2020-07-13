package sample;

public class Sample5_2 {

	public static void main(String[] args) {

//		// Aさん用オブジェクトを作成
//		Employee a = new Employee();
//		// IDをセットする
//		a.setId(100);
//
//		// Bさん用オブジェクトを作成
//		Employee b = new Employee();
//		// IDをセットする
//		b.setId(200);

		Employee a = new Employee("A", 100);
		Employee b = new Employee("B", 200);

		// それぞれのIDの表示
		System.out.println(a.getName() + "さん :" + a.getId());
		System.out.println(b.getName() + "さん :" + b.getId());
	}

}
