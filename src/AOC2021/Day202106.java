package AOC2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day202106 {
    public void solve(List<String> lines) {

        Map<Integer, Long> fishCount = new HashMap<>();
        String[] items = lines.get(0).split(",");
        for (String item : items) {
            int number = Integer.parseInt(item);
            fishCount.put(number, fishCount.getOrDefault(number, (long)0) + 1);
        }

        for (long i = 1; i <= 256; i++) {
            fishCount = newDay(fishCount);
        }
        System.out.println(count(fishCount));
    }

    private long count(Map<Integer, Long> fish) {
        long sum = 0;
        for (long cnt : fish.values()) {
            sum += cnt;
        }
        return sum;
    }

    private Map<Integer, Long> newDay(Map<Integer, Long> fishCount) {
        Map<Integer, Long> newDay = new HashMap<>();
        for (int timer : fishCount.keySet()) {
            long cnt = fishCount.get(timer);
            if (timer == 0) {
                newDay.put(8, cnt);
                newDay.put(6, cnt);
            } else {
                timer--;
                newDay.put(timer, newDay.getOrDefault(timer, (long)0) + cnt);
            }
        }
        return newDay;
    }
}
