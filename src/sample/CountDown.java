package sample;

public class CountDown {
	private int from;
	private int to;
	public void doCountDown() {
		System.out.println("Count Down Start!");
//			for ( ; to <= from; from--) {
//				System.out.println(from);
//			}
			do {
				System.out.println(from);
				from--;
			}while(to <= from);
		System.out.println("Cound Down End!");
	}
	public CountDown(int from, int to) {
		this.from = from;
		this.to = to;
	}

}
