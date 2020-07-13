package sample;

public class Array0 {
	// Use this initializer form when the data fixed:
	static int monthLen[] = {
			31, 28, 31, 30,
			31, 30, 31, 31,
			30, 31, 30, 31,
	};

	public static void main(String[] args) {
		for (int i = 0; i<monthLen.length; i++) {
			System.out.println("Month" + (i + 1) + " has " +
					monthLen[i] + " days.");
		}
	}
}
