package AOC2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day202104 {
    public void solve(List<String> lines) {
        String[] items = lines.get(0).split(",");
        List<Integer> numbers = Arrays.stream(items).map(s -> Integer.parseInt(s)).toList();

        List<int[][]> boards = new ArrayList<>();

        for (int i = 2; i < lines.size(); i = i + 6) {
            int[][] board = new int[5][];
            for (int j = 0; j < 5; j++) {
                String[] parts = lines.get(i + j).trim().replace("  ", " ").split(" ");
                int[] line = new int[5];
                for (int k = 0; k < 5; k++) {
                    line[k] = Integer.parseInt(parts[k].trim());
                }
                board[j] = line;
            }
            boards.add(board);
        }

        play(boards, numbers);
    }

    public void play(List<int[][]> boards, List<Integer> numbers) {
        boolean[] winBoards = new boolean[boards.size()];
        int winCnt = 0;
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);

            for (int j = 0; j < boards.size(); j++) {
                int[][] board = boards.get(j);
                if (winBoards[j]) {
                    continue;
                }
                boolean isWinning = check(board, number);

                if (isWinning) {
                    winCnt++;
                    winBoards[j] = true;
                    if (winCnt == winBoards.length) {
                        calculatePoint(boards.get(j), numbers, i);
                    }
                }
            }
        }
    }

    private void calculatePoint(int[][] board, List<Integer> numbers, int numberIndex) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != -1) {
                    sum += board[i][j];
                }
            }
        }
        System.out.println(sum * numbers.get(numberIndex));
    }

    private boolean check(int[][] board, int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == number) {
                    board[i][j] = -1;
                    if (isWinning(board, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isWinning(int[][] board, int i, int j) {
        int check1 = 1, check2 = 1;
        for (int m = 0; m < 5; m++) {
            check1 *= board[i][m];
            check2 *= board[m][j];
        }
        if (check1 == -1 || check2 == -1) {
            return true;
        }
        return false;
    }
}
