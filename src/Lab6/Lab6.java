package Lab6;

import java.util.Scanner;

public class Lab6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        char[] symbols = {'á', 'â', 'ã', 'ä', 'æ', 'ç', 'é', 'ê', 'ë', 'ì', 'í', 'ï', 'ð', 'ñ', 'ò', 'ô', 'õ', 'ö', '÷', 'ø', 'ù'};
        text = scanner.nextLine();
        for (char symbol : symbols) {
            int index = text.indexOf(symbol);
            if (index != -1) {
                int lastIndex = text.lastIndexOf(symbol);
                if (index <= lastIndex && !IsInThirdWord(text, symbol, index, lastIndex)) {
                    System.out.print(symbol);
                    System.out.print(' ');
                }
            }
        }
    }

    private static boolean IsInThirdWord(String text, char symbol, int index, int lastIndex){
        for (int k = index + 1; k < lastIndex + 1; k++){
            if (text.charAt(k) == ' '){
                for (int i = k + 1; i < lastIndex; i++){
                    if (text.charAt(i) == symbol){
                        for (int j = i + 1; j < lastIndex; j++){
                            if (text.charAt(j) == ' '){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
