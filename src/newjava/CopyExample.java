package newjava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyExample {

	public static void main(String[] args) throws Exception{
		Path source = Paths.get("foo/bar/note.txt");
		Path target = Paths.get("temp/note.txt");
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

	}

}
