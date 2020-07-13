package sample;

public class MethodTestBMI {

	private double weight;
	private double height;
	public MethodTestBMI(double weight, double height) {
		this.weight = weight;
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	void calcBmi() {
		double bmi = getWeight()/(getHeight()*getHeight());
		if (bmi < 18.5) {
			System.out.println(bmi + " :低体重です。");
		}else if (bmi >= 18.5 && bmi < 25 ) {
			System.out.println(bmi + " :普通体重です。");
		}else
			System.out.println(bmi + " :肥満です。");

	}

}

class calcBmi {
	public static void main(String[] args) {

		MethodTestBMI m = new MethodTestBMI(70.5,1.75);
		m.calcBmi();
		MethodTestBMI l = new MethodTestBMI(81.0,1.65);
		l.calcBmi();
		MethodTestBMI n = new MethodTestBMI(100.8,1.80);
		n.calcBmi();

	}
}