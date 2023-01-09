package AOC2021;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Day202115 {
    int[][] map;

    public void solve(List<String> lines) {
        int minDis = Integer.MAX_VALUE;

        int[][] map = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                map[i][j] = lines.get(i).charAt(j) == '#' ? 1 : 0;
            }
        }
        map = buildMap(map);
        int[][] dis = new int[map.length][map[0].length];

        LinkedList<int[]> pq = new LinkedList<>();

        int[][] dirs = new int[][]{
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };
        pq.addLast(new int[]{0, 0});

        while (pq.size() > 0) {
            int[] pos = pq.removeFirst();
            int x = pos[0], y = pos[1];
            if (pos[0] == map.length - 1 && pos[1] == map[0].length - 1) {
                minDis = Math.min(minDis, dis[pos[0]][pos[1]]);
            } else {
                int curdis = dis[x][y];

                for (int[] dir : dirs) {
                    int newx = x + dir[0];
                    int newy = y + dir[1];
                    if (newx >= 0 && newx < map.length && newy >= 0 && newy < map[0].length) {
                        if (newx == 0 && newy == 0)
                            continue;

                        int newdis = curdis + map[newx][newy];
                        if (dis[newx][newy] == 0 || dis[newx][newy] > newdis) {
                            pq.addLast(new int[]{newx, newy});
                            dis[newx][newy] = newdis;
                        }
                    }
                }
            }
        }

        System.out.println(minDis);
    }

    private int[][] buildMap(int[][] map) {
        int[][] newMap = new int[map.length * 5][map[0].length * 5];
        for (int k1 = 0; k1 < 5; k1++) {
            for (int k2 = 0; k2 < 5; k2++) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[0].length; j++) {
                        int newx = map.length * (k1) + i;
                        int newy = map[0].length * (k2) + j;
                        int val = map[i][j] + k1 + k2;
                        if (val > 9) {
                            val = val - 9;
                        }
                        newMap[newx][newy] = val;
                    }
                }
            }
        }

        return newMap;
    }
}
