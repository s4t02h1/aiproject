package sample;
public class ExecStudentMember2 {

	public static void main(String[] args) {
		StudentMember2 stmem =
				new StudentMember2(100, "今井智", "1985年1月22日");
		System.out.println(stmem.getId());
		System.out.println(stmem.getName());
		System.out.println(stmem.getBirthday());

	}

}
