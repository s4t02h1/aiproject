package newjava;

import java.util.Arrays;

public class SplitExample2 {

	public static void main(String[] args) {
		String dt = "100, 田中 宏 , 60.5";
		String[] dts = dt.split("\\s*,\\s*");
		Arrays.stream(dts).forEach(System.out::println);

	}

}
