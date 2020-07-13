package sample;

public class Circle extends GeometricShape {
	public double radius;
	public double area;

	public Circle(String color, double radius) {
		super(color);
		this.radius = radius;

	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double area() {
		return Math.pow(radius, 2)*Math.PI ;
	}


}
