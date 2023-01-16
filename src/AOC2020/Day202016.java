package AOC2020;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class Day202016 {

    List<List<int[]>> ranges = new ArrayList<>();

    public void solve(List<String> lines) {
        int lineForYourTicket = getLine(lines, "your ticket:");
        int lineForNearbyTicket = getLine(lines, "nearby tickets:");
        for (int i = 0; i < lineForYourTicket - 1; i++) {
            // format range
            String str = lines.get(i).split(":")[1].trim();
            String[] parts = str.split("or");
            List<int[]> range = new ArrayList<>();
            range.add(getRange(parts[0]));
            range.add(getRange(parts[1]));
            ranges.add(range);
        }


    }

    private boolean isInRange(int val, List<int[]> ranges) {
        for (int[] range : ranges) {
            if (val >= range[0] && val <= range[1]) {
                return true;
            }
        }
        return false;
    }

    private int[] getRange(String str) {
        String[] vals = str.split("-");
        int from = Integer.parseInt(vals[0].trim());
        int to = Integer.parseInt(vals[1].trim());
        return new int[]{from, to};
    }

    private int getLine(List<String> lines, String str) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).trim().equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
