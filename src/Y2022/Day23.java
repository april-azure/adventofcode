package Y2022;

import java.util.*;

public class Day23 {

    List<int[][]> directions = new ArrayList<>();
    Set<String> elfPoses = new HashSet<>();

    public void solve(List<String> lines) {
        directions.add(new int[][]{{1, 0}, {1, -1}, {1, 0}, {1, 1}});
        directions.add(new int[][]{{-1, 0}, {-1, -1}, {-1, 0}, {-1, 1}});
        directions.add(new int[][]{{0, -1}, {1, -1,}, {0, -1}, {-1, -1}});
        directions.add(new int[][]{{0, 1}, {1, 1}, {0, 1}, {-1, 1}});

        for (int i = lines.size() - 1; i >= 0; i--) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '#') {
                    int[] pos = new int[]{lines.size() - i - 1, j};
                    elfPoses.add(format(pos));
                }
            }
        }

        for (int i = 0; i < 5000; i++) {
            Set<String> nextPoses = action();
            boolean completed = true;
            for (String pos : nextPoses) {
                if (!elfPoses.contains(pos)) {
                    completed = false;
                }
            }
            if (completed) {
                System.out.println(i + 1);
                return;
            }

            elfPoses = nextPoses;
            // update preference
            int[][] dir = directions.remove(0);
            directions.add(dir);
//            System.out.println("Round " + (i + 1));
//            print();
        }

//        countEmptyGround();
    }

    private void countEmptyGround() {
        int minX = Integer.MAX_VALUE,
                minY = Integer.MAX_VALUE,
                maxX = Integer.MIN_VALUE,
                maxY = Integer.MIN_VALUE;
        for (String curPos : elfPoses) {
            int[] pos = convert(curPos);
            int x = pos[0];
            int y = pos[1];
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        System.out.println((maxX - minX + 1) * (maxY - minY + 1) - elfPoses.size());
    }

    private Set<String> action() {
        Map<String, String> nextPosMap = new HashMap<>();
        Map<String, Integer> nextPosCnt = new HashMap<>();

        for (String curPos : elfPoses) {
            String nextPos = getNextPos(curPos);
            nextPosMap.put(curPos, nextPos);
            nextPosCnt.put(nextPos, nextPosCnt.getOrDefault(nextPos, 0) + 1);
        }

        Set<String> nextPoses = new HashSet<>();
        for (String curPos : nextPosMap.keySet()) {
            String nextPos = nextPosMap.get(curPos);
            int cnt = nextPosCnt.get(nextPos);
            if (cnt > 1) {
                nextPoses.add(curPos);
            } else {
                nextPoses.add(nextPos);
            }
        }

        return nextPoses;
    }

    private String getNextPos(String curPos) {
        int[] pos = convert(curPos);
        if (!needToMove(curPos)) {
            return curPos;
        }
        int[] next = new int[]{};


        for (int[][] dirs : directions) {
            boolean canMove = true;
            int[] dir = dirs[0];
            for (int i = 1; i < dirs.length; i++) {
                int[] nextPos = new int[]{pos[0] + dirs[i][0], pos[1] + dirs[i][1]};
                if (elfPoses.contains(format(nextPos))) {
                    canMove = false;
                    break;
                }
            }

            if (canMove) {
                next = new int[]{pos[0] + dir[0], pos[1] + dir[1]};
                break;
            }
        }

        if (next.length == 0) {
            next = pos;
        }


        return format(next);
    }

    private boolean needToMove(String curPos) {
        int[] pos = convert(curPos);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int[] newPos = new int[]{pos[0] + i, pos[1] + j};
                if (elfPoses.contains(format(newPos))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void print() {
        int minX = Integer.MAX_VALUE,
                minY = Integer.MAX_VALUE,
                maxX = Integer.MIN_VALUE,
                maxY = Integer.MIN_VALUE;
        for (String curPos : elfPoses) {
            int[] pos = convert(curPos);
            int x = pos[0];
            int y = pos[1];
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        for (int i = maxX; i >= minX; i--) {
            for (int j = minY; j <= maxY; j++) {
                if (elfPoses.contains(format(new int[]{i, j}))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String format(int[] pos) {
        return pos[0] + "," + pos[1];
    }

    private int[] convert(String str) {
        String[] parts = str.split(",");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }
}
