package newjava;

public class Bank {
	private int[] account = new int[10];
	private int total;

	public Bank(int init) {
		for (int i = 0; i < account.length ; i++) {
			account[i] = init;
		}
	}
	public int sum() {
		for (int i = 0; i < account.length ; i++) {
			total += account[i];
		}
		return total;
	}
	public void transfer(int to, int from, int amount) {
		account[from] -= amount;
		account[to] += amount;
	}
	public void show(int i) {
		System.out.println("口座 " + (i +1) + ":" + account[i] + "円");
	}
	public int getAccount(int i) {
		return account[i];
	}
}
