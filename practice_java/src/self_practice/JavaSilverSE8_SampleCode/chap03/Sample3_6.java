class Sample3_6 {
  public static void main(String[] args) {
    String s = "abcdefa";
    System.out.println("charAt()      : " + s.charAt(2));
    System.out.println("endsWith()    : " + s.endsWith("fa"));
    System.out.println("indexOf()     : " + s.indexOf('c'));
    System.out.println("replace()     : " + s.replace('a','z'));
    System.out.println("substring()   : " + s.substring(2));
    System.out.println("toUpperCase() : " + s.toUpperCase());
  }
}
