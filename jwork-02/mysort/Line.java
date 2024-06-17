package mysort;

public class Line {

    public Line(int length) {
        this.positions = new Position[length];
    }

    private Position[] positions;

    public void put(Monster monster, int i) {
        if (this.positions[i] == null) {
            this.positions[i] = new Position(null);
        }
        this.positions[i].setMonster(monster);
    }

    public Monster get(int i) {
        if ((i < 0) || (i > positions.length)) {
            return null;
        } else {
            return positions[i].monster;
        }
    }

    static class Position {

        private Monster monster = null;

        Position(Monster monster) {
            this.monster = monster;
        }

        public void setMonster(Monster monster) {
            this.monster = monster;
            if (monster != null) {
                monster.setPosition(this);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder lineString = new StringBuilder("\t");
        for (mysort.Line.Position p : positions) {
            lineString.append(p.monster.toString());
        }
        return lineString.toString();
    }

    public Monster[] toArray() {
        Monster[] monsters = new Monster[this.positions.length];
        for (int i = 0; i < this.positions.length; i++) {
            monsters[i] = this.positions[i].monster;
        }
        return monsters;
    }

}
