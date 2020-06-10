package sample;

class CountDown2 {
	protected int from;
	protected int to;

	public void setCountDown(int f, int t) {
		from = f;
		to = t;
	}
	public void doCoundDown() {
		System.out.println("Count Down Start!");
		for (int i = from; i >= to; i--) {
			System.out.println(i);

		}
		System.out.println("Count Down End!");

	}

}

class CharCountDown extends CountDown2 {
	private char moji = 0;
	CharCountDown ccd = new CharCountDown();

	public CharCountDown(int from, int to) {
		super();
		this.from= from;
		this.to = to;
	}

	public void setChar(char moji) {
		this.moji = moji;

	}

	public void doCharCountDown() {

		System.out.println("CharCount Down Start!");
		for (int i = from; i >= to; i--) {
			for (int j = i; j < i; j++) {
				System.out.println(moji);
			}

		}
		System.out.println("CharCount Down End!");

	}
}
