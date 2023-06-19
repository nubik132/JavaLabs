package Lab19;

public class Main {
    public static void main(String[] args) {
        // ������� �� ����
        Service taro = new Divination("����", 1000);
        Service chakra = new Chakra(taro);
        Service aura = new Aura(chakra);

        // � ����� ���������
        System.out.println(aura.getPrice());

        // ��������
        Service horoscope = new Horoscope("������������ ��������", 1000);
        Service channenling = new Channeling(horoscope);
        Service avatar = new Avatar(channenling);

        // � ����� ���������
        System.out.println(avatar.getPrice());
    }

}
