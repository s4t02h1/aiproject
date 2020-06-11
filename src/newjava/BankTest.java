package newjava;

public class BankTest {
	public static void main(String[] args) {
		Bank bank = new Bank(100000);
		 do {
			int to = (int)(10*Math.random());
			bank.transfer(to, 0, 6000);
			bank.show(0);
			bank.show(to);
		} while (bank.getAccount(0) > 6000 );
		 System.out.println("合計" + bank.sum());
	}
}
