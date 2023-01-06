package AOC2021;

import java.util.List;
import java.util.PriorityQueue;

public class Day202115 {
    int[][] map;

    public void solve(List<String> lines) {
        int minDis = Integer.MAX_VALUE;

        int[][] map = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                map[i][j] = Integer.parseInt(lines.get(i).charAt(j) + "");
            }
        }
        int[][] dis = new int[map.length][map[0].length];

        PriorityQueue<int[]> pq = new PriorityQueue<>((op1, op2) -> {
            return dis[op1[0]][op1[1]] - dis[op2[0]][op2[1]];
        });

        int[][] dirs = new int[][]{
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };
        pq.offer(new int[]{0, 0});

        while (pq.size() > 0) {
            int[] pos = pq.poll();
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
                            pq.offer(new int[]{newx, newy});
                            dis[newx][newy] = newdis;
                        }
                    }
                }
            }
        }

        System.out.println(minDis);
    }
}
