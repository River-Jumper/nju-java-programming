package com.anish.mazegenerator;


public class MazeObj {

    public static final int WALL = 0;
    public static final int SPACE = 1;
    public static final int LIMIT = 2;

    private byte[][] data;
    private final int width;
    private final int height;
    private final java.util.Random rand = new java.util.Random();

    private final int entranceX = 1;
    private final int entranceY = 2;
    private final int exitX;
    private final int exitY;

    public int getEntranceX() {return entranceX; }
    public int getEntranceY() {return entranceY; }
    public int getExitX() {return exitX; }
    public int getExitY() {return exitY; }

    public MazeObj(int width, int height) {
        this.width = width;
        this.height = height;
        data = new byte[width][];
        exitY = width - 3;
        exitX = height - 2;
    }

    public byte[][] getMaze() {
        return this.data;
    }

    private void carve(int x, int y) {

        final int[] upx = {1, -1, 0, 0};
        final int[] upy = {0, 0, 1, -1};

        int dir = rand.nextInt(4);
        int count = 0;
        while (count < 4) {
            final int x1 = x + upx[dir];
            final int y1 = y + upy[dir];
            final int x2 = x1 + upx[dir];
            final int y2 = y1 + upy[dir];
            if (data[x1][y1] == WALL && data[x2][y2] == WALL) {
                data[x1][y1] = SPACE;
                data[x2][y2] = SPACE;
                carve(x2, y2);
            } else {
                dir = (dir + 1) % 4;
                count += 1;
            }
        }
    }

    public void generate() {
        for (int x = 0; x < width; x++) {
            data[x] = new byte[height];
            for (int y = 0; y < height; y++) {
                data[x][y] = WALL;
            }
        }

        for (int x = 0; x < width; x++) {
            data[x][0] = SPACE;
            data[x][height - 1] = SPACE;
        }

        for (int y = 0; y < height; y++) {
            data[0][y] = SPACE;
            data[width - 1][y] = SPACE;
        }

        data[entranceY][entranceX + 1] = SPACE;
        carve(entranceY, entranceX + 1);

        data[entranceY][entranceX - 1] = SPACE;
        data[width - 3][height - 2] = SPACE;

        for (int x = 0; x < width; x++) {
            data[x][0] = LIMIT;
            data[x][height - 1] = LIMIT;
        }

        for (int y = 0; y < height; y++) {
            data[0][y] = LIMIT;
            data[width - 1][y] = LIMIT;
        }
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (data[x][y] == WALL) {
                    System.out.print("[]");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        MazeObj m = new MazeObj(39, 23);
        m.generate();
        m.print();

    }


}



