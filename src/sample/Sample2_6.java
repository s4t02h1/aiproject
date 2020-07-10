package sample;

import java.util.ArrayList;

class Sample2_6 {
	public static void main(String[] args) {
		/*String[] array;			//�z����g�p�����ꍇ
		array = new String[1];
		array[0] = " �c�� ";
		String name = array[0];
		System.out.println(name + "�F" + array.length); */
		
		ArrayList<String> array;	//ArrayList�N���X���g�p�����ꍇ
		array = new ArrayList<String>(3);
		array.add(" �c�� ");
		String name = array.get(0);
		System.out.println(name + "�F" + array.size());
	}
}