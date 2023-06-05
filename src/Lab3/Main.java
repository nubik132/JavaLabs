package Lab3;

public class Main {
    public static void main(String[] args) {
        Car car= new Car();
        car.setMark("Mazda RX-7");
        car.setColor("Белый");
        car.setNumber("яв08а3");
        car.setGas(14.2);
        car.setMileage(5000000);
        car.setSeats(2);
        car.setSeats(9);
        Car.PrintInfo(car);
    }
}
