class Sample1_5 {
  public static void main(String[] args) {
    float x1 = 3_.1415F;     //�@：NG（コンパイルエラー）
    float x2 = 3._1415F;     //�A：NG（コンパイルエラー）
    long x3 = 999_99_9999_L; //�B：NG（コンパイルエラー）
    int x4 = _52;            //�C：NG（コンパイルエラー）
    int x5 = 5_2;            //�D：OK
    int x6 = 52_;            //�E：NG（コンパイルエラー）
    int x7 = 5_______2;      //�F：OK
    int x8 = 0_x52;          //�G：NG（コンパイルエラー）
    int x9 = 0x_52;          //�H：NG（コンパイルエラー）
    int x10 = 0x5_2;         //�I：OK 
    int x11 = 0_52;          //�J：OK 
  }
}