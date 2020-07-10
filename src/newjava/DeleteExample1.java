package newjava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteExample1 {

	public static void main(String[] args) throws Exception {
		Path target = Paths.get("foo/bar/memo.txt");
		if (Files.exists(target)) {
			Files.delete(target);
		}

	}

}
