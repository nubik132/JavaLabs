package Lab3;

public class Car {
    private String mark, color, number;
    private int seats;
    private double mileage, gas;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        if (seats <= 8)
        this.seats = seats;
    }

    public double getMileage() {
        return mileage / 1000;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

    public static void PrintInfo(Car car){
        System.out.println("Марка: " + car.getMark());
        System.out.println("Цвет: " + car.getColor());
        System.out.println("Номер: " + car.getNumber());
        System.out.println("Мест: " + car.getSeats());
        System.out.println("В баке " + car.getGas() + " л. бензина");
        System.out.println("Пробег: " + car.getMileage());
    }

}
