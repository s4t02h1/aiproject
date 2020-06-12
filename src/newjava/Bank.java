package newjava;

public class Bank {
	private int[] account = new int[10];
	private int total;

	public Bank(int init) {
		for (int i = 0; i < account.length ; i++) {
			account[i] = init;
		}
	}
	public synchronized int sum() {
		for (int i = 0; i < account.length ; i++) {
			total += account[i];
		}
		return total;
	}
	public synchronized void transfer(int to, int from, int amount) {
		while(account[from] < amount) {
			try {
				account[from] -= amount;
				account[to] += amount;
				wait();
			} catch (InterruptedException e) {}
		}
		notifyAll();
		System.out.println("transfer done");
	}
	public synchronized void show(int i) {
		System.out.println("口座 " + (i +1) + ":" + account[i] + "円");
	}
	public synchronized int getAccount(int i) {
		return account[i];
	}
}
