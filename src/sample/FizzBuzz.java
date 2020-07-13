package sample;

public class FizzBuzz {

	public static void main(String[] args) {
		int num = (int)(Math.random() * 100 + 1);

		if (num % 3 == 0) {
			System.out.println(num);
			System.out.println("Fizz");
		}else if( num % 5 == 0){
			System.out.println(num);
			System.out.println("Buzz");
		}else if(num % 15 == 0) {
			System.out.println(num);
			System.out.println("FizzBuzz");
		}else
			System.out.println(num);

	}
}