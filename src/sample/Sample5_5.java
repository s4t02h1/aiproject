package sample;

class Corona{
		int id;		String name;
		Corona() {System.out.println("Corona()");}
		Corona(String name) { System.out.println("Corona(String name)");}
		Corona(int id){System.out.println("Employee(int id)");}
		Corona(int id, String name){System.out.println("Employee(int id, String name)");}
}

public class Sample5_5 {

	public static void main(String[] args) {
		Corona a = new Corona();
		Corona b = new Corona("yamamoto");
		Corona c = new Corona(100);
		Corona d = new Corona(100,"yamamoto");
	}

}
