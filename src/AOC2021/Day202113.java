package AOC2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day202113 {
    public void solve(List<String> lines) {
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        List<Character> foldMode = new ArrayList<>();
        List<Integer> foldPos = new ArrayList<>();
        List<int[]> poses = new ArrayList<>();
        for (String line : lines) {
            if (line.length() > 0 && Character.isDigit(line.charAt(0))) {
                String[] parts = line.split(",");
                int y = Integer.parseInt(parts[0]);
                int x = Integer.parseInt(parts[1]);
                poses.add(new int[]{x, y});
                maxX = Math.max(x, maxX);
                maxY = Math.max(y, maxY);
            } else if (line.length() > 0) {
                line = line.replace("fold along ", "");
                String[] parts = line.split("=");
                char mode = parts[0].charAt(0);
                int pos = Integer.parseInt(parts[1]);
                foldMode.add(mode);
                foldPos.add(pos);
            }
        }
        int[][] map = new int[maxX + 1][maxY + 1];
        for (int[] pos : poses) {
            map[pos[0]][pos[1]] = 1;
        }
        int lastx = maxX;
        int lasty = maxY;

        for (int i = 0; i < foldMode.size(); i++) {
            char mode = foldMode.get(i);
            int pos = foldPos.get(i);
            if (mode == 'x') {
                foldX(map, pos, lastx, lasty);
                lasty = pos;
            } else {
                foldY(map, pos, lastx, lasty);
                lastx = pos;
            }
        }
        for (int i = 0; i <= lastx; i++) {
            for (int j = 0; j <= lasty; j++) {
                if (map[i][j] == 1) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private void foldX(int[][] map, int foldX, int x, int y) {
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (map[i][j] == 1 && j > foldX) {
                    int dis = j - foldX;
                    map[i][foldX - dis] = 1;
                }
            }
        }
    }

    private void foldY(int[][] map, int foldY, int x, int y) {
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (map[i][j] == 1 && i > foldY) {
                    int dis = i - foldY;
                    map[foldY - dis][j] = 1;
                }
            }
        }
    }
}
