package newjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sample8_8 {

	public static void main(String[] args) {
		Employee writeEmp = new Employee(100, "tanaka");
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("ren/8_8.txt"));
			 ObjectInputStream ois = new ObjectInputStream(
					 new FileInputStream("ren/8_8.txt"))){
			oos.writeObject(writeEmp);
			Employee readEmp = (Employee)ois.readObject();
			System.out.println("ID : " + readEmp.getId());
			System.out.println("Name : " + readEmp.getName());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}


	}

}
