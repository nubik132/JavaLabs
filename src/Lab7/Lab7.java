package Lab7;

import java.util.Scanner;

public class Lab7
{
    public static void main(String[] args)
    {
        System.out.println("�� ����� ������ ������ �_�");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        try {
            System.out.println(text.charAt(0));
            System.out.println("��, ��, �� ������ �� ������");
        }
        catch (Exception ex) {
            System.out.println("           ________\n" +
                                "      _ /      __  \\\n" +
                                "    |   |     |__|  |\n" +
                                "    |   |           |\n" +
                                "    | _ |    _____  | \n" +
                                "         [_]      [_]\n");
        }
        finally {
            System.out.println("����� ���������");
        }
    }
}
