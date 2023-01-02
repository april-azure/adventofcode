package Y2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 {
    public void solve(List<String> lines) {
        int sum = 0;

        for (int i = 0; i < lines.size(); i = i + 3) {
            String line1 = lines.get(i);
            String line2 = lines.get(i + 1);
            String line3 = lines.get(i + 2);

            int[] visit = new int[100];
            for (char c : line1.toCharArray()) {
                visit[c - 'A'] = 1;
            }

            for (char c : line2.toCharArray()) {
                if (visit[c - 'A'] == 1) {
                    visit[c - 'A'] = 2;
                }
            }

            for (char c:line3.toCharArray()) {
                if (visit[c-'A'] == 2) {
                    System.out.println(c);
                    sum += getPriority(c);
                    break;
                }
            }
        }

        System.out.println(sum);
    }

    private int getPriority(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        }

        if (c >= 'A' && c <= 'Z') {
            return (c - 'A' + 1) + 26;
        }

        return -1;
    }
}
