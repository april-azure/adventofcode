package AOC2021;

import java.util.List;

public class Day202102 {
    public void solve(List<String> lines) {
        int aim = 0;
        int horizontal = 0;
        int depth = 0;
        for (String str : lines) {
            String[] parts = str.split(" ");
            String instruction = parts[0];
            int number = Integer.parseInt(parts[1]);

            if (instruction.startsWith("forward")) {
                horizontal += number;
                depth += (aim * number);
            } else if (instruction.startsWith("down")) {
                aim += number;
            } else if (instruction.startsWith("up")) {
                aim -= number;
            }
        }

        System.out.println(horizontal * depth);
    }
}
