package Y2022;

import java.util.*;

public class Day18 {

    int maxx = 0;
    int maxy = 0;
    int maxz = 0;
    int minx = Integer.MAX_VALUE;
    int miny = Integer.MAX_VALUE;
    int minz = Integer.MAX_VALUE;

    public void solve(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> uniqueFaces = new HashSet<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int z = Integer.parseInt(parts[2]);

            maxx = Math.max(maxx, x);
            maxy = Math.max(maxy, y);
            maxz = Math.max(maxz, z);
            minx = Math.min(minx, x);
            miny = Math.min(miny, y);
            minz = Math.min(minz, z);

            int[][][] faces = getFaces(x, y, z);
            for (int[][] face : faces) {
                String f = convertPosToString(face);
                int cnt = map.getOrDefault(f, 0) + 1;
                map.put(f, cnt);
            }
        }

        maxx++;
        maxy++;
        maxz++;
        minx--;
        miny--;
        minz--;

        int cnt = 0;
        for (String f : map.keySet()) {
            if (map.get(f) == 1) {
                cnt++;
                uniqueFaces.add(f);
            }
        }
        bfs(uniqueFaces);
        System.out.println(cnt);
    }

    private void bfs(Set<String> allfaces) {
        LinkedList<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> outsideFaces = new HashSet<>();
        q.add(new int[]{maxx, maxy, maxz});
        visited.add(format(q.get(0)));

        while (q.size() > 0) {
            int[] pos = q.removeFirst();
            int x = pos[0], y = pos[1], z = pos[2];
            // check faces
            int[][][] sides = new int[][][]{
                    {
                            {x, y, z}, {x - 1, y - 1, z}, {0, 0, 1} // up z+1
                    },
                    {
                            {x, y, z - 1}, {x - 1, y - 1, z - 1}, {0, 0, -1} // down z-1
                    },
                    {
                            {x, y, z}, {x, y - 1, z - 1}, {1, 0, 0} // front x+1
                    },
                    {
                            {x - 1, y, z}, {x - 1, y - 1, z - 1}, {-1, 0, 0} // back x-1
                    },
                    {
                            {x, y - 1, z}, {x - 1, y - 1, z - 1}, {0, -1, 0} // left y-1
                    },
                    {
                            {x, y, z}, {x - 1, y, z - 1}, {0, 1, 0} // right y+1
                    }
            };
            for (int[][] side : sides) {
                String face = convertPosToString(side);
                if (allfaces.contains(face)) {
                    outsideFaces.add(face);
                    // cannot move forward
                } else {
                    int[] nextPoint = new int[]{
                            x + side[2][0],
                            y + side[2][1],
                            z + side[2][2]
                    };
                    if (nextPoint[0] < minx || nextPoint[0] > maxx || nextPoint[1] < miny
                            || nextPoint[1] > maxy || nextPoint[2] < minz || nextPoint[2] > maxz) {
                        continue;
                    }
                    if (visited.contains(format(nextPoint))) {
                        continue;
                    }
                    q.add(nextPoint);
                    visited.add(format(nextPoint));
                }
            }
        }
        System.out.println(outsideFaces.size());
    }

    private String convertPosToString(int[][] pos) {
        int[] from = pos[0], to = pos[1];
        return format(from) + ";" + format(to);
    }

    private String format(int[] pos) {
        return pos[0] + "," + pos[1] + "," + pos[2];
    }

    private int[][][] getFaces(int x, int y, int z) {
        return new int[][][]{
                {
                        {x, y, z}, {x - 1, y - 1, z} // up z+1
                },
                {
                        {x, y, z - 1}, {x - 1, y - 1, z - 1} // down z-1
                },
                {
                        {x, y, z}, {x, y - 1, z - 1} // front x+1
                },
                {
                        {x - 1, y, z}, {x - 1, y - 1, z - 1} // back x-1
                },
                {
                        {x, y - 1, z}, {x - 1, y - 1, z - 1} // left y-1
                },
                {
                        {x, y, z}, {x - 1, y, z - 1} // right y+1
                }
        };
    }
}
