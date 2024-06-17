package mysort;

import java.util.Objects;

public class Snake {
    public static Snake snake;
    private Sorter sorter;

    public static Snake getSnake() {
        if (snake == null) {
            snake = new Snake();
        }
        return snake;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public String lineUp(Line line) {

        StringBuilder log = new StringBuilder();

        if (sorter == null) {
            return null;
        }

        Monster[] monsters = line.toArray();
        int[] ranks = new int[monsters.length];

        for (int i = 0; i < monsters.length; i++) {
            ranks[i] = monsters[i].getValue();
        }

        sorter.load(ranks);
        sorter.sort();

        String[] sortSteps = this.parsePlan(sorter.getPlan());

        for (String step : sortSteps) {
            this.execute(step);
            System.out.println(line.toString());
            log.append(line.toString());
            log.append("\n[frame]\n");
        }

        return log.toString();

    }

    public String matrixSort(Matrix matrix){
        StringBuilder log = new StringBuilder();

        if (sorter == null) {
            return null;
        }

        Monster[][] monsters = matrix.toMatrix();
        int m = monsters[0].length;
        int[] ranks = new int[monsters.length * m];

        for (int i = 0; i < monsters.length; i++) {
            for (int j = 0; j < m; j++){
                ranks[i * m + j] = monsters[i][j].getValue();
            }
        }

        sorter.load(ranks);
        sorter.sort();

        String[] sortSteps = this.parsePlan(sorter.getPlan());

        for (String step : sortSteps) {
            this.execute(step);
            System.out.println(matrix.toString());
            log.append(matrix.toString());
            log.append("\n[frame]\n");
        }

        return log.toString();

    }


    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step) {
        String[] couple = step.split("<->");
        Objects.requireNonNull(Monster.getMonsterByRank(Integer.parseInt(couple[0])))
                .swapPosition(Objects.requireNonNull(Monster.getMonsterByRank(Integer.parseInt(couple[1]))));
    }

}
