package sample;

import java.time.LocalDateTime;

public class Array2 {
	public final static int INITIAL = 10,	//<1>
			GROW_FACOTOR = 2;					//<2>

	public static void main(String[] args) {
		int nDates = 0;
		LocalDateTime[] dates = new LocalDateTime[INITIAL];
		StructureDemo source = new StructureDemo(21);
		LocalDateTime c;
		while ((c=source.getDate()) != null) {


		// if (nDates >= dates.length) {
		//		throw new RuntimeException(
		//			"Two Many Dates! Simplify your life!!"
		//}

		// better: reallocate, making data structure dynamic
		if (nDates >= dates.length) {
			LocalDateTime[] tmp =
					new LocalDateTime[dates.length * GROW_FACTOR];
			System.arraycopy(dates,  0,  tmp, dates.length);
			dates = tmp;	//copies the array reference
			// old array will be garbage collected soon...
		}
		dates[nDates++] = c;
		}
		System.out.println("Final array size = " + dates.length);
	}

}
// end::main[]