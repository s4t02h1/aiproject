package newjava;

import java.io.IOException;
import java.io.PrintWriter;

public class CreateOrder {

	public static void main(String[] args) {
		try (PrintWriter out = new PrintWriter("order.txt");) {

			out.print("ICBK61");
			out.print("\t");
			out.print("2020-07-11");
			out.print("\t");
			out.println(5);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
