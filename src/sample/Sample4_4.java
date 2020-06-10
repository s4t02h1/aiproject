package sample;

public class Sample4_4 {

	public static void main(String[] args) {
		//while の場合
		int count = 5;
		while(count != 5 && count >0) {
			System.out.println("while     :count =" +  count);
			count--;
		}

		//do-whileの場合
		count = 5;
		do {
			System.out.println("do-while : count = " + count);
			count--;
		}while (count != 5 && count > 0);

	}

}
