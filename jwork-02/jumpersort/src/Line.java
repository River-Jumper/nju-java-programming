public class Line {
    private final Linable[] linables;
    private final int size;
    private int currentSize;
    public Line(int size) {
        this.size = size;
        this.currentSize = 0;
        this.linables = new Linable[size];
    }
    public void putIntoLine(Linable linable) {
        if (this.currentSize < this.size) {
            this.linables[currentSize] = linable;
            linable.setPosition(currentSize);
            currentSize++;
        }
    }
    public Linable getByPosition(int position) {
        if (currentSize > position) {
            return linables[position];
        }
        return null;
    }
    public void swapPosition(Linable linableA, Linable linableB) {
        int temp = linableB.getPosition();

        linables[linableA.getPosition()] = linableB;
        linableB.setPosition(linableA.getPosition());

        linables[temp] = linableA;
        linableA.setPosition(temp);
    }
    public int[] toArray() {
        int[] array = new int[currentSize];
        int index = 0;
        for (Linable linable : this.linables) {
            array[index++] = linable.getValue();
        }
        return array;
    }
    @Override
    public String toString() {
        StringBuilder lineToString = new StringBuilder();
        //lineToString.append("\t");
        for (Linable linable : this.linables) {
            lineToString.append(linable.toString());
        }
        return lineToString.toString();
    }
    //16 * 16 matrix
    public String matrixToString() {
        StringBuilder lineToString = new StringBuilder();
        //lineToString.append("\t");
        int wide = (int)Math.sqrt(size);
        int index = 1;
        for (Linable linable : this.linables) {
            lineToString.append(linable.toString());
            if (index % wide == 0) {
                lineToString.append("\n");
            }
            index++;
        }
        return lineToString.toString();
    }
}
