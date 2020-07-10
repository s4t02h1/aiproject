package sample;

public class Denpyo {

	String date;
	String item;
	int price;
	int number;


	public Denpyo(String date, String item, int price, int number) {
		this.date = date;
		this.item = item;
		this.price = price;
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	void disp() {

		System.out.println(getDate() + " / " + getItem() + " / " +
							getPrice()+ " / " + getNumber());


	}



}
