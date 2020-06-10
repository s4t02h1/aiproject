package sample;

public class Cashbook {
	private int balance;			//現金の現在高
	public void deposit(int n) {	//預入金額
		this.balance += n;
	}
	public void drownOut(int n) {	//引出金額
		if (n < balance)
			this.balance -= n;
		else
			this.balance = 0;
	}
	public Cashbook(int balance) {	//コンストラクタ
		this.balance = balance;
	}
	public int getBalance() {		//現在高を返す
		return balance;
	}
}
class ExeceAccess {
	public static void main(String[] args) {
		Cashbook myBook = new Cashbook(1000);		//オブジェクト作成
		myBook.deposit(500);						//500円預入
		myBook.drownOut(800);						//800円引出
		System.out.println("残高＝" + myBook.getBalance() + "円");	//現在高表示

		Cashbook myBook2 = new Cashbook(1000);		//オブジェクト作成
		myBook2.deposit(1000);						//500円預入
		myBook2.drownOut(3000);						//800円引出
		System.out.println("残高＝" + myBook2.getBalance() + "円");	//現在高表示
	}
}
