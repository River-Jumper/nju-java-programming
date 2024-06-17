package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;
import com.anish.maze.MazeGenerate;

public class WorldScreen implements Screen {

    private final World world;
    private final Calabash calabash;
    private final int[][] maze;

    public WorldScreen() {
        world = new World();

        calabash = new Calabash(Color.CYAN, 1, world);

        MazeGenerate mazeGenerator = new MazeGenerate(World.WIDTH, World.HEIGHT);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getMaze();

        for (int i = 0; i < World.HEIGHT; i++) {
            for (int j = 0; j < World.HEIGHT; j++) {
                if (maze[i][j] == 0) {
                    world.put(new Floor(world), i, j);
                } else {
                    world.put(new Wall(world), i, j);
                }
            }
        }

        world.put(calabash, mazeGenerator.getEntranceX(), mazeGenerator.getEntranceY());
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) throws OutOfRangeException, UnreachableException {
        int dx = 0, dy = 0;

        switch (key.getKeyCode()) {
            case KeyEvent.VK_UP:
                dy = -1;
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
                break;
            case KeyEvent.VK_LEFT:
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 1;
                break;
            default:
                break;
        }

        int x = calabash.getX(), y = calabash.getY();

        if (x + dx < 0 || x + dx >= World.WIDTH || y + dy < 0 || y + dy >= World.HEIGHT) {
            throw new OutOfRangeException("Out of range");
        }

        if (maze[x + dx][y + dy] == 1) {
            throw new UnreachableException("Unreachable");
        }

        world.put(new Floor(world), x, y);
        world.put(calabash, x + dx, y + dy);

        return this;
    }

}

