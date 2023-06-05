package Lab8;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Lab8 {
    public static void main(String[] args){
        byte[] bytes1;
        byte[] bytes2;
        byte[] bytes3;
        byte[][] allBytes;
        String spaceString = "";
        for (int i = 0; i < 17; i++)
            spaceString += " ";
        byte[] space = spaceString.getBytes(StandardCharsets.US_ASCII);
        try(FileInputStream fis1 = new FileInputStream("E:/nums/nums.txt");
            FileInputStream fis2 = new FileInputStream("E:/nums/nums2.txt");
            FileInputStream fis3 = new FileInputStream("E:/nums/nums3.txt");
            FileOutputStream fos = new FileOutputStream("E:/nums/output.txt", true)) {
            bytes1 = new byte[fis1.available()];
            fis1.read(bytes1);
            bytes2 = new byte[fis2.available()];
            fis2.read(bytes2);
            bytes3 = new byte[fis3.available()];
            fis3.read(bytes3);
            allBytes = new byte[][] {bytes1, bytes2, bytes3};
            for (int i = 0; i < 4;i++) {
                fos.write(124);
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++)
                        fos.write(allBytes[j][5 * i + k]);
                    fos.write(space);
                }
                fos.write(124);
                fos.write(10);
            }
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
