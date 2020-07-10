package sample;

public class GeometricShape {
	private String color;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	protected GeometricShape(String color) {
		super();
		this.color = color;
	}

}
