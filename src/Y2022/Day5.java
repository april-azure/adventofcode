package Y2022;

import java.util.List;
import java.util.Stack;

public class Day5 {
    public void solve(List<String> lines) {
        int maxLine = 0;
        int stackCnt = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (Character.isDigit(lines.get(i).charAt(1))) {
                String line = lines.get(i).trim();
                stackCnt = Integer.parseInt(line.charAt(line.length() - 1) + "");
                maxLine = i;
                System.out.println(stackCnt);
                System.out.println(maxLine);
                break;
            }
        }

        Stack<Character>[] stacks = new Stack[stackCnt];
        for (int i = 0; i < stackCnt; i++) {
            stacks[i] = new Stack<>();
        }

        for (int i = maxLine - 1; i >= 0; i--) {
            String line = lines.get(i);
            for (int j = 0; j < stackCnt; j++) {
                if (j * 4 + 1 > line.length()) {
                    break;
                }
                char c = line.charAt(j * 4 + 1);
                if (c != ' ') {
                    stacks[j].push(c);
                }
            }
        }

        // process instructions
        for (int i = maxLine + 2; i < lines.size(); i++) {
            String line = lines.get(i).replace("move", "").replace(" ", "").replace("from", ",").replace("to", ",");
            String[] parts = line.split(",");
            int cnt = Integer.parseInt(parts[0]);
            int from = Integer.parseInt(parts[1]);
            int to = Integer.parseInt(parts[2]);

            move(cnt, stacks[from - 1], stacks[to - 1]);
        }

        for (Stack<Character> stack : stacks) {
            System.out.print(stack.peek() + "");
        }
    }

    private void move(int cnt, Stack<Character> from, Stack<Character> to) {
        Stack<Character> temp = new Stack<>();
        while (cnt-- > 0) {
            temp.push(from.pop());
        }
        while (temp.size() > 0) {
            to.push(temp.pop());
        }
    }
}
