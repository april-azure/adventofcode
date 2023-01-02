package Y2022;

import java.util.List;

public class Day4 {
    public void solve(List<String> lines) {
        int cnt = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            int[] pair1 = format(parts[0]);
            int[] pair2 = format(parts[1]);

            if (pair1[1] < pair2[0] || pair2[1] < pair1[0]) {
                cnt++;
            }
        }
        System.out.println(lines.size() - cnt);
    }

    private int[] format(String item) {
        String[] parts = item.split("-");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }
}
