package Y2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {
    public void solve(List<String> lines) {
        Map<Character, Integer> cnt = new HashMap<>();

        int res = process(cnt, lines.get(0));
        System.out.println(res);
    }

    private int process(Map<Character, Integer> cnt, String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (i < 14) {
                cnt.put(c, cnt.getOrDefault(c, 0) + 1);
                continue;
            }

            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
            char preC = line.charAt(i - 14);
            cnt.put(preC, cnt.get(preC) - 1);
            if (cnt.get(preC) == 0) {
                cnt.remove(preC);
            }

            if (cnt.size() == 14) {
                return i + 1;
            }
        }
        return -1;
    }
}
