class Sample5_8 {
  int instanceVal;             //CX^XΟ
  static int staticVal;        //staticΟ

  int methodA() { return instanceVal; }           //@ OK
  int methodB() { return staticVal; }             //A OK
  //static int methodC() { return instanceVal; }  //B NG
  static int methodD() { return staticVal; }      //C OK
  static int methodE() {                          //D OK
    Sample5_8 obj = new Sample5_8();  
    return obj.instanceVal;    
  }
}
