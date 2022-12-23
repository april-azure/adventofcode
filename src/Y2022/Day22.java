package Y2022;

import java.util.List;

public class Day22 {

    int x, y;
    char[][] grid;
    int[][] range;

    int[][] directions = new int[][]{
            {0, 1}, // R
            {1, 0}, // D
            {0, -1}, // L
            {-1, 0} // U
    };

    int dirIndex = 0;

    public void solve(List<String> lines) {
        int maxy = 0;
        int emptyIndex = 0;
        for (int i = 0; i < lines.size(); i++) {
            maxy = Math.max(maxy, lines.get(i).length());
            if (lines.get(i).equals("")) {
                emptyIndex = i;
                break;
            }
        }

        grid = new char[emptyIndex][maxy];
        range = new int[emptyIndex][2];

        boolean init = true;
        for (int i = 0; i < emptyIndex; i++) {
            boolean findMin = true;
            for (int j = 0; j < maxy; j++) {
                if (j >= lines.get(i).length()) {
                    grid[i][j] = ' ';
                    continue;
                }
                grid[i][j] = lines.get(i).charAt(j);
                if (init && grid[i][j] == '.') {
                    x = i;
                    y = j;
                    init = false;
                }
                if (grid[i][j] != ' ') {
                    if (findMin) {
                        range[i][0] = j;
                        findMin = false;
                    }
                    range[i][1] = Math.max(range[i][1], j);
                }
            }
        }
        String path = lines.get(emptyIndex + 1);
        int[] dir = directions[0];

        int i = 0;
        while (i < path.length()) {
            if (Character.isAlphabetic(path.charAt(i))) {
                if (path.charAt(i) == 'R') {
                    dirIndex++;
                    if (dirIndex == 4) {
                        dirIndex = 0;
                    }
                } else {
                    dirIndex--;
                    if (dirIndex == -1) {
                        dirIndex = 3;
                    }
                }
                System.out.println(path.charAt(i));
                dir = directions[dirIndex];
                i++;
            } else {
                int j = i + 1;
                while (j < path.length() && !Character.isAlphabetic(path.charAt(j))) {
                    j++;
                }
                int step = Integer.parseInt(path.substring(i, j));
                System.out.println("step " + step);
                for (int m = 0; m < step; m++) {
                    move(dir);
                }
                i = j;
            }
            System.out.println("current pos: " + x + " " + y + " " + dirIndex);
        }
        int sum = (x + 1) * 1000 + 4 * (y + 1) + dirIndex;
        System.out.println(x + " " + y + " " + dirIndex);
        System.out.println(sum);
    }

    public void move(int[] dir) {
        int[] nextPos = getNextPos(dir);
        if (grid[nextPos[0]][nextPos[1]] == '#') {
            return;
        }

        x = nextPos[0];
        y = nextPos[1];
    }

    private int[] getNextPos(int[] dir) {
        int nx = x + dir[0];
        int ny = y + dir[1];
        if (inRange(nx, ny) && grid[nx][ny] != ' ') {
            return new int[]{nx, ny};
        }

        // out of bound, find wrapping
        while (true) {
            if (!inRange(nx, ny)) {
                if (nx < 0) {
                    nx = grid.length - 1;
                }
                if (nx >= grid.length) {
                    nx = 0;
                }
                if (ny < 0) {
                    ny = grid[0].length - 1;
                }
                if (ny >= grid[0].length) {
                    ny = 0;
                }
            } else {
                if (grid[nx][ny] == ' ') {
                    nx += dir[0];
                    ny += dir[1];
                } else {
                    return new int[]{nx, ny};
                }
            }
        }
    }

    private boolean inRange(int nx, int ny) {
        return nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length;
    }
}
