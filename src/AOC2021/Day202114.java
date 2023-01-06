package AOC2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day202114 {

    Map<String, String[]> map = new HashMap<>();
    Map<String, Long> cnt = new HashMap<>();
    Map<String, Long> pairCnt = new HashMap<>();

    public void solve(List<String> lines) {
        String cur = lines.get(0);

        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i).replace(" ", "");
            String[] parts = line.split("->");
            String from = parts[0];
            String add = parts[1];
            String[] to = new String[]{
                    add,
                    from.charAt(0) + add,
                    add + from.charAt(1)
            };
            map.put(from, to);
        }

        String line = lines.get(0);
        for (int i = 0; i < line.length(); i++) {
            cnt.put(line.charAt(i) + "", cnt.getOrDefault(line.charAt(i) + "", 0L) + 1);
        }

        for (int i = 0; i < line.length() - 1; i++) {
            String item = line.substring(i, i + 2);
            pairCnt.put(item, pairCnt.getOrDefault(item, 0L) + 1);
        }

        for (int i = 0; i < 40; i++) {
            Map<String, Long> newPairCnt = new HashMap<>(pairCnt);
            for (String pair : pairCnt.keySet()) {
                String[] to = map.get(pair);
                String newChar = to[0];
                cnt.put(newChar, cnt.getOrDefault(newChar, 0L) + pairCnt.get(pair));
                newPairCnt.put(to[1], newPairCnt.getOrDefault(to[1], 0L) + pairCnt.get(pair));
                newPairCnt.put(to[2], newPairCnt.getOrDefault(to[2], 0L) + pairCnt.get(pair));
                newPairCnt.put(pair, newPairCnt.getOrDefault(pair, 0L) - pairCnt.get(pair));
            }
            pairCnt = newPairCnt;
        }

        for (String c : cnt.keySet()) {
            System.out.println(c + " " + cnt.get(c));
        }

        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for (Map.Entry<String, Long> pair : cnt.entrySet()) {
            min = Math.min(min, pair.getValue());
            max = Math.max(max, pair.getValue());
        }
        System.out.println(max);
        System.out.println(min);
        System.out.println(max - min);
    }
}
