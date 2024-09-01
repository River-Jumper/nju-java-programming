import readc256.C256Reader;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Scene {
    static int length;
    static final int width = 16;
    public static void main(String[] args) throws IOException {
        length = width * width;

        //Goblin, Line and Basilis
        Goblin[] goblins = makeGoblinLine();
        Line line = new Line(length);
        for (Goblin goblin : goblins) {
            line.putIntoLine(goblin);
        }
        Basilis theBasilis = Basilis.getTheBasilis();
        theBasilis.setLine(line);

        //3 Sorter
        Sorter quickSorter = new QuickSort();
        Sorter matrixSorter = new MatrixSorter();
        Sorter bubbleSorter = new BubbleSorter();

        //4 sorterLog, 2 in line, 2 in matrix
        //一次运行只能输出一种日志（因为每次的日志输出会导致goblins的顺序改变）
        //String quickSorterLineLog = sortGoblinLine(quickSorter, theBasilis);
        //String bubbleSorterLineLog = sortGoblinLine(bubbleSorter, theBasilis);
        //String quickSorterMatrixLog = sortGoblinMatrix(quickSorter, theBasilis);
        String matrixSorterMatrixLog = sortGoblinMatrix(matrixSorter, theBasilis);


        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("jumperResult.txt"));
        writer.write(matrixSorterMatrixLog);
        writer.flush();
        writer.close();

    }

    private static String sortGoblinLine(Sorter sorter, Basilis theBasilis) {
        theBasilis.setSorter(sorter);
        String log = theBasilis.lineUpToALine();

        return log;
    }

    private static String sortGoblinMatrix(Sorter sorter, Basilis theBasilis) {
        theBasilis.setSorter(sorter);
        String log = theBasilis.lineUpToMatrix();

        return log;
    }

    private static Goblin[] makeGoblinLine() {
        length = width * width;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            arrayList.add(index);
        }
        Collections.shuffle(arrayList);
        Color[] sortedColor = makeSortedColors(width);

        Goblin[] goblinsLine = new Goblin[length];

        for (int index = 0; index < length; index++) {
            int rank = arrayList.get(index);
            Color theColor = sortedColor[rank];
            goblinsLine[index] = new Goblin(theColor.getRed(), theColor.getGreen(), theColor.getBlue(), rank);
        }

        return goblinsLine;
    }

    private static Goblin[][] makeGoblinMatrix() {
        length = width * width;
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            arrayList.add(index);
        }
        Collections.shuffle(arrayList);
        Color[] sortedColor = makeSortedColors(width);

        Goblin[][] goblinsMatrix = new Goblin[width][width];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                int rank = arrayList.get(16 * y + x);
                Color theColor = sortedColor[rank];
                goblinsMatrix[y][x] = new Goblin(theColor.getRed(), theColor.getGreen(), theColor.getBlue(), rank);
            }
        }

        return goblinsMatrix;
    }

    private static Color[] makeSortedColors(int width) {
        assert (width <= 16);

        C256Reader c256Reader = new C256Reader();
        java.awt.Color[][] colors = c256Reader.getColors();

        java.awt.Color[] sortedColors = new java.awt.Color[width * width];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                sortedColors[y * width + x] = colors[y][x];
            }
        }

        return sortedColors;
    }
}