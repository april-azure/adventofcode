package Y2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 {
    public void solve(List<String> lines) {
        int sum = 0;

        for (String line : lines) {
            int size = line.length() / 2;
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < size; i++) {
                set.add(line.charAt(i));
            }

            for (int i = size; i < line.length(); i++) {
                if (set.contains(line.charAt(i))) {
                    char c = line.charAt(i);
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
