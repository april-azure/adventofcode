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
        long res = 1;
        long preVal = 0;
        int i = 0;
        char operation = '+';

        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch) || ch == '(') {
                long val = 0;
                if (Character.isDigit(ch)) {
                    int j = getLastIndexForDigit(expression, i);
                    val = Integer.parseInt(expression.substring(i, j));
                    i = j;
                } else if (ch == '(') {
                    int j = getLastIndexForBracket(expression, i + 1);
                    val = evaluate(expression.substring(i + 1, j - 1));
                    i = j;
                }

                if (operation == '+') {
                    preVal = preVal + val;
                } else {
                    res = res * preVal;
                    preVal = val;
                }
            } else if (ch == '+' || ch == '*') {
                operation = ch;
                i++;
            }
        }

        return res * preVal;
    }

    private int getLastIndexForBracket(String expression, int i) {
        int openBracketCount = 1;
        while (openBracketCount != 0) {
            char nextChar = expression.charAt(i);
            if (nextChar == '(') {
                openBracketCount++;
            } else if (nextChar == ')') {
                openBracketCount--;
            }
            i++;
        }
        return i;
    }

    private int getLastIndexForDigit(String str, int start) {
        while (start < str.length()
                && Character.isDigit(str.charAt(start))) {
            start++;
        }
        return start;
    }
}
