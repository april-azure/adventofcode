package AOC2021;

import java.util.ArrayList;
import java.util.List;

public class Day202101 {
    public void solve(List<String> lines) {
        List<Integer> list = new ArrayList<>();
        for (String str : lines) {
            list.add(Integer.parseInt(str));
        }

        int cnt = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i-1)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
