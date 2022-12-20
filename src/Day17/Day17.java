package Day17;

import java.util.ArrayList;
import java.util.List;

public class Day17 {

    int h = -1;
    List<int[]> list = new ArrayList<>();
    int[] hMap = new int[]{1, 3, 3, 4, 2};
    int[][][] items = new int[][][]{{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, {{1, 0}, {0, 1}, {1, 1}, {1, 2}, {2, 1}}, {{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}}, {{0, 0}, {1, 0}, {2, 0}, {3, 0}}, {{0, 0}, {0, 1}, {1, 0}, {1, 1}}};

    List<Integer> wind = new ArrayList<>();
    int windIndex = 0;

    public void solve(List<String> lines) {
        for (char c : lines.get(0).toCharArray()) {
            wind.add(c == '>' ? 1 : -1);
        }

        for (int i = 0; i < 2022; i++) {
            fallNewPiece(i % 5);
        }

        System.out.println(h);
    }

    public void fallNewPiece(int index) {
        int itemHeight = hMap[index];
        while (list.size() < h + itemHeight + 4) {
            list.add(new int[7]);
        }

        int i = h + 4;
        int j = 2;
        while (true) {
            int direction = windIndex < wind.size() ? wind.get(windIndex++) : 0;
            if (windIndex == wind.size()) {
                windIndex = 0;
            }
            if (canMove(items[index], new int[]{0, direction}, new int[]{i, j})) {
                j = j + direction;
            }
            if (canMove(items[index], new int[]{-1, 0}, new int[]{i, j})) {
                i = i - 1;
            } else {
                break;
            }
        }

        // paint
        for (int k = 0; k < items[index].length; k++) {
            int newi = i + items[index][k][0];
            int newj = j + items[index][k][1];
            h = Math.max(h, newi);
            list.get(newi)[newj] = 1;
        }

//        print();
    }

    private void print() {
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print((i + " ").substring(0, 2) + ": ");
            for (int j = 0; j < 7; j++) {
                System.out.print(list.get(i)[j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    private boolean canMove(int[][] item, int[] direction, int[] curPos) {
        for (int i = 0; i < item.length; i++) {
            int newi = item[i][0] + direction[0] + curPos[0];
            int newj = item[i][1] + direction[1] + curPos[1];
            if (newj < 0 || newj >= 7 || newi < 0) {
                return false;
            }

            if (list.get(newi)[newj] == 1) {
                return false;
            }
        }

        return true;
    }
}
