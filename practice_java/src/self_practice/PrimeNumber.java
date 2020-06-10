import java.io.*;
import java.util.ArrayList;

public class PrimeNumber{
	public static void main(String[] args){
	
	int maxn = 100;
	ArrayList<Boolean> pn;
	pn = new ArrayList<Boolean>(maxn);
//	pn.setResize(maxn, 1);
	
	for(int i = 2; i < maxn; i++){
		if(pn[i]){
			System.out.println(i);
			for(int j = i; j < maxn; j += i){
				pn[j] = 0;
		}
		}
	}
	break;
	}
}