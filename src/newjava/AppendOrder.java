package newjava;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AppendOrder {

	public static void main(String[] args) {
		Path path = Paths.get("order.txt");
		try (BufferedWriter out = Files.newBufferedWriter(path,
												StandardOpenOption.CREATE,
												StandardOpenOption.APPEND);){
			out.write("ICBK62" + "\t");
			out.write("2020-09-02" + "\t");
			out.write(12 + "\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
