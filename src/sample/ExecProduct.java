package sample;

public class ExecProduct {

	public static void main(String[] args) {
		Product p1 = new Product("A100","XenPad", 35760, true);
		int quantity = 5;
		System.out.println("商品名= " + p1.getName());
		System.out.println("個数= " + quantity);
		System.out.println("総額= " + p1.totalPrice(quantity));

		System.out.println(p1.isHighPrice(p1.totalPrice(quantity)));
	}

}
