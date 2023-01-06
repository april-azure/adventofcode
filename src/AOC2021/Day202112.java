package AOC2021;

import java.util.*;

public class Day202112 {

    Map<String, List<String>> map = new HashMap<>();
    Set<String> paths = new HashSet<>();

    public void solve(List<String> lines) {
        for (String line : lines) {
            String[] parts = line.split("-");
            String x = parts[0], y = parts[1];
            if (!map.containsKey(x)) {
                map.put(x, new ArrayList<>());
            }
            if (!map.containsKey(y)) {
                map.put(y, new ArrayList<>());
            }
            map.get(x).add(y);
            map.get(y).add(x);
        }

        dfs(new ArrayList<>(), new HashSet<>(), "start", null);
//        System.out.println(String.join("\n", paths));
        System.out.println(paths.size());
    }

    private void dfs(List<String> path, Set<String> visited, String pos, String visitedTwice) {
//        System.out.println(String.join(",", path) + " visit " + pos + " visited twice: " + visitedTwice);
        if (pos.equals("end")) {
            paths.add(String.join(",", path));
            return;
        }
        path.add(pos);
        if (!pos.equals("start")) {
            if (Character.isLowerCase(pos.charAt(0))) {
                if (visited.contains(pos)) {
                    visitedTwice = pos;
                }
                visited.add(pos);
            }
        }
        for (String nextpos : map.get(pos)) {
            if (nextpos.equals("start")) {
                continue;
            }

            if (nextpos.equals("end")) {
                dfs(path, visited, "end", visitedTwice);
            }

            char c = nextpos.charAt(0);
            if (Character.isLowerCase(c) && visited.contains(nextpos)) {
                if (visitedTwice == null) {
                    dfs(path, visited, nextpos, null);
                } else {
                    continue;
                }
            } else {
                dfs(path, visited, nextpos, visitedTwice);
            }
        }
        path.remove(path.size() - 1);
        if (visitedTwice != null && visitedTwice.equals(pos)) {
            return;
        }
        visited.remove(pos);
    }
}
