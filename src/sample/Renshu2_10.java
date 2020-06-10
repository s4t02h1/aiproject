package sample;

class Renshu2_10 {
	public static void main(String[] args) {
		int[] ary1 = {10, 20, 30};
		int[] ary2 = ary1;
		ary2[0] = 50;
		System.out.println(ary1[0]);
	}
}