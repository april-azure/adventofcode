package AOC2021;

import java.util.*;

public class Day202109 {
    public void solve(List<String> lines) {
        int[][] grid = readInput(lines);
        List<int[]> basinPts = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
                boolean isLowPt = true;
                for (int[] dir : dirs) {
                    int newi = i + dir[0], newj = j + dir[1];
                    boolean isValid = isValid(grid, newi, newj) ? grid[newi][newj] > grid[i][j] : true;
                    isLowPt = isLowPt && isValid;
                }
                if (isLowPt) {
                    basinPts.add(new int[]{i, j});
                }
            }
        }

        int[] res = new int[basinPts.size()];
        int i = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int[] basin : basinPts) {
            int size = findSize(grid, basin, visited);
            System.out.println(size);
            res[i++] = size;
        }
        Arrays.sort(res);
        int len = res.length;
        int finalRes = res[len - 1] * res[len - 2] * res[len - 3];
        System.out.println(finalRes);
    }

    private int findSize(int[][] grid, int[] basin, boolean[][] visited) {
        if (visited[basin[0]][basin[1]]) {
            return 0;
        }
        LinkedList<int[]> pts = new LinkedList<>();
        pts.add(basin);
        visited[basin[0]][basin[1]] = true;
        int size = 0;
        while (pts.size() > 0) {
            int[] cur = pts.removeFirst();
            size++;

            int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int i = cur[0] + dir[0], j = cur[1] + dir[1];
                if (isValid(grid, i, j) && !visited[i][j] && grid[i][j] != 9) {
                    visited[i][j] = true;
                    pts.add(new int[]{i, j});
                }
            }
        }
        return size;
    }

    private boolean isValid(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    public int[][] readInput(List<String> lines) {
        int[][] grid = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                grid[i][j] = Integer.parseInt(lines.get(i).charAt(j) + "");
            }
        }
        return grid;
    }
}
