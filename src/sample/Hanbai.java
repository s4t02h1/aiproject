package sample;

public class Hanbai {
	private int kosu;
	private int tanka;
	private int sougaku;
	public Hanbai(int kosu, int tanka, int sougaku) {
		this.kosu = kosu;
		this.tanka = tanka;
		this.sougaku = sougaku;
	}
	public double nebikiRitu() {
		if (kosu < 100)
			return  0.0;
		else if (kosu >= 100 && kosu < 500)
			return 0.05;
		else
			return 0.10;
	}
	public void print(int sougaku, double ritu) {
		System.out.printf("［個数］＞" + kosu);
		System.out.printf("［単価］＞" + tanka);
		System.out.printf("販売額＝%.10d円\n" + sougaku);
		System.out.printf("値引き＝%.10d円\n" + (int)(sougaku*ritu));
		System.out.printf("売　上＝%.10d円" + (int)(sougaku*(1-ritu)));
	}
}
