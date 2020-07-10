package newjava;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ScannerExample1 {

	public static void main(String[] args) {
		Path path = Paths.get("data.txt");

		try (Scanner in = new Scanner(path);) {

			in.useDelimiter("[ \t] + |" + System.lineSeparator());
			while (in.hasNext()) {
				int number = in.nextInt();
				String name = in.next();
				double weight = in.nextDouble();

				System.out.println(number + "\t" + name + "\t" + weight);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
