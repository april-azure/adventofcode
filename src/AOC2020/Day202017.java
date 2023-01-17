package AOC2020;

import Helper.Utils;
import jdk.jshell.execution.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day202017 {

    Set<String> activePoints = new HashSet<>();
    int x, y, z, w;

    public void solve(List<String> lines) {
        x = lines.size();
        y = lines.size();
        z = 1;
        w = 1;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (lines.get(i).charAt(j) == '#') {
                    int[] pt = new int[]{i, j, 0, 0};
                    String hash = Utils.convert(pt);
                    activePoints.add(hash);
                }
            }
        }

        for (int i = 0; i <= 5; i++) {
            action();
        }

        System.out.println(activePoints.size());
    }

    public void action() {
        Set<String> newActivePts = new HashSet<>();
        for (int i = -x; i <= x; i++) {
            for (int j = -y; j <= y; j++) {
                for (int k = -z; k <= z; k++) {
                    for (int h = -w; h <= w; h++) {
                        int[] pt = new int[]{i, j, k, h};
                        int activeNeighbors = 0;
                        for (int m = -1; m <= 1; m++) {
                            for (int n = -1; n <= 1; n++) {
                                for (int o = -1; o <= 1; o++) {
                                    for (int p = -1; p <= 1; p++) {
                                        if (m == 0 && n == 0 && o == 0 && p == 0) {
                                            continue;
                                        }
                                        int[] npt = new int[]{
                                                i + m,
                                                j + n,
                                                k + o,
                                                h + p
                                        };
                                        if (activePoints.contains(Utils.convert(npt))) {
                                            activeNeighbors++;
                                        }
                                    }

                                }
                            }
                        }
                        if (activePoints.contains(Utils.convert(pt))) {
                            if (activeNeighbors == 2 || activeNeighbors == 3) {
                                newActivePts.add(Utils.convert(pt));
                            }
                        } else {
                            if (activeNeighbors == 3) {
                                newActivePts.add(Utils.convert(pt));
                            }
                        }
                    }
                }
            }
        }
        x++;
        y++;
        z++;
        w++;
        activePoints = newActivePts;
    }
}
