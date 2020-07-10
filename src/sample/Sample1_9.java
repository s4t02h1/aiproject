package sample;

class Sample1_9 {
	public static void main(String[] args) {
		int x = 100;
		{
			x = 200;
			System.out.println(x);
		}
		x = 300;
		System.out.println(x);
	}
}