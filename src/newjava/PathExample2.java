package newjava;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample2 {

	public static void main(String[] args) {

		Path p = Paths.get("note.txt");
		System.out.println(p);
		System.out.println(p.toAbsolutePath());
		Path p2 = Paths.get("");
		System.out.println(p2.toAbsolutePath());

	}

}
