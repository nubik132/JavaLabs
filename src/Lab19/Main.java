package Lab19;

public class Main {
    public static void main(String[] args) {
        // Гадание на Таро
        Service taro = new Divination("Таро", 1000);
        Service chakra = new Chakra(taro);
        Service aura = new Aura(chakra);

        // И общая стоимость
        System.out.println(aura.getPrice());

        // Гороскоп
        Service horoscope = new Horoscope("Персональный гороскоп", 1000);
        Service channenling = new Channeling(horoscope);
        Service avatar = new Avatar(channenling);

        // И общая стоимость
        System.out.println(avatar.getPrice());
    }

}
