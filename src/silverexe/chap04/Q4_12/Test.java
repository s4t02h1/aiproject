package silverexe.chap04.Q4_12;

public class Test {
  public static void main(String[] args) {
    int[] ary = {1, 2, 3, 4, 5};
    int i = 1;
    while(i < ary.length) {
      i++;
      if(i == 2) {
        continue;
      }
      System.out.print(i + " ");
      if(i > 3) {
        break;
      }
    }
  }
}
