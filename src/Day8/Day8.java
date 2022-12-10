package Day8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8 {

    public static void findHighestScenicScore() {
        int[][] trees = initTrees();
        int row = trees.length;
        int col = trees[0].length;
        int maxScore = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int score = 1;
                int cur = trees[i][j];

                // up
                int up = 0;
                for (int k = i - 1; k >= 0; k--) {
                    up++;
                    if (trees[k][j] >= cur) {
                        break;
                    }
                }

                // down
                int down = 0;
                for (int k = i + 1; k < row; k++) {
                    down++;
                    if (trees[k][j] >= cur) {
                        break;
                    }
                }

                // left
                int left = 0;
                for (int k = j - 1; k >= 0; k--) {
                    left++;
                    if (trees[i][k] >= trees[i][j]) {
                        break;
                    }
                }

                // right
                int right = 0;
                for (int k = j + 1; k < col; k++) {
                    right++;
                    if (trees[i][k] >= trees[i][j]) {
                        break;
                    }
                }
                score = up * down * right * left;
                maxScore = Math.max(maxScore, score);
                System.out.println(i + " " + j + " " + score);
            }
        }
        System.out.println(maxScore);
    }

    public static void countVisibleTrees() {
        int[][] trees = initTrees();
        int row = trees.length;
        int col = trees[0].length;
        int[][] upperHighestTrees = new int[row + 2][col + 2];
        int[][] lowerHighestTrees = new int[row + 2][col + 2];
        int[][] leftHighestTrees = new int[row + 2][col + 2];
        int[][] rightHighestTrees = new int[row + 2][col + 2];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                upperHighestTrees[i][j] = Math.max(upperHighestTrees[i - 1][j], trees[i - 1][j - 1]);
                leftHighestTrees[i][j] = Math.max(leftHighestTrees[i][j - 1], trees[i - 1][j - 1]);
            }
        }

        for (int i = row; i >= 1; i--) {
            for (int j = col; j >= 1; j--) {
                lowerHighestTrees[i][j] = Math.max(lowerHighestTrees[i + 1][j], trees[i - 1][j - 1]);
                rightHighestTrees[i][j] = Math.max(rightHighestTrees[i][j + 1], trees[i - 1][j - 1]);
            }
        }

        print(upperHighestTrees);
        print(lowerHighestTrees);
        print(leftHighestTrees);
        print(rightHighestTrees);

        int totalVisibleTrees = 0;

        for (int i = 1; i < row + 1; i++) {
            for (int j = 1; j < col + 1; j++) {
                int tree = trees[i - 1][j - 1];
                int up = upperHighestTrees[i - 1][j];
                int down = lowerHighestTrees[i + 1][j];
                int left = leftHighestTrees[i][j - 1];
                int right = rightHighestTrees[i][j + 1];
                if (tree > up || tree > down || tree > left || tree > right || isOnEdge(trees, i - 1, j - 1)) {
                    totalVisibleTrees++;
                }
            }
        }

        System.out.println(totalVisibleTrees);
    }

    private static boolean isOnEdge(int[][] grid, int i, int j) {
        return i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1;
    }

    private static int[][] initTrees() {
        Scanner in = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while (in.hasNext()) {
            input.add(in.nextLine());
        }
        int[][] grid = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = input.get(i).charAt(j) - '0';
            }
        }

        return grid;
    }

    private static void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
