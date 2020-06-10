package newjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewBufferedReaderExample {

	public static void main(String[] args) throws IOException{

		Path path = Paths.get("nagasaki.txt");
		BufferedReader in = Files.newBufferedReader(path);

		String line;
		while((line = in.readLine()) != null) {
			System.out.println(line);
		}

	}

}
