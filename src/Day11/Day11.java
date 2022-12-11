package Day11;

import com.sun.source.tree.ModuleTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {

    public void solve() {
        // readInput
        Scanner in = new Scanner(System.in);
        Monkey[] monkeys = new Monkey[4];
        int i = 0;
        while (in.hasNext()) {
            if (in.nextLine().contains("Monkey")) {
                Monkey monkey = new Monkey();
                // items
                String[] itemsString = in.nextLine().split(":")[1].split(", ");
                for (String item : itemsString) {
                    monkey.items.add(Integer.parseInt(item.trim()));
                }

                // operation
                String operation = in.nextLine().split(":")[1];
                monkey.operation = operation.trim();

                // divisible
                int divideBy = Integer.parseInt(in.nextLine().split(" ")[5]);
                monkey.divideBy = divideBy;

                // test true
                int testTrue = Integer.parseInt(in.nextLine().split(" ")[9]);
                monkey.ifTestTrue = testTrue;

                // test false
                int testFalse = Integer.parseInt(in.nextLine().split(" ")[9]);
                monkey.ifTestFalse = testFalse;

                monkeys[i] = monkey;
                i++;
            }
        }

        for (int round = 0; round < 20; round++) {
            for (Monkey m : monkeys) {
                m.throwItems(monkeys);
            }
        }

        for (Monkey m : monkeys) {
            System.out.println(m.inspectTimes);
        }
    }

    public class Monkey {
        List<Integer> items = new ArrayList<>();
        int divideBy = 1;
        int ifTestTrue = 0;
        int ifTestFalse = 0;
        String operation = "";
        int inspectTimes = 0;

        public void throwItems(Monkey[] monkeys) {
            for (int item : items) {
                int[] newVal = inspect(item);
                throwToMonkey(monkeys[newVal[1]], newVal[0]);
            }
            items.clear();
        }

        private int[] inspect(int val) {
            inspectTimes++;
            val = calculate(val);
            val /= 3;
            if (val % divideBy == 0) {
                return new int[] {val, ifTestTrue};
            } else {
                return new int[] {val, ifTestFalse};
            }
        }

        private int calculate(int oldVal) {
            char ops = operation.contains("*") ? '*' : '+';
            String[] vals = operation.split(" ");
            String secondVal = vals[4];
            if (secondVal.equals("old")) {
                return (ops == '*') ? oldVal * oldVal : oldVal + oldVal;
            } else {
                int val = Integer.parseInt(secondVal);
                return (ops == '*') ? oldVal * val : oldVal + val;
            }
        }

        private void throwToMonkey(Monkey other, int val) {
            System.out.println(val);
            other.items.add(val);
        }
    }
}
