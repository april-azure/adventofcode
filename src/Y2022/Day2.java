package Y2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    public void solve(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("X", 0);
        scoreMap.put("Y", 3);
        scoreMap.put("Z", 6);

        int score = 0;
        for (String line : lines) {
            String[] pts = line.split(" ");
            int p1 = map.get(pts[0]);

            String res = pts[1];
            score += scoreMap.get(res);
            if (res.equals("Y")) {
                score += p1;
            } else if (res.equals("X")) {
                // need to lose
                int p2 = p1 - 1;
                if (p2 == 0) {
                    p2 = 3;
                }
                score += p2;
            } else {
                int p2 = p1 + 1;
                if (p2 == 4) {
                    p2 = 1;
                }
                score += p2;
            }
        }
        System.out.println(score);
    }

    // which ds to use??
    // 1: p1 win
    // 0: draw
    // -1: p1 lose
    private int compare(int p1, int p2) {
        if (p1 == p2) {
            return 0;
        }

        if (Math.abs(p1 - p2) == 1) {
            return p1 - p2;
        }

        if (p1 < p2) {
            return 1;
        } else {
            return -1;
        }
    }
}
