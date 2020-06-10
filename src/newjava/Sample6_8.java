package newjava;

abstract class Super { //抽象クラス
	  protected abstract void methodA();  //抽象メソッド
	  public void methodB(){
		  System.out.println("Super methodB");
	  }             //具象メソッド
	}
	class Sub extends Super {   //具象クラス
	  protected void methodA(){
		  System.out.println("Sub methodA");
	  } // 必須//抽象メソッドは必ず実装
	  // 以下でもOK。アクセス修飾子は同じか公開範囲を広いものを使用
	  // public void methodA(){ }
	  public void methodB(){
		  System.out.println("Override");
	  }    // 任意//必要に応じて実装可能
	}

public class Sample6_8 {
	public static void main(String[] args) {
		Super sp = new Sub();
		sp.methodA();
		sp.methodB();


	}

}
