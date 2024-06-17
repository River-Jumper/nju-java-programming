package mysort;

public class MixedSorter implements Sorter {
    private int[] a;
    private String plan = "";

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += a[i] + "<->" + a[j] + "\n";
    }

    @Override
    public void sort() {
        quickSort(0, a.length - 1);
    }

    private void quickSort(int l, int r) {
        if (r - l < 17) {
            insertSort(l, r);
            return;
        }

        int i = l, j = r;
        int pivot = a[(l + r) / 2];
        while (i <= j) {
            while (a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (l < j) {
            quickSort(l, j);
        }
        if (i < r) {
            quickSort(i, r);
        }
    }

    private void insertSort(int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int j = i;
            for (int k = j - 1; k >= l; k--) {
                if (a[k] > a[j]) {
                    swap(k, j);
                    j = k;
                } else {
                    break;
                }
            }
        }
    }


    @Override
    public String getPlan() {
        return this.plan;
    }
}
