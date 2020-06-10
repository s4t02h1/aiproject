package newjava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MoveExample2 {

	public static void main(String[] args) throws Exception {
		Path source = Paths.get("temp/memo.txt");
		Path target = Paths.get("foo/bar/memo.txt");
		Files.move(source, target, StandardCopyOption.ATOMIC_MOVE);
	}

}
