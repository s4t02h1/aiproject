package silverexe.chap04.Q4_05;

public class Test {
  public static void main(String[] args) {
    int[][] ary = {{1, 2}, {3, 4}};
    for(int x = 0; x < ary.length; x++) {
      for(int y = 0; y < ary[x].length; y++) {

    	  ary[x][y] = ary[x][y] * ary[x][y];

    	  System.out.print(ary[x][y] + " ");
      }
    }
  }
}
