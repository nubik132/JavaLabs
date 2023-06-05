package Lab8_9;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Lab8 {
    public static void main(String[] args) {
        String str = "";
        try (FileReader fileReader = new FileReader("E://text.txt")) {
            int c;
            while ((c = fileReader.read()) != -1) {
                str += (char) c;
            }}
        catch(IOException ex)
            {
                System.out.println("Ошибка");
            }
            File file = new File("E://text1.txt");
           String[] words = str.toLowerCase().replaceAll("[,.!:?]", "").split(" ");

        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false);
             Writer writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);)
        {
           for (int i = 0; i < words.length; i++) {
               if (words[i].charAt(0) == 'c' || words[i].charAt(0) =='C')
               {
                    writer.append(words[i] + " ");
               }
            }
           writer.flush();
        }
       catch(IOException ex)
       {
           System.out.println("Ошибка");
       }
}
}

