package AOC2021;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Day202110 {
    public void solve(List<String> lines) {
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            String missing = getMissingParts(line);
            if (!missing.equals("invalid")){
                scores.add(calculate(missing));
            }
        }

        scores.sort((a, b) -> a < b ? -1 : 1);
        System.out.println(scores.get(scores.size() / 2));
    }

    private long calculate(String missing) {
        long sum = 0;
        for (char c : missing.toCharArray()) {
            sum = sum * 5 + getPoint(c);
        }
        return sum;
    }

    private String getMissingParts(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (c == '[' || c == '(' || c == '<' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return "invalid";
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

                return "invalid";
            }
        }
        StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            char c = stack.pop();
            if (c == '(') {
                sb.append(')');
            } else if (c == '[') {
                sb.append(']');
            } else if (c == '{') {
                sb.append('}');
            } else if (c == '<') {
                sb.append('>');
            }
        }
        return sb.toString();
    }

    private int getPoint(char c) {
        if (c == ')')
            return 1;
        if (c == ']')
            return 2;
        if (c == '}')
            return 3;
        if (c == '>')
            return 4;
        return 0;
    }
}
