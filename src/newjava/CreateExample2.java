package newjava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateExample2 {

	public static void main(String[] args) throws Exception {
		Path path = Paths.get("foo/bar");
		Files.createDirectories(path);

	}

}
