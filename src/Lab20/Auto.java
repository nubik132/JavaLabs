package Lab20;

public class Auto {
    FillStrategy fillStrategy;

    public Auto(FillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }


    public void fill() {
        fillStrategy.fill();
    }

    public void gas() {
        System.out.println("���� ������");
    }

    public void stop() {
        System.out.println("��������!");
    }

    public void setFillStrategy(FillStrategy fillStrategy) {
        this.fillStrategy = fillStrategy;
    }
}
