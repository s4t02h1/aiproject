package sample;

public class Employee {
	private String name;
	private int id = 100;


	public Employee() {
//		super();
	}
	public Employee(String name,int id) {
		this.name = name;
		this.id = id;

//		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
