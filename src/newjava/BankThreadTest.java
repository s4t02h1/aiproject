package newjava;

public class BankThreadTest {
	public static void main(String[] args) {
		Bank bank = new Bank(100000);
		AccountThread[] t = new AccountThread[10];
		for (int i = 0; i < 10 ; i++) {
			t[i] = new AccountThread(bank, i);
			System.out.println(t + " ");
		}
		bank.start();
	}
}
