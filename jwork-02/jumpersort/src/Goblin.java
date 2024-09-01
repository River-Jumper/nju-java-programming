import java.text.DecimalFormat;

public class Goblin implements Linable {
    private final int r;
    private final int g;
    private final int b;
    private final int rank;
    private int position;
    public Goblin(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rank = (int)(0.299 * r + 0.587 * g + 0.114 * b);
    }
    public Goblin(int r, int g, int b, int rank) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rank = rank;
    }
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("000");
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + df.format(getValue()) + "  \033[0m";
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getValue() {
        return this.rank;
    }
}
