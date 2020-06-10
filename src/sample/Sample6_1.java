package sample;

class Employee6{
	private String id = "100";
	public String getId() {
		return id;
	}
}

class Sales2 extends Employee6{
	private String clientName = "SE";
	public String getClientName() {
		return clientName;
	}
}

public class Sample6_1 {
	public static void main(String[] args) {
		Sales2 s = new Sales2();
		System.out.println("clientName: " + s.getClientName());
		System.out.println("id		  :	" + s.getId());
	}
}
