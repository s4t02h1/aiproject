import java.io.*;
class Sample8_5 {
  public static void main(String[] args) {
    //例外処理は任意
    int i = Integer.parseInt("10");

    //例外処理は必須
    FileReader r = new FileReader("Test.txt");
  }
}
