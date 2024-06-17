package mysort;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Scene {
    public static void main(String[] args) {
        //随机序列
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 255; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        Matrix matrix = new Matrix(16);
        Line[] lines = new Line[16];
        for (int i = 0; i < 16; i++) {
            lines[i] = new Line(16);
            matrix.put(lines[i], i);
        }

        //获取颜色
        try {
            File imageFile = new File("c256.png");
            BufferedImage image = ImageIO.read(imageFile);

            int widthInterval = image.getWidth() / 16;
            int heightInterval = image.getHeight() / 16;

            for (int i = 0; i < 256; i++) {
                int x = i / 16, y = i % 16;
                int pixel = image.getRGB(x * widthInterval, y * heightInterval);
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;
                int num = numbers.get(i);
                lines[num / 16].put(new Monster(red, green, blue), num % 16);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Snake snake = Snake.getSnake();
        Sorter sorter = new MixedSorter();
        snake.setSorter(sorter);
        //排序并记录
        String log = snake.matrixSort(matrix);
        try {
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter("result.txt"));
            writer.write(log);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
