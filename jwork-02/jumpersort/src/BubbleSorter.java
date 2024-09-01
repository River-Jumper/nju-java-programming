

public class BubbleSorter implements Sorter {

    private int[] a;

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    @Override
    public String sortAndGetPlan() {
        sort();
        return getPlan();
    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += i + "<->" + j + "\n";
    }

    private String plan = "";

    private void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    private String getPlan() {
        return this.plan;
    }

}

