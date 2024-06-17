package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import com.anish.monsters.BubbleSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[][] monsters;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        monsters = new Monster[10][10];
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                monsters[i][j] = new Monster(
                        new Color(r.nextInt(0, 256),
                                r.nextInt(0,256),
                                r.nextInt(0,256)),
                        numbers.get(i * 10 + j),
                        world);
                if (i % 2 == 0) {
                    world.put(monsters[i][j], 5 + i * 2, 5 + j * 2);
                }else{
                    world.put(monsters[i][j], 5 + i * 2, 28 - (5 + j * 2));
                }
            }
        }

        Monster[] m = new Monster[100];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                m[i * 10 + j] = monsters[i][j];
            }
        }

        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(m);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[][] monsters, String step) {
        String[] couple = step.split("<->");
        Objects.requireNonNull(getBroByRank(monsters, Integer.parseInt(couple[0]))).
                swap(Objects.requireNonNull(getBroByRank(monsters, Integer.parseInt(couple[1]))));
    }

    private Monster getBroByRank(Monster[][] monsters, int rank) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (monsters[i][j].getRank() == rank)
                    return monsters[i][j];
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(monsters, sortSteps[i]);
            i++;
        }

        return this;
    }

}
