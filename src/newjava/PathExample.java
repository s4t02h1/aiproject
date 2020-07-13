package newjava;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {

	public static void main(String[] args) {
		Path p = Paths.get("C:/docs/note.txt");
		System.out.println(p);
		System.out.println(p.getRoot());
		System.out.println(p.getParent());
		System.out.println(p.getFileName());

	}

}
