package newjava;

class Foo{
	public void show() {
	}
}
class Bar extends Foo{
	@Override
	public void show() {
		System.out.println("★Barクラスです");
	}
}
class Baz extends Foo{
	@Override
	public void show() {
		System.out.println("☆Bazクラスです");
	}
}
public class PolymorphismDemo {
	public static void main(String[] args) {
		Foo foo = new Bar();
		foo.show();
		foo = new Baz();
		foo.show();
	}
}
