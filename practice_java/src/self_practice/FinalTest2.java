public class FinalTest2 //コンパイルエラー
{
	private static final Circle wheel = new Circle(5.0);
	
	public static void main(String[] args)
	{
		System.out.println("Radius of wheel is " +
							wheel.radius());
		wheel = new Circle(7.4);
		System.out.println("Radius of wheel is now " +
							wheel.radius());
	}
}