package AOC2021;

import java.util.*;

public class Day202108 {
    public void solve(List<String> lines) {
        String[] digitsArray = new String[]{
                "1110111",
                "0010010",
                "1011101",
                "1011011",
                "0111010",
                "1101011",
                "1101111",
                "1010010",
                "1111111",
                "1111011"
        };
        Set<String> digits = new HashSet<>(Arrays.asList(digitsArray));

        List<String> allSegments = fillAllSegments();

        int sum = 0;
        for (String line : lines) {
            String[] p = line.split("\\|");
            String[] numbers = p[0].trim().split(" ");
            String[] output = p[1].trim().split(" ");

            for (String pattern : allSegments) {
                if (match(pattern, numbers, digits)) {
                    // find out put numbers
                    sum += findOutput(pattern, output, digitsArray);
                    break;
                }
            }
        }

        System.out.println(sum);
    }

    private int findOutput(String pattern, String[] output, String[] digits) {
        String res = "";
        for (String o : output) {
            String[] digit = new String[7];
            Arrays.fill(digit, "0");
            for (char c : o.toCharArray()) {
                int index = pattern.indexOf(c);
                digit[index] = "1";
            }
            res +=findDigit(digits, String.join("",digit));
        }
        return Integer.parseInt(res);
    }

    private int findDigit(String[] digits, String digit) {
        for (int i=0;i<digits.length;i++) {
            if (digits[i].equals(digit)) {
                return i;
            }
        }
        return -1;
    }

    private boolean match(String pattern, String[] numbers, Set<String> digits) {
        for (String number : numbers) {
            String[] digit = new String[7];
            Arrays.fill(digit, "0");
            for (char c : number.toCharArray()) {
                int index = pattern.indexOf(c);
                digit[index] = "1";
            }
            if (!digits.contains(String.join("", digit))) {
                return false;
            }
        }
        return true;
    }

    private List<String> fillAllSegments() {
        List<String> list = new ArrayList<>();
        dfs(new StringBuilder(), 'a', list);
        return list;
    }

    private void dfs(StringBuilder sb, char c, List<String> list) {
        if (c == 'g' + 1) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i <= sb.length(); i++) {
            sb.insert(i, c);
            dfs(sb, (char) (c + 1), list);
            sb.deleteCharAt(i);
        }
    }
}
