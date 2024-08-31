import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Scene {
    static final int num = 256;
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        Set<Color> colorSet = new HashSet<>();
        while (colorSet.size() < num) {
            int r = random.nextInt(0, 255);
            int g = random.nextInt(0, 255);
            int b = random.nextInt(0, 255);
            Color color = new Color(r, g, b);
            colorSet.add(color);
        }

        Goblin[] goblins = new Goblin[num];
        int indexGoblins = 0;
        for (Color color : colorSet) {
            goblins[indexGoblins] = new Goblin(color.r, color.g, color.b);
            indexGoblins++;
        }

        Line line = new Line(num);
        for (Goblin goblin : goblins) {
            line.putIntoLine(goblin);
        }

        Sorter sorter = new QuickSort();

        Basilis theBasilis = Basilis.getTheBasilis();
        theBasilis.setLine(line);
        theBasilis.setSorter(sorter);
        String log = theBasilis.lineUp();

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("jumperResult.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }
    private static class Color {
        public int r;
        public int g;
        public int b;
        public Color(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
}