package newjava;

public class CheckType {

	public static void main(String[] args) {
		Member mem = new Member(1234, "今井");
		if (mem instanceof Member) {
			System.out.println("Member型です");
		}
		if (mem instanceof Version) {
			System.out.println("Version型です");
		}

	}

}
