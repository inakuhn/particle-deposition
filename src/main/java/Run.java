import color.CalculationOfColor;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Ina Kuhn on 21.01.2017.
 */
public class Run extends JFrame {
    static int size = 256;
    public volatile int[][] field = new int[size][size];
    public volatile int[][] result = new int[size][size];

    Run() {

        int vulkanos = 500;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size / 2 && j == size / 2) {
                    field[i][j] = 0;
                    result[i][j] = 0;
                } else {
                    result[i][j] = 0;
                    field[i][j] = 0;
                }
            }
        }

        int index = 0;
        Random randomGenerator = new Random();
        while (index < vulkanos) {
            int partN = randomGenerator.nextInt(10000);
            int i = randomGenerator.nextInt(size);
            int j = randomGenerator.nextInt(size);
            if (field[i][j] == 0) {
                field[i][j] = partN;
                index++;
            }

            System.out.println("particlesArray[" + i + "][" + j + "] = " + partN + "\n");

        }


        System.out.println("--------------Start----------");
        long startTime = System.nanoTime();
        while (istDiferent()) {
            atualize();
            distribute();
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time : " + (totalTime / 1000000000.00));
        System.out.println("-----------END-------");
        setSize(size, size);
        setTitle("An Empty Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        repaint();
    }

    private void atualize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = field[i][j];
            }
        }

    }

    private void distribute() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {


// index left Up neighbour
                if ((i - 1) >= 0 && (j - 1) >= 0) {
                    //if there is a high difference from 2 particle can be moved
                    if (field[i][j] >= (field[(i - 1)][(j - 1)] + 2)) {
                        field[(i - 1)][(j - 1)]++;
                        field[i][j]--;
                    }
                }
                //index up neighbour
                if ((j - 1) >= 0) {
                    //if there is a high difference from 2 particle can be moved
                    if (field[i][j] >= (field[i][j - 1] + 2)) {
                        field[i][j - 1]++;
                        field[i][j]--;
                    }
                }
                // index rigthUp neighbour
                if ((i + 1) < size && (j - 1) >= 0) {
                    if (field[i][j] >= (field[i + 1][j - 1] + 2)) {
                        field[i + 1][j - 1]++;
                        field[i][j]--;

                    }
                }
                // index left
                if ((i - 1) >= 0) {
                    //if there is a high difference from 2 particle can be moved
                    if (field[i][j] >= (field[i - 1][j] + 2)) {
                        field[i - 1][j]++;
                        field[i][j]--;

                    }
                }
                // index rigth neighbour
                if ((i + 1) < size) {
                    if (field[i][j] >= (field[i + 1][j] + 2)) {
                        field[i + 1][j]++;
                        field[i][j]--;

                    }
                }
                // index rightDown neighbour
                //int indLeftDown = (i - 1) + (j + 1) * jiS;
                if ((i - 1) >= 0 && (j + 1) < size) {
                    if (field[i][j] >= (field[i - 1][j + 1] + 2)) {
                        field[i - 1][j + 1]++;
                        field[i][j]--;
                    }
                }

                //index down neighbour
                //int indDown = (i)+(j + 1) * jiS;
                if ((j + 1) < size) {
                    if (field[i][j] >= (field[i][j + 1] + 2)) {
                        field[i][j + 1]++;
                        field[i][j]--;
                    }
                }


                // index rigthDown neighbour
               // int indRightDown = (i + 1) + (j + 1) * jiS;
                if ((i + 1) < size && (j + 1) < size) {
                    if (field[i][j] >= (field[i + 1][j + 1] + 2)) {
                        field[i + 1][j + 1]++;
                        field[i][j]--;
                    }
                }

            }
        }
    }


    public static void main(String[] args) {
        Run m = new Run();

    }

    @Override
    public void paint(Graphics g) {
        CalculationOfColor.getCalculation().setMax(getMax());
        CalculationOfColor.getCalculation().setMin(getMin());
        int squareSize = 1;
        int xStart = squareSize;
        int yStart = squareSize;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g.setColor(CalculationOfColor.getCalculation()
                        .getColorForValue(field[i][j]));
                g.fillRect(xStart, yStart, squareSize, squareSize);
                yStart = yStart + squareSize;
            }
            xStart = xStart + squareSize;
            yStart = squareSize;
        }

    }


    public boolean istDiferent() {
        boolean isDiferent = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] != result[i][j])
                    isDiferent = true;
            }
        }
        return isDiferent;
    }

    int getMax() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                max = Math.max(field[i][j], max);
            }
        }
        return max;
    }

    int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                min = Math.min(field[i][j], min);
            }
        }
        return min;
    }
}
