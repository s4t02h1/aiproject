package com.se;

interface XIF {
	  void methodA();
	}
	interface YIF {
	  void methodB();
	}
	interface SubIF extends XIF, YIF {
	  void methodC();
	}
	class MyClass1 implements SubIF{
	  public void methodA() { System.out.println("methodA()"); }
	  public void methodB() { System.out.println("methodB()"); }
	  public void methodC() { System.out.println("methodC()"); }
	}

public class Sample6_12 {

	public static void main(String[] args) {
		MyClass1 c1 = new MyClass1();
	    c1.methodA(); c1.methodB(); c1.methodC();
	}

}
