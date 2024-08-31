package example;

public class QuickSorter implements Sorter{
    private int[] elements;
    private StringBuilder plan;
    @Override
    public void load(int[] elements) {
        this.elements = elements;
    }

    @Override
    public void sort() {
        plan = new StringBuilder();
        quickSort(0, elements.length);
    }

    @Override
    public String getPlan() {
        return this.plan.toString();
    }
    public int[] getElements() {
        return elements;
    }
    //[q, r)
    private void quickSort(int p, int r) {
        //one element
        if (p >= r - 1) {
            return;
        }
        //more than one element
        int q = partition(p, r);
        quickSort(p, q);
        quickSort(q + 1, r);
    }
    //[q, r)
    private int partition(int p, int r) {
        int partitionIndex = p;
        for (int index = p + 1; index < r; index++) {
            if (elements[index] < elements[partitionIndex]) {
                swap(index, partitionIndex + 1);
                swap(partitionIndex + 1, partitionIndex);
                partitionIndex++;
            }
        }
        return partitionIndex;
    }
    private void swap(int indexA, int indexB) {
        int temp = elements[indexA];
        elements[indexA] = elements[indexB];
        elements[indexB] = temp;
        //plan += "" + a[i] + "<->" + a[j] + "\n";
        plan.append(elements[indexA]);
        plan.append("<->");
        plan.append(elements[indexB]);
        plan.append("\n");
    }
}
