package Y2022;

import java.util.List;

public class Day25 {
    public void solve(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            sum += convert(line);
        }
        System.out.println("sum " + sum);
        System.out.println("SANFU: " + convert(sum));
    }

    public long convert(String input) {
        int i = 0;
        long sum = 0;
//        long M = 1;
        while (i < input.length()) {
            sum = sum * 5;
            if (Character.isDigit(input.charAt(i))) {
                sum += input.charAt(i) - '0';
            } else if (input.charAt(i) == '-') {
                sum -= 1;
            } else if (input.charAt(i) == '=') {
                sum -= 2;
            }
            i++;
        }
        return sum;
    }

    public String convert(long input) {
        StringBuilder sb = new StringBuilder();
        while (input != 0) {
            long r = input % 5;
            if (r == 0 || r == 1 || r == 2) {
                sb.insert(0, r);
                input = input / 5;
            }

            if (r == 3) {
                sb.insert(0, "=");
                input = (input + 2) / 5;
            }

            if (r == 4) {
                sb.insert(0, "-");
                input = (input + 1) / 5;
            }
        }

        return sb.toString();
    }
}
