package Lab6_9;
import java.util.Arrays;

public class Lab6 {
    public static void main(String[] args) {
        int count = 1;
        String text = "������� ������� ���������!";
        String[] words = text.toLowerCase().replaceAll("[,.!:?]", "").split("\\s");
        Arrays.sort(words);
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals(words[i - 1])) {
                do {
                    count++;
                    i++;
                } while(i < words.length && words[i].equals(words[i - 1]));
            }
            System.out.println("����� \"" + words[i - 1] + "\" ����������� � ������: " + count + " ���.");
            count = 1;
        }
    }
}
