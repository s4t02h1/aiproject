package sample;

public class Case {

	public static void main(String[] args) {
		//tag::main[]
		String name = "Java Cookbook";
		System.out.println("Normal:\t" + name);
		System.out.println("Upper:\t" + name.toUpperCase());
		System.out.println("Lower:\t" + name.toLowerCase());
		String javaName = "java cookBook"; // If it were Java identifiers :-)
		if (!name.equals(javaName))
			System.out.println("equals() correctly reports false");
		else
			System.out.println("equals() incorrectly reports true");
		if (name.equalsIgnoreCase(javaName))
			System.err.println("equalsIgnoreCase() correctly reports true");
		else
			System.err.println("equalsIgnoreCase() incorrectly reports false");
		// end::main[]

	}

}
