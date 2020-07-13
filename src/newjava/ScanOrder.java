package newjava;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ScanOrder {

	public static void main(String[] args) {
		Path path = Paths.get("order.txt");
		try (Scanner in = new Scanner(path);){
			while(in.hasNext()) {
				String code = in.next();
				String date = in.next();
				int quantity = in.nextInt();
				System.out.println(code + "/" + date + "/" + quantity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
