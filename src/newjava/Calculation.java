package newjava;

public abstract class Calculation {
	private double dt[];
	protected Calculation(double[] dt) {
		this.dt = dt;
	}
	public abstract double calculate() ;
	public double[] getDt() {
		return dt;
	}
}

class Sum extends Calculation{

	public Sum(double[] dt) {
		super(dt);
	}
	@Override
	public double calculate() {
		double sum = 0.0;
		for(double i : getDt()) {
			sum += i;
		}
		return sum;
	}
	public double average() {
		return calculate()/getDt().length;
	}

}