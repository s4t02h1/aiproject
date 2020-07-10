package newjava;

abstract class AbsCar {
	String name;
	public AbsCar(String name) {
		this.name = name;
	}
	public abstract double maxSpeed();
}

public class Automobile extends AbsCar {
	public Automobile(String name) {
		super(name);
	}
	@Override
	public double maxSpeed() {
			return 300;
	}

	public static void main(String[] args) {
		AbsCar obj = new Automobile("RacingCar");
		obj.maxSpeed();

	}

}
