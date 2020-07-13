package newjava;

public abstract class GeometricShape {
	private String color;
	protected GeometricShape(String color) {
		this.color = color;
	}
	public abstract String getName();
	public abstract double area();
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
