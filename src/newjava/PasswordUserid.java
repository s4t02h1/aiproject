package newjava;

import java.util.Scanner;

public class PasswordUserid {
	public static void main(String[] args) {

		System.out.println("useridを入力してください。");
		Scanner sc = new Scanner(System.in);
		String ui = sc.nextLine();

		try {
			if (ui == null||ui.length() == 0) {
				System.out.println("useridが不正です。");
			}else if (ui.matches("^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\\\s+).{4,8}$")) {
				System.out.println(ui);
			}else {
			System.out.println("Errorです。");
			}
			System.out.println("入力okです。 ");
		}catch (Exception e) {
			System.out.println("Errorです。");
		}

		System.out.println("passwordを入力してください。");
		Scanner psc = new Scanner(System.in);
		String pw = psc.nextLine();

		try {
			if (pw == null||pw.length() == 0) {
				System.out.println("passwordが不正です。");
			}else if (pw.matches("^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\\\s+).{4,8}$")) {
				System.out.println(pw);
			}else {
			System.out.println("Error");
			}
			System.out.println("Your password : ");
		} catch (Exception e) {
			System.out.println("Errorです。");
		}
	}
}
