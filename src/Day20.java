import java.util.ArrayList;
import java.util.List;

public class Day20 {

    long[] items;
    List<String> list = new ArrayList<>();

    int M = 811589153;

    public void solve(List<String> lines) {
        items = new long[lines.size()];
        int zeroIndex = 0;
        for (int i = 0; i < lines.size(); i++) {
            long number = (long)Integer.parseInt(lines.get(i)) * M;
            items[i] = number;
            list.add(hash(i, number));
            if (number == 0) {
                zeroIndex = i;
            }
        }
        System.out.println(3 * M);
        print(list);
        int MOD = lines.size() - 1;

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < items.length; i++) {
                int pos = list.indexOf(hash(i, items[i]));
                long step = items[i];
                int newIndex = (int)((pos + step) % MOD);
                if (newIndex < 0) {
                    newIndex += MOD;
                }
                list.remove(pos);
                list.add(newIndex, hash(i, items[i]));


            }
        }
        print(list);
        findResult(list, zeroIndex);
    }

    private void findResult(List<String> list, int zeroIndex) {
        int pos = list.indexOf(hash(zeroIndex, 0));
        long _1000th = Long.parseLong(list.get((pos + 1000) % list.size()).split(",")[1]);
        long _2000th = Long.parseLong(list.get((pos + 2000) % list.size()).split(",")[1]);
        long _3000th = Long.parseLong(list.get((pos + 3000) % list.size()).split(",")[1]);

        System.out.println(_1000th + _2000th + _3000th);
    }

    private void print(List<String> list) {
        for (String item : list) {
            String[] p = item.split(",");
            System.out.print(p[1] + " ");
        }
        System.out.println();
    }

    private String hash(int i, long j) {
        return i + "," + j;
    }
}
