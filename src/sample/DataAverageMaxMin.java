package sample;

public class DataAverageMaxMin {

	public static void main(String[] args) {

		double [] hight = {172.5,168.0,163.7,155.6,191.8};
		double sum  = 0.0;

		for(int num = 0; num < hight.length; num++) {
			sum += hight[num];
		}

		System.out.println("平均身長："	+ (sum / hight.length));

		double max = hight[0];
		double min = hight[0];
		for(int num = 0; num < hight.length; num++) {
			if (max < hight[num]) {
					 max = hight[num++] ;
//					hight[num++] = hight[num];
//					hight[num] = max;
				}
			 }
		for(int num1 = 0; num1 < hight.length; num1++) {
			 if (min > hight[num1]) {
			 	min = hight[num1] ;
//				hight[num++] = hight[num];
//				hight[num] = min;
			 }
		}System.out.println( "max" + max + "min" + min);
	}
}
