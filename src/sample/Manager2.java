package sample;

public class Manager2 extends Employee2 {
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Manager2(long id, String name, int age, String title) {
		super(id, name, age);
		this.title = title;

	}

}
