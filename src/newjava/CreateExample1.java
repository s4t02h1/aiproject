package newjava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateExample1 {

	public static void main(String[] args) {

		Path path = Paths.get("temp");
		try {
			Files.createDirectory(path);

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
