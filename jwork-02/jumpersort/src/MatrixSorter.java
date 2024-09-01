public class MatrixSorter implements Sorter{
    private int[][] elements;
    private String plan = "";
    private int width;
    private int height;
    @Override
    public void load(int[] elements) {
        int length = elements.length;
        this.width = (int)Math.sqrt(length);
        this.height = width;

        this.elements = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.elements[y][x] = elements[width * y + x];
            }
        }
    }

    @Override
    public String sortAndGetPlan() {
        Point rangePoint = new Point(width - 1, height - 1);
        while (!rangePoint.equals(new Point(0, 0))) {
            Point maxPoint = getMaxByRangeFrom0ToPoint(rangePoint);
            moveFromTo(maxPoint, rangePoint);
            if (rangePoint.getX() == 0) {
                rangePoint.setXAndY(width - 1, rangePoint.getY() - 1);
            }
            else {
                rangePoint.setX(rangePoint.getX() - 1);
            }
            //displayElement();
        }

        return plan;
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setXAndY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void swapWithPoint(Point point) {
            int tempX = point.getX();
            int tempY = point.getY();
            point.setXAndY(x, y);
            this.setXAndY(tempX, tempY);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // 检查是否为同一对象
            if (o == null || getClass() != o.getClass()) return false; // 检查类型
            Point point = (Point) o;
            return this.x == point.x && this.y == point.y; // 比较x和y的值
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }


    private int getValueByPoint(Point point) {
        return elements[point.getY()][point.getX()];
    }

    //point1(x1, y1) swaps with point(x2, y2)
    //point(x1, y1) closed to point(x2, y2)
    private void swap(Point point1, Point point2) {
        int temp;
        temp = getValueByPoint(point1);
        elements[point1.getY()][point1.getX()] = getValueByPoint(point2);
        elements[point2.getY()][point2.getX()] = temp;

        plan += pointCoordinateToLine(point1) + "<->" + pointCoordinateToLine(point2) + "\n";
    }

    private int pointCoordinateToLine(Point point) {
        return width * point.getY() + point.getX();
    }

    //move point1(x1, y1) to point2(x2, y2)
    //point1(x1, y1) change to (x2, y2)
    //point2(x2, y2) change to (x2 -1, y2) or (x2, y2 - 1)
    private void moveFromTo(Point point1, Point point2) {
        //move (x1, y1) to (0, y1)
        int x1 = point1.getX(), y1 = point1.getY(), x2 = point2.getX(), y2 = point2.getY();
        while (!point1.equals(new Point(0, point1.getY()))) {
            Point swapPoint = new Point(point1.getX() - 1, point1.getY());
            swap(point1, swapPoint);
            point1.swapWithPoint(swapPoint);
        }
        //move (0, y1) to (0, y2)
        while (!point1.equals(new Point(0, point2.getY()))) {
            Point swapPoint = new Point(point1.getX(), point1.getY() + 1);
            swap(point1, swapPoint);
            point1.swapWithPoint(swapPoint);
        }
        //move (0, y2) to (x2, y2)
        while (!point1.equals(point2)) {
            Point swapPoint = new Point(point1.getX() + 1, point1.getY());
            swap(point1, swapPoint);
            point1.swapWithPoint(swapPoint);
        }
    }

    //search all the element before point and return the minimum element
    private Point getMaxByRangeFrom0ToPoint(Point point) {
        Point maxPoint = new Point(0, 0);
        int maxElement = elements[0][0];
        int rangeX = point.getX(), rangeY = point.getY();
        for (int indexY = 0; indexY <= rangeY; indexY++) {
            if (indexY == rangeY) {
                for (int indexX = 0; indexX <= rangeX; indexX++) {
                    if (elements[indexY][indexX] > maxElement) {
                        maxPoint.setXAndY(indexX, indexY);
                        maxElement = elements[indexY][indexX];
                    }
                }
            }
            else {
                for (int indexX = 0; indexX < width; indexX++) {
                    if (elements[indexY][indexX] > maxElement) {
                        maxPoint.setXAndY(indexX, indexY);
                        maxElement = elements[indexY][indexX];
                    }
                }
            }

        }

        return maxPoint;
    }

    public int[][] getElements() {
        return elements;
    }


}
