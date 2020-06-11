package newjava;

public class Sample10_7 {
	private int a = 0;
	private String b;

	public synchronized void set() {
		while ( a != 0) {
			try {
				wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		a++; b = "data";
		System.out.println("set() a : " + a + "b : " + b);
	}

	public  synchronized void print() {
		while (b == null) {
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		notify();
		a--; b = null;
		System.out.println(" print() a : " + a + " b: " + b);
	}
}
