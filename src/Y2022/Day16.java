//package Day16;
//
//import java.util.*;
//
//public class Day16 {
//
//    int max = 0;
//
//    public void solve(List<String> lines) {
//        Map<String, String[]> paths = new HashMap<>();
//        Map<String, Map<String, Integer>> dis;
//        Set<String> allPts = new HashSet<>();
//        Set<String> allPtsNonZero = new HashSet<>();
//        Map<String, Integer> pressure = new HashMap<>();
//
//        for (String line : lines) {
//            String pt = line.substring(6, 8);
//            line = line.substring(8).replace(" has flow rate=", "")
//                    .replace(" tunnels lead to valves ", "")
//                    .replace(" tunnel leads to valve ", "")
//                    .replace(" ", "");
//            String[] parts = line.split(";");
//            int rate = Integer.parseInt(parts[0]);
//            String[] reachablePts = parts[1].split(",");
//
//            pressure.put(pt, rate);
//            allPts.add(pt);
//            if (rate > 0) {
//                allPtsNonZero.add(pt);
//            }
//            paths.put(pt, reachablePts);
//        }
//
//        dis = findClosestDis(paths, allPts);
//        Set<String> visited = new HashSet<>();
//        visit(dis, allPtsNonZero, visited, pressure, 30, 0, "AA");
//
//        System.out.println("Max: " + max);
//    }
//
//    private Map<String, Map<String, Integer>> findClosestDis(
//            Map<String, String[]> paths,
//            Set<String> allPts) {
//        Map<String, Map<String, Integer>> disMap = new HashMap<>();
//        for (String pt : allPts) {
//            Set<String> visited = new HashSet<>();
//            int dis = -1;
//            LinkedList<String> toVisit = new LinkedList<>();
//            toVisit.add(pt);
//
//            Map curDisMap = new HashMap();
//            while (toVisit.size() > 0) {
//                dis++;
//                // visit current
//                int size = toVisit.size();
//                while (size-- > 0) {
//
//                    String curPt = toVisit.removeFirst();
//                    visited.add(curPt);
//                    curDisMap.put(curPt, dis);
//
//                    String[] connectedPts = paths.get(curPt);
//                    for (String connectedPt : connectedPts) {
//                        if (!visited.contains(connectedPt)) {
//                            toVisit.addLast(connectedPt);
//                        }
//                    }
//                }
//            }
//            disMap.put(pt, curDisMap);
//        }
//
//        return disMap;
//    }
//
//    private void visit(Map<String, Map<String, Integer>> allDis,
//                       Set<String> allPts, Set<String> visited, Map<String, Integer> pressure,
//                       int min1, int min2, int sum, String v1, String v2) {
//        max = Math.max(max, sum);
//
//        // open current valve
//        // didn't consider AA situation
//        visited.add(v1);
//        visited.add(v2);
//
//        for (String pt1 : allPts) {
//            for (String pt2 : allPts) {
//                if (visited.contains(pt1) || visited.contains(pt2) || pt1.equals(pt2)) {
//                    continue;
//                }
//
//                int dis1 = allDis.get(v1).get(pt1);
//                int dis2 = allDis.get(v2).get(pt2);
//                if (dis > minute)
//                    continue;
//
//                visit(
//                        allDis,
//                        allPts,
//                        visited,
//                        pressure,
//                        minute - 1 - dis,
//                        sum + pressure.get(pt) * (minute - dis - 1),
//                        pt
//                );
//            }
//        }
//        visited.remove(v1);
//        visited.remove(v2);
//    }
//}
