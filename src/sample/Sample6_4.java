package sample;

class Foo1{
	String s; int i;
	public Foo1() {
		this("no_data");
	}
	public Foo1(String s) {
		this(s,1);
	}
	public Foo1(String s, int i) {
		this.s = s; this.i = i;
		System.out.println("String:" + this.s);
		System.out.println("int:" + this.i);
	}
}

public class Sample6_4 {

	public static void main(String[] args) {
		System.out.println("Foo()の呼び出し--------------------");
		Foo1 f1 = new Foo1();
		System.out.println("Foo(\"Hey\")の呼び出し------------------");
		Foo1 f2 = new Foo1("Hey");
		System.out.println("Foo(\"Bye\",200)の呼び出し-----------------");
		Foo1 f3 = new Foo1("Bye",200);

	}

}
