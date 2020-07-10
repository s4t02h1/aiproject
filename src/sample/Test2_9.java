package sample;

import java.util.ArrayList;
import java.util.List;
public class Test2_9 {
	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(2);
		ArrayList list = new ArrayList();
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(i1); list2.add(i2);
		System.out.println(list2);
	}
}