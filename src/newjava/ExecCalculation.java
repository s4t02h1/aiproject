package newjava;

public class ExecCalculation {

	public static void main(String[] args) {
		double dt[] = {122.3 ,445.6,666.7};
		Calculation cal = new Sum(dt);
	//			Sum cal2 = new Sum(dt);
		System.out.println(cal.calculate());
	//	System.out.println(cal2.average());
		System.out.println(((Sum)cal).average());
	}
}
