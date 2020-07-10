package newjava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateExample3 {
	public static void main(String[] args) throws Exception {
		Path path = Paths.get("foo/bar/note.txt");
		Files.createFile(path);

	}

}
