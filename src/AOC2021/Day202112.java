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

        dfs(new ArrayList<>(), new HashSet<>(), "start");
        System.out.println(paths.size());
    }

    private void dfs(List<String> path, Set<String> visited, String pos) {
        if (pos.equals("end")) {
            paths.add(String.join(",",path));
            return;
        }
        path.add(pos);
        if (!pos.equals("start")) {
            if (Character.isLowerCase(pos.charAt(0))) {
                visited.add(pos);
            }
        }
        for (String nextpos : map.get(pos)) {
            if (nextpos.equals("start")) {
                continue;
            }

            if (nextpos.equals("end")) {
                dfs(path, visited, "end");
            }

            char c = nextpos.charAt(0);
            if (Character.isLowerCase(c) && visited.contains(nextpos)) {
                continue;
            }
            dfs(path, visited, nextpos);
        }
        path.remove(path.size() - 1);
        visited.remove(pos);
    }
}
