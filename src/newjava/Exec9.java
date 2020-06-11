package newjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import newjava.MyLogin.Input;

class Auth{
	public void check(Login l) {
		l.getInfo();
		if(!l.isUser()) {
			System.out.println("Login failure");
		}else {
			System.out.println("Welcome");
		}
	}
}

class Login{
	String[] idList = {"tanaka", "yamada"};
	String uid;

	public void getInfo() {
		uid = Input.inputString();
	}

	public boolean isUser(){
		for (int i = 0; i < idList.length; i++) {
			if (uid.equals(idList[i])) {
				return true;
			}
		}
		return false;
	}
}
class MyLogin extends Login{
		int[] pwList = {1234, 5678};
		int upw;
		public void getInfo() {
			upw = Input.inputString();
		}
		public boolean isUser(){
			for (int i = 0; i < pwList.length; i++) {
			if (upw.equals(pwList[i])) {
				return true;
			}
		}
		return false;
	}

	static class Input {
		static String str;
		public static String inputString() {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				str = in.readLine();
			}catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}


public class Exec9 {
	public static void main(String[] args) {
		Auth au = new Auth();
		Login Io = new Login();
		au.check(Io);
	}
}
