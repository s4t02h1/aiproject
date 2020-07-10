package newjava;

public class ExecFactory {
	public static void main(String[] args) {
	Factory f = new MyFactory("BigBerry",450);
	Visible v = new FactoryViewer(f);
	v.view();
	}
}
