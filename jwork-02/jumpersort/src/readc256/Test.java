package readc256;

import java.util.HashSet;

public class Test {
    public static void main(String args[]) {
        String data = new String("0\t27\t53\t54\t80\t81\t107\t108\t134\t161\t188\t215\t241\t242\t268\t269\t322\t349\t376\t402\t403");
        String[] elements = data.split("\t");
        HashSet<Integer> set = new HashSet<>();

        for (String element : elements) {
            set.add(Integer.parseInt(element));
        }

        for (int index = 13; index < 428; index += 27) {
            if (set.contains(index)){
                System.out.println("Choose fail!");
                System.out.println(index);
                return;
            }
        }
        System.out.println("Well done!");
    }
}
