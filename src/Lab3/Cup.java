package Lab3;

public class Cup extends Dish {
    private boolean mugHandle;
    public Cup(int size, boolean breakable, boolean mugHandle) {
        super(size, breakable);
        this.mugHandle = mugHandle;
    }

    public void Drink(){
        System.out.println("�� ������!");
    }

    @Override
    public void PrintInfo(){
        System.out.println("��������: " + material + "\n" + "����� " + size + "\n" +
                "�����������? " + breakable + "\n" + "���� �����?" + mugHandle + "\n");
    }
}
