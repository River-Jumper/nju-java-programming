package com.anish.screen;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.*;
import com.anish.mazegenerator.MazeObj;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MazeWorldScreen implements Screen {
    private World world;
    private byte[][] maze;
    private Creature mazeWalker;
    private Thing exit;
    private Boolean reachedExit;

    public MazeWorldScreen() {
        //World.WIDTH must equal to World.HEIGHT
        this.world = new World();
        reachedExit = false;

        this.mazeWalker = new Calabash(Color.CYAN, 0, world);
        this.exit = new Thing(Color.red, (char) 3, world);

        makeMaze(mazeWalker, exit);

    }

    private void makeMaze(Creature creature, Thing exit) {
        MazeObj mazeGenerator = new MazeObj(World.HEIGHT, World.WIDTH);
        mazeGenerator.generate();

        this.maze = mazeGenerator.getMaze();

        for (int y = 0; y < World.WIDTH; y++) {
            for (int x = 0; x < World.WIDTH; x++) {
                if (maze[y][x] == MazeObj.WALL) {
                    this.world.put(new Wall(world), x, y);
                } else if (maze[y][x] == MazeObj.SPACE) {
                    this.world.put(new Floor(world), x, y);
                } else if (maze[y][x] == MazeObj.LIMIT) {
                    this.world.put(new LimitBlock(world), x, y);
                }
            }
        }
        //put entrance and exit
        world.put(creature, mazeGenerator.getEntranceX(), mazeGenerator.getEntranceY());
        world.put(exit, mazeGenerator.getExitX(), mazeGenerator.getExitY());
    }

    private void moveLeft() {
        Thing leftObject = world.get(mazeWalker.getX() - 1, mazeWalker.getY());
        if (!isMovable(leftObject)) {
            return;
        }
        if (leftObject == this.exit) {
            arriveExit();
        }
        else {
            swapThingInWorld(mazeWalker, leftObject);
        }
    }

    private void moveRight() {
        Thing rightObject = world.get(mazeWalker.getX() + 1, mazeWalker.getY());
        if (!isMovable(rightObject)) {
            return;
        }
        if (rightObject == this.exit) {
            arriveExit();
            rightObject = world.get(exit.getX(), exit.getY() + 1);
        }
        else {
            swapThingInWorld(mazeWalker, rightObject);
        }
    }

    private void moveUp() {
        Thing upObject = world.get(mazeWalker.getX(), mazeWalker.getY() + 1);
        if (!isMovable(upObject)) {
            return;
        }
        if (upObject == this.exit) {
            arriveExit();
            upObject = world.get(exit.getX(), exit.getY() + 1);
        }
        else {
            swapThingInWorld(mazeWalker, upObject);
        }
    }

    private void moveDown() {
        Thing downObject = world.get(mazeWalker.getX(), mazeWalker.getY() - 1);
        if (!isMovable(downObject)) {
            return;
        }
        if (downObject == this.exit) {
            arriveExit();
            downObject = world.get(exit.getX(), exit.getY() + 1);
        }
        else {
            swapThingInWorld(mazeWalker, downObject);
        }
    }

    //change exit to Floor and set reachedExit to true
    private void arriveExit() {
        swapThingInWorld(mazeWalker, exit);

        this.reachedExit = true;
        int x = exit.getX(), y = exit.getY();
        this.world.put(new Floor(world), x, y);
        System.out.println("Arrived!");
    }

    //swap two Thing in world scale
    private void swapThingInWorld(Thing a, Thing b) {
        int tempX = a.getX(), tempY = a.getY();
        world.put(a, b.getX(), b.getY());
        world.put(b, tempX, tempY);
    }

    //can't move if closedObject is LimitBlock or Wall
    private boolean isMovable(Thing closedObject) {
        return !(closedObject instanceof LimitBlock || closedObject instanceof Wall);
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
    public Screen respondToUserInput(KeyEvent key) {
        int keyCode = key.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W: {
                moveDown();
                break;
            }
            case KeyEvent.VK_A: {
                moveLeft();
                break;
            }
            case KeyEvent.VK_S: {
                moveUp();
                break;
            }
            case KeyEvent.VK_D: {
                moveRight();
                break;
            }
            default:
                break;
        }
        return this;
    }
}
