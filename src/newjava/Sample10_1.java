package newjava;

public class Sample10_1 {

	public static void main(String[] args) {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		Thread a  = new Thread(threadA);
		Thread b = new Thread(threadB);

		a.start();
		b.start();
	}

}

class ThreadA implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("A:" + i + " ");
		}
	}
}

class ThreadB implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("B:" + i + " ");
		}
	}
}