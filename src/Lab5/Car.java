package Lab5;

public class Car implements Comparable<Car> {
    int startPosition;
    int speed;

    public Car(int startPosition, int speed) {
        this.startPosition = startPosition;
        this.speed = speed;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public float getSpeed() {
        return speed;
    }

    public void Print(){
        System.out.println("Начальное положение: " + startPosition +
                "\nСкорость: " + speed + " км/ч");
    }

    @Override
    public int compareTo(Car obj) {
        if (this.speed > obj.getSpeed())
            return -1;
        else if(this.speed == obj.getSpeed())
            return 0;
        else
            return 1;
    }
}
