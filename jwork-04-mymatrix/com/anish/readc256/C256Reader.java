package com.anish.readc256;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class C256Reader {
    File c256File;
    Color[][] colors = new Color[16][16];
    Color[] colorRow = new Color[256];

    public C256Reader() {
        try {
            c256File = new File("com/anish/readc256/c256.png");
            BufferedImage c256 = ImageIO.read(c256File);

            int width = c256.getWidth();
            int height = c256.getHeight();

            int xIncrement = 35, xInitial = 18;
            int yIncrement = 27, yInitial = 13;

            for (int y = yInitial; y < height; y += yIncrement) {
                for (int x = xInitial; x < width; x += xIncrement) {
                    int pixel = c256.getRGB(x, y);

                    int red = (pixel >> 16) & 0xff;
                    int blue = pixel & 0xff;
                    int green = (pixel >> 8) & 0xff;

                    this.colors[(y - yInitial) / yIncrement][((x - xInitial)) / xIncrement] = new Color(red, blue, green);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Color[][] getColors() {
        return colors;
    }

    public Color[] getColorInRow() {
        if (colorRow[0] != null) {
            return colorRow;
        }
        int indexColorRow = 0;
        for (int indexRow = 0; indexRow < 16; indexRow++) {
            for (int indexColumn = 0; indexColumn < 16; indexColumn++) {
                colorRow[indexColorRow] = colors[indexRow][indexColumn];
                indexColorRow++;
            }
        }
        return colorRow;

    }

    public static void main(String[] args) {
        C256Reader c256Reader = new C256Reader();
        Color[] colorRow = c256Reader.getColorInRow();

        for (int x = 0; x < 256; x++) {
            int r = colorRow[x].getRed();
            int g = colorRow[x].getGreen();
            int b = colorRow[x].getBlue();
            if (x % 16 != 15) {
                System.out.printf("(r:%d, g:%d, b:%d)\t", r, g, b);
            } else {
                System.out.printf("(r:%d, g:%d, b:%d)\n", r, g, b);
            }
        }

    }
}
