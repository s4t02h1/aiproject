package newjava;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NewBufferedWriterExample {

	public static void main(String[] args) {
		Path path = Paths.get("data.txt");
		try (BufferedWriter out = Files.newBufferedWriter(path, 
								StandardOpenOption.CREATE,
								StandardOpenOption.APPEND);){
			
			out.write(100 + "\t");
			out.write("佐藤一郎" + "\t");
			out.write(73.2 + "\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
