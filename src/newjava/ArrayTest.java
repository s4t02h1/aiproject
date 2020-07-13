package newjava;
import java.awt.Button;

class ArrayTest
{
	public static final int arraySize = 3;
	public static void main(String[] args)
	{
		int[] ia = new int[arraySize];
		for (int i = 0; i < arraySize; i++)
			System.out.println("int array initially " + ia[i]);
			
		for (int i = 0; i < arraySize; i++)
			ia[i] = i + 1;
			
		for (int i = 0; i < arraySize; i++)
			System.out.println("int array now " + ia[i]);
			
		Button[] oa = new Button[arraySize];
		for (int i = 0; i < arraySize; i++)
			System.out.println("Button array initially " + oa[i]);
			
		for (int i = 0; i < arraySize; i++)
			oa[i] = new Button("button " + (i+1));
			
		for (int i = 0; i < arraySize; i++)
			System.out.println("Button array now " + oa[i]);
	}
}