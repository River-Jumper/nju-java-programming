package com.anish.screen;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.Goblin;
import com.anish.calabashbros.MatrixBubbleSorter;
import com.anish.calabashbros.World;
import com.anish.readc256.C256Reader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoblinWorldScreen implements Screen {
    private World world;
    private final int goblinMatrixWidth = 16;
    private Goblin[][] goblins = new Goblin[goblinMatrixWidth][goblinMatrixWidth];
    private String[] sortSteps;


    public GoblinWorldScreen() {
        this.world = new World();

        Goblin[][] goblinCopies;

        goblinCopies = makeGoblins(makeSortedColors(goblinMatrixWidth));
        //displayGoblinRank();

        putGoblinsInWorld();

        MatrixBubbleSorter<Goblin> matrixBubbleSorter = new MatrixBubbleSorter<>();
        matrixBubbleSorter.load(goblinCopies);
        matrixBubbleSorter.sort();

        //displayGoblinRank();

        //System.out.print(matrixBubbleSorter.getPlan());
        //System.out.println();

        sortSteps = parsePlan(matrixBubbleSorter.getPlan());
        //displayGoblinRank();
    }

    //change width * width in c256.png to a row of color
    //return a row of color
    private Color[] makeSortedColors(int width) {
        assert (width <= 16);

        C256Reader c256Reader = new C256Reader();
        Color[][] colors = c256Reader.getColors();

        Color[] sortedColors = new Color[width * width];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                sortedColors[y * width + x] = colors[y][x];
            }
        }

        return sortedColors;
    }

    private Goblin[][] makeGoblins(Color[] sortedColors) {
        int length = goblinMatrixWidth * goblinMatrixWidth;

        Goblin[][] goblinCopies = new Goblin[goblinMatrixWidth][goblinMatrixWidth];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            numbers.add(i);
        }
        // make number in numbers out of order
        Collections.shuffle(numbers);

        int numberIndex = 0;
        for (int y = 0; y < goblinMatrixWidth; y++) {
            for (int x = 0; x < goblinMatrixWidth; x++) {
                int rank = numbers.get(numberIndex);
                goblins[y][x] = new Goblin(sortedColors[rank], rank, world);
                goblinCopies[y][x] = new Goblin(sortedColors[rank], rank, world);
                numberIndex++;
            }
        }
        return goblinCopies;
    }

    private void putGoblinsInWorld() {
        int xBegin = (world.getWIDTH() - 2 * (goblinMatrixWidth)) / 2;
        int yBegin = (world.getHEIGHT() - 2 * (goblinMatrixWidth)) / 2;

        for (int y = 0; y < goblinMatrixWidth; y++) {
            for (int x = 0; x < goblinMatrixWidth; x++) {
                world.put(goblins[y][x], xBegin + 2 * x, yBegin + 2 * y);
            }
        }
    }

    private void execute(String step) {
        //single step: x1,y1<->x2,y2
        String[] couple = step.split("<->");
        String p1 = couple[0], p2 = couple[1];
        int x1 = Integer.parseInt(p1.split(",")[0]), y1 = Integer.parseInt(p1.split(",")[1]);
        int x2 = Integer.parseInt(p2.split(",")[0]), y2 = Integer.parseInt(p2.split(",")[1]);
        goblins[y1][x1].swap(goblins[y2][x2]);

        Goblin temp = goblins[y1][x1];
        goblins[y1][x1] = goblins[y2][x2];
        goblins[y2][x2] = temp;

    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void displayGoblinRank() {
        for (int y = 0; y < goblinMatrixWidth; y++) {
            for (int x = 0; x < goblinMatrixWidth; x++) {
                System.out.print(goblins[y][x].getRank());
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    @Override
    public void displayOutput(AsciiPanel terminal) {
        for (int x = 0; x < world.getWIDTH(); x++) {
            for (int y = 0; y < world.getHEIGHT(); y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int stepNum = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (stepNum < sortSteps.length) {
            this.execute(sortSteps[stepNum]);
            stepNum++;
        }
        return this;
    }
}
