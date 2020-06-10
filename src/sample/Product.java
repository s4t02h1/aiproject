package sample;

public class Product {

	private String number;
	private String name;
	private int price;
	private boolean stock;



	public Product(String number, String name, int price, boolean stock) {
		this.number = number;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int totalPrice(int quantity) {
		int total = price * quantity;
		return total;
	}

	public boolean isHighPrice(int p) {
		if (p < price) {
			return false;
		}else
			return true;

	}

}
