package Lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Lab5 {
    public static void main(String[] args){
        Random random = new Random();
        ArrayList<Car> cars = new ArrayList<Car>();

        int carsCount = 3;
        int overtakes = 0;
        int startPos;

        for (int i = 0; i < carsCount; i++){
            cars.add(new Car(i + 1, random.nextInt(140) + 60));
            cars.get(i).Print();
        }

        Collections.sort(cars);

        for (int i = 0; i < carsCount; i++) {
            startPos = cars.get(i).getStartPosition();
            if (startPos > i)
                overtakes += startPos - i - 1;
        }
        System.out.println("Обгонов " + overtakes);
    }
}
