package AOC2020;

import Helper.Utils;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class Day202016 {

    List<List<int[]>> ranges = new ArrayList<>();
    List<int[]> tickets = new ArrayList<>();
    Map<Integer, Integer> indexMap = new HashMap<>();
    Map<Integer, Set<Integer>> invalids = new HashMap<>();

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

        for (int i = lineForNearbyTicket + 1; i < lines.size(); i++) {
            int[] ticket = formatTicket(lines.get(i));
            if (isValidTicket(ticket)) {
                tickets.add(ticket);
            }
        }

        dfs(0);
        for (int key : indexMap.keySet()) {
            System.out.println(key + " " + indexMap.get(key));
        }

        int[] yourTicket = formatTicket(lines.get(lineForYourTicket + 1));
        long res = 1;
        for (int i = 0; i < yourTicket.length; i++) {
            int mappedIndex = indexMap.get(i);
            if (mappedIndex < 6) {
                res *= yourTicket[i];
            }
        }

        System.out.println(res);
    }

    // two methods,
    // 1) randomize the numbers, and check one by one
    // 2) map first one and then recurisvely match all
//    private void test1() {
//        findAllMappings(new ArrayList<>(), 0);
//    }

    private boolean dfs(int numberIndex) {
        if (numberIndex == ranges.size()) {
            return true;
        }

        for (int i = 0; i < ranges.size(); i++) {
            // check all valid indexes
            if (mightBeValidRange(i, numberIndex)) {
                indexMap.put(numberIndex, i);
                if (dfs(numberIndex + 1)) {
                    return true;
                }
                indexMap.remove(numberIndex);
            }
        }
        return false;
    }

    private boolean mightBeValidRange(int i, int numberIndex) {
        // if mapped
        Set<Integer> mapped = new HashSet<>(indexMap.values());
        if (mapped.contains(i)) {
            return false;
        }
        // if is invalid
        Set<Integer> invalidIndexes = invalids.getOrDefault(numberIndex, new HashSet<>());
        if (invalidIndexes.contains(i)) {
            return false;
        }
        List<int[]> targetRange = ranges.get(i);
        for (int[] ticket : tickets) {
            if (!isInRange(ticket[numberIndex], targetRange)) {
                if (!invalids.containsKey(numberIndex)) {
                    invalids.put(numberIndex, new HashSet<>());
                }
                invalids.get(numberIndex).add(i);
                return false;
            }
        }
        return true;
    }

//    private boolean testIsValidMapping(List<Integer> mapping) {
//        for (int ticketIndex = 0; ticketIndex < tickets.size(); ticketIndex++) {
//            for (int numberIndex = 0; numberIndex < ranges.size(); numberIndex++) {
//                List<int[]> range = ranges.get(mapping.get(numberIndex));
//                if (!isInRange(tickets.get(ticketIndex)[numberIndex], range)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

//    private boolean findAllMappings( List<Integer> path, int index) {
//        if (path.size() == ranges.size()) {
//            if (testIsValidMapping(path)) {
//                Utils.print(path);
//                return true;
//            }
//            return false;
//        }
//
//        for (int i = 0; i <= path.size(); i++) {
//            path.add(i, index);
//            if (findAllMappings(path, index + 1)) {
//                return true;
//            }
//            path.remove(i);
//        }
//        return false;
//    }

    private boolean isValidTicket(int[] ticket) {
        for (int val : ticket) {
            if (!isValidRange(val)) {
                return false;
            }
        }
        return true;
    }

    private int[] formatTicket(String str) {
        String[] parts = str.split(",");
        int[] ticket = new int[parts.length];
        for (int i = 0; i < ticket.length; i++) {
            ticket[i] = Integer.parseInt(parts[i]);
        }
        return ticket;
    }

    private boolean isValidRange(int val) {
        for (List<int[]> range : ranges) {
            for (int[] r : range) {
                if (val >= r[0] && val <= r[1]) {
                    return true;
                }
            }
        }
        return false;
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
