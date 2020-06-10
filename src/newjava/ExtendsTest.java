package newjava;

import com.se.X;
import com.se.Y;

class Y extends X {

	@Override
	public void hello() {
		System.out.println("こんにちは");
	}

}
public class ExtendsTest extends X {

	public static void main(String[] args) {
		Y y = new Y();
		y.talk();
		y.hello();
		y.bye();

	}

}
