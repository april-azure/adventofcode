import java.util.ArrayList;
import java.util.List;

public class Day20 {

    int[] items;
    List<String> list = new ArrayList<>();

    public void solve(List<String> lines) {
        items = new int[lines.size()];
        int zeroIndex = 0;
        for (int i = 0; i < lines.size(); i++) {
            int number = Integer.parseInt(lines.get(i));
            items[i] = number;
            list.add(hash(i, number));
            if (number == 0) {
                zeroIndex = i;
            }
        }

        int MOD = lines.size() - 1;

        for (int i = 0; i < items.length; i++) {
            int pos = list.indexOf(hash(i, items[i]));
            int step = items[i];
            int newIndex = (pos + step) % MOD;
            if (newIndex < 0) {
                newIndex += MOD;
            }
            list.remove(pos);
            list.add(newIndex, hash(i, items[i]));

//            print(list);
        }

        findResult(list, zeroIndex);
    }

    private void findResult(List<String> list, int zeroIndex) {
        int pos = list.indexOf(hash(zeroIndex, 0));
        int _1000th = Integer.parseInt(list.get((pos + 1000) % list.size()).split(",")[1]);
        int _2000th = Integer.parseInt(list.get((pos + 2000) % list.size()).split(",")[1]);
        int _3000th = Integer.parseInt(list.get((pos + 3000) % list.size()).split(",")[1]);

        System.out.println(_1000th + _2000th + _3000th);
    }

    private void print(List<String> list) {
        for (String item : list) {
            String[] p = item.split(",");
            System.out.print(p[1] + " ");
        }
        System.out.println();
    }

    private String hash(int i, int j) {
        return i + "," + j;
    }
}
