package sample;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class FunctionSample {
	public static void main(String[] args) {
		int num = 10;
		int add = calc(num, n -> n + 10);
		int sub = calc(num, n -> n - 10);
		int mul = calc(num, n -> n * 10);
		int div = calc(num, n -> n / 10);
		System.out.println(add + " " + sub + " " + mul + " " + div);

		String str = "abc_de";
		// 空文字かどうか判定
		boolean b2 = judge(str, s -> s.isEmpty());
		// 5文字以上かどうか判定
		boolean b1 = judge(str, s -> s.length() >= 5);
		// 文字aを含むかどうか判定
		boolean b3 = judge(str, s -> s.contains("a"));
		boolean b4 = judge(str, s -> s.indexOf("a") != -1);
		// 出力
		System.out.println(b1 + " " + b2 + " " + b3 + " " + b4);
	}
	static int calc(int n, UnaryOperator<Integer> u) {
		return u.apply(n);
	}
	// judge()メソッドを定義
	// 修飾子: static
	// 戻り値: boolean  // 引数: String s, Predicate<String> p
	// 処理: Predicateインタフェースのtest()メソッドに引数sを渡した結果を返す
	static boolean judge(String s, Predicate<String> p) {
		return p.test(s);
	}
}

