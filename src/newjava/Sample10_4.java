package newjava;

public class Sample10_4 {

	public static void main(String[] args) {
		Thread threadA = new Thread(() -> {
			System.out.println("threadA : sleep start");
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				System.out.println("threadA : catched interruption");
			}
			System.out.println("threadA : process restart");
		});
		threadA.start();

		try {
			System.out.println("main : sleep start");
			Thread.sleep(4000);
			System.out.println("main : sleep end");
			threadA.interrupt();
		} catch (InterruptedException e) {
			System.out.println("main : catched interruption");
		}
	}
}
