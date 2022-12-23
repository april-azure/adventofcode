package AOC2021;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Day202111 {

    int n = 10;

    public void solve(List<String> lines) {
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = lines.get(i).charAt(j) - '0';
            }
        }

//        int total = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            boolean isAllFlashed = action(grid);
            if (isAllFlashed) {
                System.out.println("step " + (i + 1));
                return;
            }
        }
//        System.out.println(total);
    }

    private boolean action(int[][] grid) {
        int cnt = 0;
        Set<String> visited = new HashSet<>();
        LinkedList<int[]> toFlash = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j]++;
                if (grid[i][j] > 9) {
                    toFlash.addLast(new int[]{i, j});
                    visited.add(format(i, j));
                }
            }
        }

//        print(grid);
        while (toFlash.size() > 0) {
            int[] cur = toFlash.removeFirst();
            int x = cur[0], y = cur[1];
            // flash
            grid[x][y] = 0;
            cnt++;

            // add adjacent
            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {1, 1}, {-1, -1}, {0, 1}, {0, -1}, {-1, 1}, {1, -1}};
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (!inRange(nx, ny)) {
                    continue;
                }
                if (visited.contains(format(nx, ny))) {
                    continue;
                }
                grid[nx][ny]++;
                if (grid[nx][ny] > 9) {
                    toFlash.addLast(new int[]{nx, ny});
                    visited.add(format(nx,ny));
                }
            }

//            print(grid);
        }

        return cnt == 100;
    }

    private boolean inRange(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    private String format(int i, int j) {
        return i + "," + j;
    }

    private void print(int[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
