package Y2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day14 {
    public void solve(List<String> lines) {
        Set<String> blocks = new HashSet<>();
        for (String line : lines) {
            String[] pts = line.split(" -> ");
            for (int i = 0; i < pts.length - 1; i++) {
                int[] pt1 = format(pts[i]);
                int[] pt2 = format(pts[i + 1]);

                for (int m = Math.min(pt1[0], pt2[0]); m <= Math.max(pt1[0], pt2[0]); m++) {
                    for (int n = Math.min(pt1[1], pt2[1]); n <= Math.max(pt1[1], pt2[1]); n++) {
                        blocks.add(n + "," + m);
                    }
                }
            }
        }

        int cnt = 0;
        while (sandFall(blocks, 0, 500)) {
            cnt++;
        }
        System.out.println(cnt);
    }

    private boolean sandFall(Set<String> blocks, int i, int j) {
        int MAXY = 1000;
        if (i >= MAXY) {
            return false;
        }

        int[][] dirs = new int[][]{{1, 0}, {1, -1}, {1, 1}};
        for (int[] dir : dirs) {
            int newi = i + dir[0], newj = j + dir[1];
            if (!blocks.contains(newi + "," + newj)) {
                return sandFall(blocks, newi, newj);
            }
        }
        blocks.add(i + "," + j);
        return true;
    }

    private int[] format(String str) {
        String[] pt = str.split(",");
        return new int[]{Integer.parseInt(pt[0]), Integer.parseInt(pt[1])};
    }
}
