package newjava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pass1 {

	public static void main(String[] args)  {
		Path p1 = Paths.get("text/note.txt");
		Path p2 = Paths.get("books");
		Path p3 = Paths.get("books/pdf/ref.pdf");
		Path p4 = Paths.get("hello.html");

		Path p0 = Paths.get("html");
				try {
					Files.createFile(p4);
					Files.createDirectory(p0);
					Files.move(p4, p0);
				}catch (IOException e) {
					e.printStackTrace();
				}
		System.out.println(p0);
		System.out.println(p0.toAbsolutePath());

	}

}
