package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sample7_18 {

	public static void main(String[] args) {
		List<Integer> data =
				new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));

		 /* //匿名クラスで実装した場合
	    data.removeIf(new Predicate<Integer>() {
	      public boolean test(Integer i) {
	        if(i % 2 != 0) {
	          return true;
	        }else{
	          return false;
	        }
	      }
	    });
	    */
		data.removeIf((Integer i) -> { return i % 2 != 0;});

		System.out.println(data);
	}

}
