package Lab20;

public class Main {
    public static void main(String[] args) {
        ChildrenBuggies buggies = new ChildrenBuggies();
        buggies.setFillStrategy(new StandartFillStrategy());

        buggies.fill();

    }

}
