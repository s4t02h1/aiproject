package newjava;
class Circle
{
	private double rad;
	
	public Circle(double r)
	{
		rad = r;
	}
	
	public void setRadius(double r)
	{
		rad = r;
	}
	
	public double radius()
	{
		return rad;
	}
}

public class FinalTest
{
	private static final Circle wheel = new Circle(5.0);
	
	public static void main(String[] args)
	{
		System.out.println("Radius of wheel is " +
							wheel.radius());
		wheel.setRadius(7.4);
		System.out.println("Radius of wheel is now " +
							wheel.radius());
	}
}