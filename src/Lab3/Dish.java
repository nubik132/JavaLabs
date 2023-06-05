package Lab3;

public class Dish {
    protected String material;
    protected int size;
    protected boolean breakable;
    public void tryBreak(){
        if (breakable)
            System.out.println("Сломано((((((((");
        else
            System.out.println("Не сломано!!)!)!))");
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Dish(int size, boolean breakable) {
        this.size = size;
        this.breakable = breakable;
    }

    public void PrintInfo(){
        System.out.println("Материал: " + material + "\n" + "объём " + size + "\n" +
                "Разбивается? " + breakable + "\n");
    }
}
