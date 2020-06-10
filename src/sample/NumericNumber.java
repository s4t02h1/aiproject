package sample;

public class NumericNumber {

	public static void main(String[] args) {
		int num = 101;
		int sum = 0 ;
	  while(num <= 200) {
		  if(num % 2 == 0) {
			sum += num;

	  }num++;

	  }
	  System.out.println(sum);
	}

}