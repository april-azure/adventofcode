package AOC2020;

import java.util.List;

public class Day202018 {
    public void solve(List<String> lines) {
        long sum = 0;
        for (String str : lines) {
            sum += evaluate(str);
        }

        System.out.println(sum);
    }

    private long evaluate(String expression) {
        expression = expression.replace(" ", "");
        long res = 0;

        int i = 0;
        char operation = '+';

        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                int j = i;
                while (j < expression.length()
                        && Character.isDigit(expression.charAt(j))) {
                    j++;
                }
                int val = Integer.parseInt(expression.substring(i, j));
                if (operation == '+') {
                    res = res + val;
                } else {
                    res = res * val;
                }
                i = j;
            } else if (ch == '+' || ch == '*') {
                operation = ch;
                i++;
            } else if (ch == '(') {
                int openBracketCount = 1;
                int j = i + 1;
                while (openBracketCount != 0) {
                    char nextChar = expression.charAt(j);
                    if (nextChar == '(') {
                        openBracketCount++;
                    } else if (nextChar == ')') {
                        openBracketCount--;
                    }
                    j++;
                }
                long val = evaluate(expression.substring(i + 1, j - 1));
                if (operation == '+') {
                    res = res + val;
                } else if (operation == '*') {
                    res = res * val;
                }
                i = j;
            }
        }

        return res;
    }
}
