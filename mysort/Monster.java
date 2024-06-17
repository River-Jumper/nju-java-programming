package mysort;

import mysort.Line.Position;

import java.util.ArrayList;

public class Monster {
    private final int r;
    private final int g;
    private final int b;

    private Position position;

    private static int count = 0;
    private static ArrayList<Monster> countArray = new ArrayList<Monster>();
    private final int myRank;

    Monster(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.myRank = count++;
        countArray.add(this);
    }

    public static Monster getMonsterByRank(int rank) {

        for (Monster monster : countArray) {
            if (monster.rank() == rank) {
                return monster;
            }
        }
        return null;

    }

    public int rank() {
        return this.myRank + 1;
    }

    @Override
    public String toString() {
        String s = "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    ";
        if (this.rank() < 10){
            s += " " + this.rank() + " ";
        }else if (this.rank() < 100){
            s += " " + this.rank();
        }else{
            s += this.rank();
        }
        s += "  \033[0m";
        return s;
    }


    public void setPosition(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(Monster another) {
        Position p = another.position;
        this.position.setMonster(another);
        p.setMonster(this);
    }


    public int getValue() {
        return this.rank();
    }
}
