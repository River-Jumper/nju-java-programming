import java.util.Arrays;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        int[] elements = new int[10];
        for (int index = 0; index < elements.length; index++) {
            //Double.parseDouble(String.format("%.2f", random.nextDouble(20.0)));
            elements[index] = random.nextInt(20);
        }
        QuickSort quickSort = new QuickSort();
        quickSort.load(elements);
        quickSort.sortAndGetPlan();
        elements = quickSort.getElements();
        System.out.printf(Arrays.toString(elements));
    }
}
