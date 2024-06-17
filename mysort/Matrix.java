package mysort;

public class Matrix {
    Line[] lines;

    public Matrix(int length) {
        lines = new Line[length];
    }

    public void put(Line line, int i) {
        if (i < lines.length && i >= 0) {
            lines[i] = line;
        }
    }

    @Override
    public String toString() {
        StringBuilder lineString = new StringBuilder();
        for (Line line : lines){
            lineString.append(line.toString() + "\n");
        }
        return lineString.toString();
    }

    public Monster[][] toMatrix() {
        Monster[][] monsters = new Monster[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            monsters[i] = lines[i].toArray();
        }
        return monsters;
    }


}
