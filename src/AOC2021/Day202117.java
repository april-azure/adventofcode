package AOC2021;

import java.util.List;

public class Day202117 {
    public void solve(List<String> lines) {
        int[] range = new int[4];
        String line = lines.get(0);
        line = line.replace("target area: x=", "").replace(" y=", "");
        String[] parts = line.split(",");
        String[] xvalues = parts[0].split("\\.\\.");
        String[] yvalues = parts[1].split("\\.\\.");
        range = new int[]{Integer.parseInt(xvalues[0]),
                Integer.parseInt(xvalues[1]),
                Integer.parseInt(yvalues[0]),
                Integer.parseInt(yvalues[1])};
        System.out.println(range[0] + " " + range[1] + " " + range[2] + " " + range[3]);

        int height = Integer.MIN_VALUE;
        int cnt = 0;
        for (int x = -500; x <= 500; x++) {
            for (int y = -500; y <= 500; y++) {
                int h = calHeight(x, y, range);
                if (h!=Integer.MIN_VALUE) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private int calHeight(int x, int y, int[] range) {
        int xDis = 0;
        int yDis = 0;
        int maxH = Integer.MIN_VALUE;
        boolean canHit = false;
        while (yDis > range[2]) {
            xDis += x;
            yDis += y;
            maxH = Math.max(maxH, yDis);
            // if in range
            if (xDis >= range[0] && xDis <= range[1] && yDis >= range[2] && yDis <= range[3]) {
                canHit= true;
            }
            x = x > 0 ? x - 1 : 0;
            y = y - 1;
        }
        if (canHit) {
            return maxH;
        }
        return Integer.MIN_VALUE;
    }
}
