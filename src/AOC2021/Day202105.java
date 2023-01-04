package AOC2021;

import java.util.List;

public class Day202105 {
    public void solve(List<String> lines) {
        int[][] numbers = new int[lines.size()][];
        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line = line.replace(" ", "");
            String[] parts = line.split("->");
            int[] p1 = format(parts[0]);
            int[] p2 = format(parts[1]);
            numbers[i] = new int[]{p1[0], p1[1], p2[0], p2[1]};
            maxX = Math.max(p1[0], maxX);
            maxX = Math.max(p2[0], maxX);
            maxY = Math.max(p1[1], maxY);
            maxY = Math.max(p2[1], maxY);
        }
        System.out.println("maxx " + maxX);
        System.out.println("maxy " + maxY);

        int[][] map = new int[maxX + 1][maxY + 1];

        for (int[] line : numbers) {
            mark(map, line);
        }

        int cnt = 0;
        for (int[] row : map) {
            for (int i : row) {
                if (i > 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private void mark(int[][] map, int[] line) {
        int x1 = line[0], y1 = line[1], x2 = line[2], y2 = line[3];
        if (x1 == x2 || y1 == y2) {
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[x][y]++;
                }
            }
        } else {
            int x = x1, y = y1;
            while (x != x2 || y != y2) {
                map[x][y]++;

                if (x2 > x) {
                    x++;
                } else {
                    x--;
                }
                if (y2 > y) {
                    y++;
                } else {
                    y--;
                }
            }
            map[x][y]++;
        }
    }

    private int[] format(String str) {
        String[] s = str.split(",");
        return new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])};
    }
}
