package newjava;

public class Synchronizedexample {

	public static void main(String[] args) {
		Share share = new Share();
		ThreadC threadC = new ThreadC(share);
		ThreadD threadD = new ThreadD(share);
		threadC.start(); threadD.start();
	}
}

class Share {
	private int a = 0;
	private String b;
	public synchronized void set() {
		a++; b = "data";
		System.out.println("set() a : " + a + "b : " + b);
	}
	public synchronized void print() {
		a--; b = null;
		System.out.println(" print() a : " + a + " b : " + b);
	}
}

class ThreadC extends Thread {
	private Share share;
	public  ThreadC(Share share) {this.share = share; }
	public void run() {
		for (int i =0; i < 5; i++) {share.set();}
	}
}

class ThreadD extends Thread {
	private Share share;
	public ThreadD(Share share) {this.share = share;}
	public void run() {
		for (int i = 0; i < 5; i++) {share.print();}
	}
}