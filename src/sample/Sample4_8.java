package sample;

import java.util.ArrayList;

public class Sample4_8 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");	list.add("b"); list.add("c");
		//for文
		for (int count = 0; count < list.size(); count++) {
			System.out.print(list.get(count) + " ");
		}
		System.out.println();
		//拡張for文
		for (String str : list) {	//拡張for文で処理する場合
			System.out.print(str + " ");
		}
	}
}
