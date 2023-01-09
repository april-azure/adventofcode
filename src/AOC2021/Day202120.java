package AOC2021;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day202120 {
    int[][] dirs = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 0}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public void solve(List<String> lines) {
        String algo = lines.get(0);

        int rows = lines.size() - 2;
        int cols = lines.get(2).length();

        int[][] map = new int[rows + 300][cols + 300];
        for (int i = 2; i < lines.size(); i++) {
            for (int j = 0; j < cols; j++) {
                if (lines.get(i).charAt(j) == '#') {
                    map[i + 150][j + 150] = 1;
                }
            }
        }

        for (int i = 0; i < 50; i++) {
            map = applyAlgo(map, algo);
            print(map);
        }

        count(map);
    }

    private void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] == 1 ? "#" : ".");
            }
            System.out.println();
        }
    }

    private void count(int[][] map) {
        int cnt = 0;
        for (int i = 52; i < map.length - 52; i++) {
            for (int j = 52; j < map[0].length - 52; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private int[][] applyAlgo(int[][] map, String algo) {
        int[][] newmap = new int[map.length][map[0].length];
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[0].length - 1; j++) {
                String s = "";
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    s += map[x][y] == 1 ? '1' : '0';
                }
                int index = convert(s);
                newmap[i][j] = algo.charAt(index) == '#' ? 1 : 0;
            }
        }
        return newmap;
    }

    private int convert(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res = res * 2 + (str.charAt(i) - '0');
        }
        return res;
    }

//    private int[] format(String str) {
//        String[] pts = str.split(",");
//        return new int[]{Integer.parseInt(pts[0]), Integer.parseInt(pts[1])};
//    }
//
//    private String convert(int[] pt) {
//        return pt[0] + "," + pt[1];
//    }
}
