import java.util.*;
import java.util.function.*;

public class Sample7_17 {
  public static void main(String[] args) {
    List<String> words = Arrays.asList("Tanaka", "Sato");
    
    /*  //匿名クラスで実装した場合
    words.replaceAll(new UnaryOperator<String>() {
      public String apply(String str) {
        return str.toUpperCase();
      }
    });
    */
    words.replaceAll( (String str) -> { return str.toUpperCase(); } );
    //words.replaceAll( str -> str.toUpperCase() );
   
    System.out.println(words);
  }
}