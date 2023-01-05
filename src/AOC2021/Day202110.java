package AOC2021;

import java.util.List;
import java.util.Stack;

public class Day202110 {
    public void solve(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            sum += calPoint(line);
        }
        System.out.println(sum);
    }

    private int calPoint(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (c == '[' || c == '(' || c == '<' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return getPoint(c);
                }

                char preC = stack.pop();
                if (preC == '(' && c == ')')
                    continue;
                if (preC == '[' && c == ']')
                    continue;
                if (preC == '<' && c == '>')
                    continue;
                if (preC == '{' && c == '}')
                    continue;

                return getPoint(c);
            }
        }
        return 0;
    }

    private int getPoint(char c) {
        if (c == ')')
            return 3;
        if (c == ']')
            return 57;
        if (c == '}')
            return 1197;
        if (c == '>')
            return 25137;
        return 0;
    }
}
