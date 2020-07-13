package newjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewBufferedReaderWithCharset {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("nagasaki_ms932.txt");
		BufferedReader in = Files.newBufferedReader(path, Charset.forName("MS932"));

		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

	}

}
