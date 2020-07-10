package newjava;

public class ExecGeometricShape {

	public static void main(String[] args) {
		GeometricShape geo = new Circle("white", 5.71);
		System.out.println(geo.getName() + geo.area());
		System.out.println(geo.getColor());
	}
}
