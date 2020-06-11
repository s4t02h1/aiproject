public class GreatPrimeNumber {
	public static void main(String[] args) {
//		final int NUMBER_OF_PRIMES = 50; // Number of primes to display
//		final int NUMBER_OF_PRIMES_PER_LINE = 10; // Display 10 per line
//		int number = 2; // A number to be tested for primeness
		int total = 0;
		
		System.out.println("The total of the first prime numbers between 1 and 100are \n");
		
		// Repeatedly find prime numbers
		for (int number = 2; number < 100; number++) {
			boolean isPrime = true; // Is the current number prime?
			int divisor =2;
			divisor++;
			//Test whether number is prime
		//	for (int divisor = 2; divisor <= number; divisor++){
				if (number % divisor == 0) { // If true, number is not prime
					isPrime = false; // Set isPrime to false
					break; // Exit the for loop
				}else
					isPrime = true;
					total += number;
		//	}
			System.out.print(number);	
			System.out.println(total);
		}
	}
}
