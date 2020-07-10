package sample;

class Foo3 {}

public class Sample7_2 {

	public static void main(String[] args) {
		Foo3 f1 = new Foo3(); Foo3 f2 = new Foo3();
		System.out.println("f1.equals(f2)  :" + (f1.equals(f2)));

		Foo3 f3 = new Foo3(); Foo3 f4 = f3;
		System.out.println("f3.equals(f4)  :" + (f3.equals(f4)));

	}

}
