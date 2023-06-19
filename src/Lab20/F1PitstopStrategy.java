package Lab20;

public class F1PitstopStrategy implements FillStrategy {

    @Override
    public void fill() {
        System.out.println("Заправляем бензин только после всех остальных процедур пит-стопа!");
    }
}
