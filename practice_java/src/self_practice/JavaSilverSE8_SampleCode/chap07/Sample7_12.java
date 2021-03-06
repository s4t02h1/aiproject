import java.time.LocalDate;

public class Sample7_12 {
  public static void main(String[] args) {
    LocalDate date = LocalDate.of(2015, 10, 1);
    System.out.println("date     : " + date);
    System.out.println("3日後    : " + date.plusDays(3));
    System.out.println("5ケ月後  : " + date.plusMonths(5));
    System.out.println("2週間後  : " + date.plusWeeks(2));
    System.out.println("10年後   : " + date.plusYears(10));
    System.out.println("date     : " + date);
  }
}