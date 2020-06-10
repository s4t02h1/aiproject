package com.se;
interface MyInter1{
	void methodA();
}
abstract class Super {
	abstract void methodB();
}
class MyClass extends Super implements MyInter1 {
	public void methodA() {
		System.out.println("MyClass methodA");
	}

	@Override
	void methodB() {
		System.out.println("Super methodB");

	}
}
public class Sample6_11 {
	public static void main(String[] args) {
		MyClass mc = new MyClass();
		mc.methodA();
		mc.methodB();

	}

}
