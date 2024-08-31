import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[20];
        for (int index = 0; index < array.length; index++) {
            array[index] = random.nextInt(100);
        }

        MergeSort mergeSort = new MergeSort(array);
        mergeSort.sort();
        array = mergeSort.getArray();

        for (int integer : array) {
            System.out.printf("%d ", integer);
        }
    }

    private static class MergeSort {
        private int[] array;

        public MergeSort(int[] array) {
            this.array = array;
        }

        private static int[] merge(int[] arrayA, int[] arrayB) {
            int[] mergeArray = new int[arrayA.length + arrayB.length];
            for (int indexA = 0, indexB = 0; indexA < arrayA.length ||
                    indexB < arrayB.length; ) {
                if (indexA == arrayA.length) {
                    mergeArray[indexA + indexB] = arrayB[indexB];
                    indexB++;
                } else if (indexB == arrayB.length) {
                    mergeArray[indexA + indexB] = arrayA[indexA];
                    indexA++;
                } else if (arrayA[indexA] < arrayB[indexB]) {
                    mergeArray[indexA + indexB] = arrayA[indexA];
                    indexA++;
                } else {
                    mergeArray[indexA + indexB] = arrayB[indexB];
                    indexB++;
                }
            }
            return mergeArray;
        }

        private static int[] divideAndMerge(int[] array) {
            if (array.length == 1) {
                return array;
            } else {
                int mid = (array.length + 1) / 2;
                int[] arrayA = Arrays.copyOfRange(array, 0, mid);
                int[] arrayB = Arrays.copyOfRange(array, mid, array.length);
                return merge(divideAndMerge(arrayA), divideAndMerge(arrayB));
            }
        }

        public void sort() {
            this.array = divideAndMerge(this.array);
        }

        public int[] getArray() {
            return array;
        }
    }
}