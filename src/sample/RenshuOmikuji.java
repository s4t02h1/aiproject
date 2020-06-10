package sample;

public class RenshuOmikuji {

	public static void main(String[] args) {
		int num = (int)(Math.random() * 6 + 1);
		System.out.println("おみくじは" + num + "です。");

		switch(num) {
		case 0:
			System.out.println("大吉です。");
			break;
		case 1:
			System.out.println("大吉です。");
			break;
		case 2:
			System.out.println("中吉です。");
			break;
		case 3:
			System.out.println("中吉です。");
			break;
		case 4:
			System.out.println("凶です。");
			break;
		default:
			System.out.println("吉です。");
		}

	}

}
