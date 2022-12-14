package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day14 {

    boolean moving = true;
    int maxLeft = Integer.MAX_VALUE;
    int maxRight = 0;
    int maxDown = 0;
    int cnt = 0;

    public void solve() throws IOException {
        int[][] grid = readInput();

        while (moving) {
            fallSand(grid);
        }

        System.out.println(cnt);
    }

    private void fallSand(int[][] grid) {
        int i = 0;
        int j = 500;

        int[] dir = checkMoveDirection(grid, i, j);
        while (dir != null && dir[0] != -1) {
            // need to move
            i = i + dir[0];
            j = j + dir[1];
            dir = checkMoveDirection(grid, i, j);
        }

        if (dir == null) {
            // resting
            grid[i][j] = 1;
            System.out.println(i + "," + j);
            cnt++;
            return;
        }

        if (dir[0] == -1) {
            moving = false;
        }
    }

    private int[] checkMoveDirection(int[][] grid, int i, int j) {
        // if already on boundary
        if (i == maxDown || j == maxLeft || j == maxRight) {
            return new int[]{-1, 0};
        }

        // try move down
        if (grid[i + 1][j] != 1) {
            return new int[]{1, 0};
        }

        // try move left down
        if (grid[i + 1][j - 1] != 1) {
            return new int[]{1, -1};
        }

        // try move right down
        if (grid[i + 1][j + 1] != 1) {
            return new int[]{1, 1};
        }

        return null;
    }

    private int[][] readInput() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/linafang/IdeaProjects/adventofcode/src/Day14/test.txt"));
        List<List<int[]>> formattedPts = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] pts = line.split("->");
            List<int[]> row = new ArrayList<>();
            for (String pt : pts) {
                String[] res = pt.trim().split(",");
                int right = Integer.parseInt(res[0]);
                int down = Integer.parseInt(res[1]);
                maxRight = Math.max(right, maxRight);
                maxDown = Math.max(down, maxDown);
                maxLeft = Math.min(maxLeft, right);
                row.add(new int[]{down, right});
            }
            formattedPts.add(row);
        }
        System.out.println(maxLeft + " " + maxRight + " " + maxDown);
        maxDown = maxDown + 1;
        maxRight = maxRight + 1;
        maxLeft = maxLeft - 1;
        int[][] grid = new int[maxDown + 1][maxRight + 1];

        for (int i = 0; i < formattedPts.size(); i++) {
            for (int j = 0; j < formattedPts.get(i).size() - 1; j++) {
                int cur = j;
                int next = j + 1;
                int fx = formattedPts.get(i).get(cur)[0];
                int fy = formattedPts.get(i).get(cur)[1];
                int tx = formattedPts.get(i).get(next)[0];
                int ty = formattedPts.get(i).get(next)[1];
                grid[fx][fy] = 1;
                grid[tx][ty] = 1;
                if (fx == tx) {
                    // same row
                    while (fy != ty) {
                        grid[fx][fy] = 1;
                        if (fy > ty) {
                            fy--;
                        } else {
                            fy++;
                        }
                    }
                } else {
                    // same col
                    while (fx != tx) {
                        grid[fx][fy] = 1;
                        if (fx < tx) {
                            fx++;
                        } else {
                            fx--;
                        }
                    }
                }
            }
        }
        return grid;
    }
}
