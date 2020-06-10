package sample;
public class ExecStudentMamber2 {

	public static void main(String[] args) {
		StudentMember stmem =
				new StudentMember(100, "今井智", "1985年1月22日");
		System.out.println(stmem.getId());
		System.out.println(stmem.getName());
		System.out.println(stmem.getBirthday());

	}

}
