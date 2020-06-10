package newjava;

public class Sample10_3 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				System.out.println("hello");
			}
		}).start();

		new Thread(()-> {
			System.out.println("hello");
		}).start();

	}

}
