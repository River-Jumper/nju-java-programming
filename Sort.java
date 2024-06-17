import java.util.Arrays;

public class Sort {

    public static int[] sort(int[] numbers) {
        int l = numbers.length;

        for (int i = 0; i < l - 1; i++) {
            int cur = i;
            for (int j = i + 1; j < l; j++) {
                if (numbers[j] < numbers[cur]) {
                    cur = j;
                }
            }

            int tmp = numbers[i];
            numbers[i] = numbers[cur];
            numbers[cur] = tmp;
        }

        return numbers;
    }

    public static void main(String[] args) {
        int[] arr = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        System.out.println(Arrays.toString(sort(arr)));
    }
}
