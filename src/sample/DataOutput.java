package sample;

public class DataOutput {

	public static void main(String[] args) {
	String [] name = {"佐藤 ", "岡田 ", "島原","佐々木","金子"};
	int [] age = {26,32,22,18,36};
	double [] hight = {176.3,166.5,159.2,188.1,171.9};

//	s[0] = {"佐藤","",""};
//	s[1] = {"","",""};
//	s[2] = {"","",""};
//	s[3] = {"","",""};
//	s[4] = {"","",""};

	for (int num = 0; num <name.length; num++) {
		System.out.println("氏名："	+	name[num]	+
							"年齢："+	age[num]	+
							"身長："	+ hight[num]);

	}

	}

}
