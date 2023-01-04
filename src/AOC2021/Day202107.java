package AOC2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// how to calculate the point that's closest to all points?

public class Day202107 {
    public void solve(List<String> lines) {
        String[] parts = lines.get(0).split(",");
        List<Integer> crabs = new ArrayList<>();
        for (String crab : parts) {
            crabs.add(Integer.parseInt(crab));
        }

        int min = Integer.MAX_VALUE;

        int minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
        for (int val : crabs) {
            minx = Math.min(minx, val);
            maxx = Math.max(maxx, val);
        }

        for (int pos = minx; pos <= maxx; pos++) {
            int fuel = calculateFuel(crabs, pos);
            min = Math.min(fuel, min);
        }
        System.out.println(min);
    }

    private int calculateFuel(List<Integer> crabs, int pos) {
        int sum = 0;
        for (int crab : crabs) {
            sum += cal(crab, pos);
        }
        return sum;
    }

    private int cal(int x, int y) {
        int n = Math.abs(x - y);
        return (1 + n) * n / 2;
    }
}
