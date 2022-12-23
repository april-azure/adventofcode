package Y2022;

import java.sql.Array;
import java.util.*;

public class Day21 {
    public void solve(List<String> lines) {
        Map<String, Character> operations = new HashMap<>();
        Map<String, Long> values = new HashMap<>();
        Map<String, String[]> operators = new HashMap<>();
        Map<String, Integer> dependents = new HashMap<>();
        Map<String, List<String>> leadsTo = new HashMap<>();

        for (String line : lines) {
            String[] parts = line.split(":");
            String m = parts[0];
            if (parts[1].contains("+") || parts[1].contains("-") || parts[1].contains("*") || parts[1].contains("/")) {
                line = parts[1].trim().replace(" ", "");
                String op1 = line.substring(0, 4);
                String op2 = line.substring(5);
                operations.put(m, line.charAt(4));
                if (!leadsTo.containsKey(op1)) {
                    leadsTo.put(op1, new ArrayList<>());
                }
                if (!leadsTo.containsKey(op2)) {
                    leadsTo.put(op2, new ArrayList<>());
                }
                operators.put(m, new String[]{op1, op2});
                leadsTo.get(op1).add(m);
                leadsTo.get(op2).add(m);
                dependents.put(m, 2);
            } else {
                long val = Long.parseLong(parts[1].trim());
                values.put(m, val);
            }
        }
        for (String m : values.keySet()) {
            for (String next : leadsTo.get(m)) {
                dependents.put(next, dependents.get(next) - 1);
            }
        }
        Set<String> monkeys = operations.keySet();

        while (dependents.size() > 0) {
            for (String m : monkeys) {
                if (dependents.containsKey(m)) {
                    if (dependents.get(m) != 0) {
                        continue;
                    }

                    // ready to calculate
                    String op1 = operators.get(m)[0];
                    String op2 = operators.get(m)[1];
                    long op1long = values.get(op1);
                    long op2long = values.get(op2);
                    Character op = operations.get(m);
                    long val = cal(op1long, op2long, op);
                    values.put(m, val);
                    System.out.println(m + " " + val);
                    for (String nextM : leadsTo.get(m)) {
                        dependents.put(nextM, dependents.get(nextM) - 1);
                    }
                    dependents.remove(m);
                }
            }
        }
        System.out.println(values.get("pppw"));
    }

    private long cal(long x, long y, Character c) {
        if (c == '+') {
            return x + y;
        } else if (c == '-') {
            return x - y;
        } else if (c == '/') {
            return x / y;
        } else {
            return x * y;
        }
    }
}
