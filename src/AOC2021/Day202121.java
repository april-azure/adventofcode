package AOC2021;

import java.util.List;

public class Day202121 {

    int s1 = 0, s2 = 0;
    int roll = 1;
    int rollCnt = 0;

    public void solve(List<String> lines) {
        int p1 = Integer.parseInt(lines.get(0).split(":")[1].trim());
        int p2 = Integer.parseInt(lines.get(1).split(":")[1].trim());
        System.out.println(p1 + " " + p2);


        while (true) {
            // p1
            p1 = playStep(p1);
            if (p1 == 0) p1 = 10;
            s1 += p1;
            if (hasWinning()) {
                break;
            }

            p2 = playStep(p2);
            if (p2 == 0) p2 = 10;
            s2 += p2;
            if (hasWinning()) {
                break;
            }
        }
        System.out.println(Math.min(s1, s2) * rollCnt);
    }

    private boolean hasWinning() {
        return s1 >= 1000 || s2 >= 1000;
    }

    private int playStep(int p) {
        int step = (roll * 3 + 3 + p) % 10;
        rollCnt += 3;
        roll = roll + 3;
        return step;
    }
}
