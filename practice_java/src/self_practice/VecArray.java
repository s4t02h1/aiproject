import java.util.Vector;
import java.awt.Button;
class VecArray
{
	public static void main(String[] args)
	{
		int i = 1;
		int[] ia = new int[10];
		ia[0] = i;
		
		Button[] ba = new Button[10];
		ba[0] = new Button(" ");
		Vector v = new Vector(10);
		v.add(new Button(" "));
		v.add(i);
		v.add(new Integer(i));
	}
}