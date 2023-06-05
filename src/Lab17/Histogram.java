package Lab17;

import Lab17.DBDisplayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Histogram extends JFrame{
    final int height = 400;
    final int offset = 130;
    final int columnWidth = 40;
    int[] rowsCounts;
    Histogram(int[] rowsCounts){
        super("Histogram");
        this.rowsCounts = rowsCounts;
        setLayout(null);
        setSize(650, height);
        setVisible(true);
        setBackground(Color.WHITE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){
        int k = (height - 50) / Arrays.stream(rowsCounts).max().getAsInt();
        for (int i = 0; i < rowsCounts.length; i++){
            g.setColor(Color.orange);
            int x = offset * i + columnWidth;
            int y = height - k * rowsCounts[i];
            g.fillRect(x,
                    y,
                    columnWidth,
                    k * rowsCounts[i] - 50);
            g.drawChars(DBDisplayer.tables[i].toCharArray(), 0, DBDisplayer.tables[i].length(), x, height - 20);
            g.setColor(Color.BLACK);
            char[] chars = Integer.toString(rowsCounts[i]).toCharArray();
            g.drawChars(chars, 0, chars.length, x + 15, y + 15);
        }
    }
}
