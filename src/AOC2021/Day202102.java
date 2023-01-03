package AOC2021;

import java.util.List;

public class Day202102 {
    public void solve(List<String> lines) {
        int horizontal = 0;
        int depth = 0;
        for (String str : lines) {
            String[] parts = str.split(" ");
            String instruction = parts[0];
            int number = Integer.parseInt(parts[1]);

            if (instruction.startsWith("forward")) {
                horizontal += number;
            } else if (instruction.startsWith("down")) {
                depth += number;
            } else if (instruction.startsWith("up")) {
                depth -= number;
            } else {
                horizontal -= number;
            }
        }

        System.out.println(horizontal * depth);
    }
}
