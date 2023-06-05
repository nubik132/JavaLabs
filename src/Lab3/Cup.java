package Lab3;

public class Cup extends Dish {
    private boolean mugHandle;
    public Cup(int size, boolean breakable, boolean mugHandle) {
        super(size, breakable);
        this.mugHandle = mugHandle;
    }

    public void Drink(){
        System.out.println("Вы выпили!");
    }

    @Override
    public void PrintInfo(){
        System.out.println("Материал: " + material + "\n" + "Объём " + size + "\n" +
                "Разбивается? " + breakable + "\n" + "Есть ручка?" + mugHandle + "\n");
    }
}
