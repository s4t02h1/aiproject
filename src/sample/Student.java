package sample;

import java.util.ArrayList;

public class Student {

	private String name;
	private int kokugo;
	private int sansu;

	public Student(String name, int kokugo, int sansu) {
		this.name = name;
		this.kokugo = kokugo;
		this.sansu = sansu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKokugo() {
		return kokugo;
	}

	public void setKokugo(int kokugo) {
		this.kokugo = kokugo;
	}

	public int getSansu() {
		return sansu;
	}

	public void setSansu(int sansu) {
		this.sansu = sansu;
	}

	void disp1() {
		System.out.println("氏名：" + getName() + "," + "国語：" + getKokugo() + "," +  "算数：" + getSansu() + "  優");
	}
	void disp2() {
		System.out.println("氏名：" + getName() + "," + "国語：" + getKokugo() + "," +  "算数：" + getSansu() +"  良");
	}
	void disp3() {
		System.out.println("氏名：" + getName() + "," + "国語：" + getKokugo() + "," +  "算数：" + getSansu() +"  可");
	}
	void disp4() {
		System.out.println("氏名：" + getName() + "," + "国語：" + getKokugo() + "," +  "算数：" + getSansu() +"  不可");
	}
}

class Hantei {
	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<Student>();

		list.add(new Student("佐藤",90,80));
		list.add (new Student("今井",70,57));
		list.add(new Student("鈴木",71,82));
		list.add(new Student("吉田",89,69));
		list.add(new Student("新谷",85,80));


		for (Student st : list) {
			if (st.getKokugo() >=80 && st.getSansu() >= 80) {
				st.disp1();
			}else if (st.getKokugo() >= 70 && st.getSansu() >= 70) {
					st.disp2();
			}else if (st.getKokugo() >= 60 && st.getSansu() >= 60) {
					st.disp3();
			}else
					st.disp4();
			}
		}
	}
