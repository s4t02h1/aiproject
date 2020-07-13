package sample;
import sample.Member;
//import sample.Member;
//import java.time.LocalDate;

public class StudentMember2 extends Member {
	private String birthday;
	public StudentMember2(int id, String name, String birthday) {
		super(id, name);
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
//	public birthday expirationDate() {
//		return birthday.plusYears(18);
//	}

}
