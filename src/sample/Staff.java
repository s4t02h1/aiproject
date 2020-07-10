package sample;

public class Staff {

	String name;
	int age;
	double height;

	void disp() {
		System.out.println(name + age + height);
	}


public Staff(String name, int age, double height) {
		this.name = name;
		this.age = age;
		this.height = height;
	}
}

class StaffExec {
	public static void main(String[] args) {

		Staff[] ary = {
				new Staff("佐藤",26,176.3),
				new Staff("岡田",32,166.5),
				new Staff("島原",22,159.2),
				new Staff("佐々木",18,188.1),
				new Staff("金子",36,171.9)
			};

		for (Staff i : ary ) {
			i.disp();
		}
	}
}