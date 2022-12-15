package Day15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day15 {
    public void solve(List<String> lines) {
        List<int[]> sensorsWithDistance = new ArrayList<>();
        Set<String> beacons = new HashSet<>();
        int maxx = 0;
        int maxDis = 0;

        for (String line : lines) {
            line = line.replace("Sensor at x=", "");
            line = line.replace(" closest beacon is at x=", "");
            line = line.replace(" y=", "");

            String[] parts = line.split(":");
            String[] part1 = parts[0].split(",");
            String[] part2 = parts[1].split(",");

            int sx = Integer.parseInt(part1[0]);
            int sy = Integer.parseInt(part1[1]);
            int bx = Integer.parseInt(part2[0]);
            int by = Integer.parseInt(part2[1]);

            int dis = Math.abs(sx - bx) + Math.abs(sy - by);
            sensorsWithDistance.add(new int[]{sx, sy, dis});
            beacons.add(bx + "," + by);
            maxx = Math.max(sx, maxx);
            maxx = Math.max(bx, maxx);
            maxDis = Math.max(dis, maxDis);
        }

        int y = 2000000;
        int cnt = 0;
        for (int x = -175938 - maxDis; x <= maxx + maxDis; x++) {
            boolean possible = true;
            for (int[] sensorWithDis : sensorsWithDistance) {
                int dis = calDis(sensorWithDis, x, y);
                if (dis <= sensorWithDis[2] && !beacons.contains(x + "," + y)) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
//                System.out.println(x + "," + y);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private int calDis(int[] pos, int i, int j) {
        return Math.abs(pos[0] - i) + Math.abs(pos[1] - j);
    }
}
