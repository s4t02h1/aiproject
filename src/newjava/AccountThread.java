package newjava;

public class AccountThread extends Thread{
	private Bank bank;
	private int account;

	public AccountThread(Bank bank, int num) {
		String name = "スレッド" + (getAccount() + 1);
		this.bank = bank;
		this.setAccount(account);
	}

	public void run() {
		for (int i = 0; i <= 100 ; i++) {
			//System.out.println(this.name + "回目の処理");
			Thread threadE = new Thread(() -> {
				System.out.println("sleep start : ");
				try {
					int to = (int)(10*Math.random());
					bank.transfer(to, getAccount(), 6000);
					System.out.println("");
					bank.show(getAccount());
					bank.sum();
					Thread.sleep((int)(2000*Math.random()));
				} catch (InterruptedException e) {
					System.out.println("interrupted");
				}
				System.out.println("process restart");
			});
		}
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

}
