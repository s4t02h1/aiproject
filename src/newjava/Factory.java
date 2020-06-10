package newjava;

public abstract class Factory {
	 abstract public String toString();
}

class MyFactory extends Factory{
	String product;
	int price;

	public MyFactory(String product, int price) {
		this.product = product;
		this.price = price;
	}
	@Override
	public String toString() {
		return product + ":" + price;
	}
}

interface Visible {
	void view();
}
class FactoryViewer implements Visible{
	Factory f;

	public FactoryViewer(Factory f) {
		super();
		this.f = f;
	}

	@Override
	public void view() {
		System.out.println(f.toString());
	}
}

