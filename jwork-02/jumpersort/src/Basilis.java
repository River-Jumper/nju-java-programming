//蛇妖类
public class Basilis {
    private static Basilis theBasilis;

    private Line line;
    private Sorter sorter;

    private Basilis() {

    }

    public static Basilis getTheBasilis() {
        if (theBasilis == null) {
            theBasilis = new Basilis();
        }
        return theBasilis;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    //排成一行
    public String lineUpToALine() {
        if (line == null || sorter == null) {
            return null;
        }
        StringBuilder log = new StringBuilder();
        sorter.load(line.toArray());
        String plan = sorter.sortAndGetPlan();
        String[] steps = parsePlan(plan);

        toALine(steps, log);

        return log.toString();
    }
    //排成矩阵
    public String lineUpToMatrix() {
        if (line == null || sorter == null) {
            return null;
        }
        StringBuilder log = new StringBuilder();
        sorter.load(line.toArray());
        String plan = sorter.sortAndGetPlan();
        String[] steps = parsePlan(plan);

        toAMatrix(steps, log);

        return log.toString();
    }
    //排成一行
    private void toALine(String[] steps, StringBuilder log) {
        for (String step : steps) {
            this.execute(step);
            System.out.println(line.toString());
            log.append(line.toString());
            log.append("\n[frame]\n");
        }
    }
    //排成矩阵，要求size必须是一个平方数
    private void toAMatrix(String[] steps, StringBuilder log) {
        for (String step : steps) {
            this.execute(step);
            System.out.println(line.toString());
            log.append(line.matrixToString());
            log.append("\n[frame]\n");
        }
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step) {
        String[] couple = step.split("<->");
        int positionA = Integer.parseInt(couple[0]);
        int positionB = Integer.parseInt(couple[1]);
        line.swapPosition(line.getByPosition(positionA), line.getByPosition(positionB));
    }
}
