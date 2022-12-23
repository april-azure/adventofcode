package Y2022;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Day1 {
    public void solve(List<String> lines) {
        List<Integer> energy = new ArrayList<>();
        PriorityQueue<Integer> top3 = new PriorityQueue<>((a, b) -> a - b);
        int cnt = 0;
        int max = 0;
        for (String line : lines) {
            if (line.equals("")) {
                top3.add(cnt);
                if (top3.size() > 3) {
                    top3.poll();
                }
                cnt = 0;
            } else {
                cnt += Integer.parseInt(line);
            }
        }
        top3.add(cnt);
        if (top3.size() > 3) {
            top3.poll();
        }
        int sum = 0;
        while (top3.size() > 0) {
            sum += top3.poll();
        }
        System.out.println(sum);
    }
}
