package blackjack.mylib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {
	public static String getString(String s) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str;
		try {
			System.out.print(s + "    >");
			str = in.readLine();
			if(str.length() > 0) {
				return str;
			}else {
				return null;
			}
		}catch(IOException e) {
			return "";
		}
	}
}
