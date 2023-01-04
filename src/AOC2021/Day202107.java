package AOC2021;

import java.util.ArrayList;
import java.util.List;

public class Day202107 {
    public void solve(List<String> lines) {
        String[] parts = lines.get(0).split(",");
        List<Integer> crabs = new ArrayList<>();
        for (String crab : parts) {
            crabs.add(Integer.parseInt(crab));
        }

        int min = Integer.MAX_VALUE;
        for (int pos : crabs) {
            int fuel = calculateFuel(crabs, pos);
            min = Math.min(fuel, min);
        }
        System.out.println(min);
    }

    private int calculateFuel(List<Integer> crabs, int pos) {
        int sum = 0;
        for (int crab : crabs) {
            sum += Math.abs(crab - pos);
        }
        return sum;
    }
}
