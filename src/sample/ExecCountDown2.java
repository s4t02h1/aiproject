package sample;

public class ExecCountDown2 extends CountDown2 {
	super();

	public static void main(String[] args) {

		ccd.setCountDown(10, 7);
		ccd.doCharCountDown();
		System.out.println();
		ccd.setChar('*');
		ccd.doCharCountDown();
	}

}
