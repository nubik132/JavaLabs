package Lab3;

public class Pan extends Dish {
    public Pan(int size, boolean breakable) {
        super(size, breakable);
    }
    private boolean burnt = false;

    public boolean isBurnt() {
        return burnt;
    }

    public void setBurnt(boolean burnt) {
        this.burnt = burnt;
    }
    public void Podgorel(){
        if (burnt)
            System.out.println("Вы сгорели!");
        else
            System.out.println("Не сгорело(((((");
    }
}
