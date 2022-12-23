package Y2022;

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

            String[] parts = line.split(";");
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

        for (int[] sensor : sensorsWithDistance) {
            int x = sensor[0], y = sensor[1], dis = sensor[2];
            int[] left = new int[]{x - dis - 1, y};
            int[] bottom = new int[]{x, y - dis - 1};
            int[] right = new int[]{x + dis + 1, y};
            for (int i = 0; i <= dis + 1; i++) {
                if (checkPossible(sensorsWithDistance, beacons, left[0] + i, left[1] + i))
                    return;
                if (checkPossible(sensorsWithDistance, beacons, bottom[0] + i, bottom[1] + i))
                    return;
                if (checkPossible(sensorsWithDistance, beacons, bottom[0] - i, bottom[1] + i))
                    return;
                if (checkPossible(sensorsWithDistance, beacons, right[0] - i, right[1] + i))
                    return;
            }
        }
    }

    private boolean checkPossible(List<int[]> sensorsWithDistance, Set<String> beacons, int x, int y) {
        if (x < 0 || x > 4000000 || y < 0 || y > 4000000 || beacons.contains(x + "," + y))
            return false;
        for (int[] sensorWithDis : sensorsWithDistance) {
            int dis = calDis(sensorWithDis, x, y);
            if (dis <= sensorWithDis[2] && !beacons.contains(x + "," + y)) {
                return false;
            }
        }
        System.out.println(x + "," + y);
        return true;
    }

    private int calDis(int[] pos, int i, int j) {
        return Math.abs(pos[0] - i) + Math.abs(pos[1] - j);
    }
}
