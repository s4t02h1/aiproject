package newjava;


class Car {
    public void drive() {
        System.out.print("Driving a car ");
    }
}
public class Taxi extends Car {
    public void drive() throws RuntimeException {
        super.drive();
        if(true)
           throw new RuntimeException();
       System.out.print("Driving a taxi ");
   }
   public static void main(String[] args) {
       new Taxi().drive();
   }
}