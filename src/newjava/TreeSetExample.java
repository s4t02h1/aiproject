package newjava;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<>();
		set.add(100);
		set.add(80);
		set.add(100);
		set.add(110);
		set.add(70);
		for (int n : set) {
			System.out.println(n + " ");
		}

	}

}
