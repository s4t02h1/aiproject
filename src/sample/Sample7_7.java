package sample;

class Foo7_7 {
	  String name;
	  Foo7_7(String name) { this.name = name; }
//	public String toString() {
//		return "名前は" + name + "です。";
//	}
}

public class Sample7_7 {

	public static void main(String[] args) {
		Foo7_7[] ary = { new Foo7_7("tanaka"),
        new Foo7_7("suzuki"),
        new Foo7_7("yamada") };

		System.out.println(ary);
		System.out.println(ary[1]);
		System.out.println(ary[1].name);
	}

}
