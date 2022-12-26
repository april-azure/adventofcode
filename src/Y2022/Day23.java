package Y2022;

import java.util.*;

public class Day23 {

    List<int[][]> directions = new ArrayList<>();

    public void solve(List<String> lines) {
        directions.add(new int[][]{
                {1, 0}, {1, -1}, {1, 0}, {1, 1}
        });
        directions.add(new int[][]{
                {-1, 0}, {-1, -1}, {-1, 0}, {1, 1}
        });
        directions.add(new int[][]{
                {0, -1}, {1, -1,}, {0, -1}, {-1, -1}
        });
        directions.add(new int[][]{
                {0, 1}, {1, 1}, {0, 1}, {1, 1}
        });
    }

    // new elves pos
    private List<String> action(List<String> elves) {
        Map<String, List<String>> nextMovesMap = new HashMap<>();

        for (String elf : elves) {

        }

        List<String> newPos = new ArrayList<>();

        return newPos;
    }

    private int[] getPos(String str) {
        String[] pos = str.split(",");
        return new int[]{Integer.parseInt(pos[0]), Integer.parseInt(pos[1])};
    }

    private String format(int[] pos) {
        return pos[0] + "," + pos[1];
    }
}
