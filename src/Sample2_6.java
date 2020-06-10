import java.util.ArrayList;

public class Sample2_6 {

	public static void main(String[] args) {
		ArrayList<String> array;
		array = new ArrayList<String>(3);
		array.add("田中");
		String name = array.get(0);
		System.out.println(name + " : " + array.size());

	}

}
