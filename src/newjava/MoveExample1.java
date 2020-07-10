package newjava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoveExample1 {

	public static void main(String[] args) throws Exception {
		Path source = Paths.get("temp/memo.txt");
		Path target = Paths.get("foo/bar/memo.txt");
		Files.move(source, target);

	}

}
