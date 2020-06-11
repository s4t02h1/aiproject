class Sample3_7 {
  public static void main(String[] args) {
    StringBuilder sb1 = new StringBuilder("Ab Cdefge");
      System.out.println("append()   : " + sb1.append("XYZ"));
    StringBuilder sb2 = new StringBuilder("Ab Cdefge");
      System.out.println("insert()   : " + sb2.insert(2,"ZZ"));
    StringBuilder sb3 = new StringBuilder("Ab Cdefge");
      System.out.println("reverse()  : " + sb3.reverse());
    StringBuilder sb4 = new StringBuilder("Ab Cdefge");
      sb4.setCharAt(3,'Z');
      System.out.println("setCharAt(): " + sb4);
    StringBuilder sb5 = new StringBuilder("Ab Cdefge");
      System.out.println("delete()   : " + sb5.delete(0,5));
    StringBuilder sb6 = new StringBuilder("Ab Cdefge");
      System.out.println("replace()  : " +
                             sb6.replace(3,sb6.length(),"YYY"));
    StringBuilder sb7 = new StringBuilder("Ab Cdefge");
      System.out.println("substring(): " + sb7.substring(5,7));
    StringBuilder sb8 = new StringBuilder("Ab Cdefge");
      System.out.println("substring(): " + sb8.substring(5));
  }
}
