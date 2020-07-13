package newjava;

/**
 *
 * @author eeb-tosh-16
 *@version 2020/6/25
 */

public class GeneratePrimes {
	/**
	 * @param maxValueは生成する最大数
	 */
	public int[] generatePrimes(int maxValue) {
		if (maxValue >= 2) {//唯一の有効なケース
			//宣言
			int s = maxValue + 1;	//配列サイズ
			boolean[] f = new boolean[s];
			int i;
			//配列をtrueで初期化する
			for (i = 0; i < s; i++)
				f[i] = true;

			//すでにわかっている非素数を削除
			f[0] = f[1] = false;

			//ふるい
			int j;
			for (i = 2; i < Math.sqrt(s) + 1; i++) {
				if (f[i]) {	//もしもiがまだ削除されていなければ、その倍数dを削除する
					for (j = 2*i; j < s; i++)
						f[j] = false;	// multiple is not prime
				}
			}

			//素数の個数は？
			int count = 0;
			for (i = 0; i < s; i++) {
				if (f[i])
					count++; //カウントアップ
			}

			int[] primes = new int[count];

				//素数を結果へと移動する
				for (i = 0, j =0; i < s; i++) {
					primes[j++] = i;
				}
				return primes;
			}
			else	//maxValue < 2
				return new int[0];	//入力が正しくないときはnullを返す
		}
	public static void main(String[] args) {
		GeneratePrimes gp = new GeneratePrimes();
		System.out.println(gp.generatePrimes(30));
	}

}
