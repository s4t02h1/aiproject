class Sample1_5 {
  public static void main(String[] args) {
    float x1 = 3_.1415F;     //①：NG（コンパイルエラー）
    float x2 = 3._1415F;     //②：NG（コンパイルエラー）
    long x3 = 999_99_9999_L; //③：NG（コンパイルエラー）
    int x4 = _52;            //④：NG（コンパイルエラー）
    int x5 = 5_2;            //⑤：OK
    int x6 = 52_;            //⑥：NG（コンパイルエラー）
    int x7 = 5_______2;      //⑦：OK
    int x8 = 0_x52;          //⑧：NG（コンパイルエラー）
    int x9 = 0x_52;          //⑨：NG（コンパイルエラー）
    int x10 = 0x5_2;         //⑩：OK 
    int x11 = 0_52;          //⑪：OK 
  }
}