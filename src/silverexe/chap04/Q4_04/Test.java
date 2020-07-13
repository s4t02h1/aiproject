package silverexe.chap04.Q4_04;

public class Test {
  public static void main(String[] a) {
    int i = 2;
    Outer:
    if ( i < 5 ) {
      System.out.println("i: " + i);
      i++;
//      continue Outer;
    }
  }
}
