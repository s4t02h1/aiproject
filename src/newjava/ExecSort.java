package newjava;

import java.util.Arrays;

public class ExecSort {

	public static void main(String[] args) {

		Seiseki[] seisekis = {
				new Seiseki("鈴木貴", 75, 60),
				new Seiseki("斎藤由紀子", 80, 75),
				new Seiseki("田中康一", 68, 66),
				new Seiseki("河合華子", 89, 71),
				new Seiseki("中村一生", 91, 78),
		};
		Arrays.sort(seisekis);
		for(Seiseki s : seisekis) {
			System.out.println(s);
		}

	}

}
