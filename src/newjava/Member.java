package newjava;

public class Member implements Version {
	private int id;
	private String name;
	public Member(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getVersion() {

		return "Memberクラス version 1.0";
	}

}
