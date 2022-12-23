package Y2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Day12 {

    int minSteps = Integer.MAX_VALUE;

    public void solve() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/linafang/IdeaProjects/adventofcode/src/Day12/test.txt"));
        int row = lines.size();
        int col = lines.get(0).length();
        char[][] grid = new char[row][col];
        int[] start = new int[2];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = lines.get(i).charAt(j);

                if (grid[i][j] == 'E') {
                    grid[i][j] = 'z';
                    start = new int[]{i, j};
                }
            }
        }

        int res = solve(grid, start, new HashSet<>());
        System.out.println(res);
    }

    private int solve(char[][] grid, int[] start, Set<String> visited) {
        int step = 0;

        LinkedList<int[]> q = new LinkedList<>();
        q.add(start);
        while (q.size() > 0) {
            int cnt = q.size();
            while (cnt > 0) {
                int[] cur = q.removeFirst();
                int i = cur[0], j = cur[1];
                char curChar = grid[i][j];

                if (grid[i][j] == 'a') return step;

                int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
                for (int[] dir : dirs) {
                    int newi = i + dir[0], newj = j + dir[1];
                    if (newi >= 0 && newi < grid.length && newj >= 0 && newj < grid[0].length) {
                        if (visited.contains(newi + "," + newj)) {
                            continue;
                        }

                        if (grid[newi][newj] >= curChar - 1) {
                            q.addLast(new int[]{newi, newj});
                            visited.add(newi + "," + newj);
                        }
                    }
                }
                cnt--;
            }
            step++;
        }
        return 0;
    }
}
