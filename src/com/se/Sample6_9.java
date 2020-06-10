package com.se;

abstract class X {                //抽象クラス
	  protected abstract void methodA();
	}
	abstract class Y extends X { }   //抽象クラス
	class Z extends Y {              //具象クラス
	  protected void methodA(){
		  System.out.println("class Z methodA");
	  }
	  //public void methodA(){ }     //public修飾子でもOK
	  //void methodA(){ }            //修飾子の指定なしはNG
	}

public class Sample6_9 {
	public static void main(String[] args) {
		X x = new Z();
		x.methodA();
		Y y = new Z();
		y.methodA();
		Z z = new Z();
		z.methodA();

	}

}
