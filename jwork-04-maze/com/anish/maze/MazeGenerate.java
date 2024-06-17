package com.anish.maze;

import java.util.Arrays;
import java.util.Random;

public class MazeGenerate {

    private int width;
    private int height;
    private char[][] maze;

    private int entranceX;
    private int entranceY;
    private int exitX;
    private int exitY;

    public MazeGenerate(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new char[width][height];
        initializeMaze();
    }

    private void initializeMaze() {
        for (int i = 0; i < width; i++) {
            Arrays.fill(maze[i], '#'); // Initialize the maze with walls
        }
    }

    public void generateMaze() {
        entranceX = 0; // 入口在迷宫的左边
        entranceY = randomOddNumber(1, height - 2); // 随机选择入口的垂直位置

        exitX = width - 1; // 出口在迷宫的右边
        exitY = randomOddNumber(1, height - 2); // 随机选择出口的垂直位置

        maze[entranceX][entranceY] = ' '; // 设置入口
        maze[exitX][exitY] = ' '; // 设置出口

        divide(0, 0, width - 1, height - 1);
    }

    private void divide(int x1, int y1, int x2, int y2) {
        if (x2 - x1 < 2 || y2 - y1 < 2) {
            return;
        }

        int horizontal = randomEvenNumber(y1 + 1, y2 - 1);
        int vertical = randomEvenNumber(x1 + 1, x2 - 1);

        for (int i = x1; i <= x2; i++) {
            maze[i][horizontal] = ' ';
        }

        for (int j = y1; j <= y2; j++) {
            maze[vertical][j] = ' ';
        }

        int passageX = randomOddNumber(x1, x2);
        int passageY = randomOddNumber(y1, y2);
        maze[passageX][passageY] = ' ';

        divide(x1, y1, vertical - 1, horizontal - 1);
        divide(vertical + 1, y1, x2, horizontal - 1);
        divide(x1, horizontal + 1, vertical - 1, y2);
        divide(vertical + 1, horizontal + 1, x2, y2);
    }

    private int randomEvenNumber(int min, int max) {
        Random random = new Random();
        int num = random.nextInt((max - min) / 2 + 1) * 2 + min;
        return num;
    }

    private int randomOddNumber(int min, int max) {
        Random random = new Random();
        int num = random.nextInt((max - min + 1) / 2) * 2 + 1 + min;
        return num;
    }

    public void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(maze[j][i]);
            }
            System.out.println();
        }
    }

    public int[][] getMaze() {
        int[][] mazeInt = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                mazeInt[j][i] = maze[j][i] == '#' ? 1 : 0;

            }
        }
        return mazeInt;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public static void main(String[] args) {
        int width = 40; // Adjust the width of the maze
        int height = 40; // Adjust the height of the maze
        MazeGenerate mazeGenerator = new MazeGenerate(width, height);
        mazeGenerator.generateMaze();
        mazeGenerator.printMaze();
    }
}
