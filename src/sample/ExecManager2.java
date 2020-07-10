package sample;

public class ExecManager2 {

	public static void main(String[] args) {
		Manager2 mg = new Manager2(110, "森下 樹", 32, "プロジェクトマネージャー");
		System.out.println(mg.getId() + "\t"+ mg.getName() +
				"\t" + mg.getAge() + "\t" + mg.getTitle());
	}

}
