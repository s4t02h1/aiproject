public class TimeMill {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		long time = end - start;
		
		System.out.print(time);
	}
}
