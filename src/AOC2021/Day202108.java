package AOC2021;

import java.util.List;

public class Day202108 {
    public void solve(List<String> lines) {
        int cnt = 0;
        for (String line : lines) {
            String outputs = line.split("\\|")[1];
            String[] digits = outputs.trim().split(" ");
            for (String digit : digits) {
                if (digit.length() == 2
                        || digit.length() == 4
                        || digit.length() == 3
                        || digit.length() == 7
                ) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
