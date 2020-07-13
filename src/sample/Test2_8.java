package sample;

import java.util.ArrayList;
class Test2_8 {
	public static void main(String[] args) {
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(10.0);		//list��10.0���i�[����
		double d = list.get(0);		//list����10.0�����o��
		System.out.println(d);
	}
}