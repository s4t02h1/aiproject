package sample;

public class ExecCircle  {

	public static void main(String[] args) {
		Circle cc = new Circle("blue", 5.5);
		System.out.println("色 =" + cc.getColor() +
				"半径=" + cc.getRadius()
				+ "面積=%6.2f" + cc.area());

	}

}
