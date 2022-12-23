package Y2022;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day10 {
    int register = 1;
    int cycle = 0;
    int signalStrength = 0;

    Set<Integer> targetSingalStrengthCycles;

    public Day10() {
        targetSingalStrengthCycles = new HashSet<>();
        targetSingalStrengthCycles.add(20);
        targetSingalStrengthCycles.add(60);
        targetSingalStrengthCycles.add(100);
        targetSingalStrengthCycles.add(140);
        targetSingalStrengthCycles.add(180);
        targetSingalStrengthCycles.add(220);
    }

    public void noop() {
        cycle++;
        drawCRT();
        calculateSingalStrength();
    }

    public void addX(int x) {
        // clock 1
        cycle++;
        calculateSingalStrength();
        drawCRT();

        // clock 2
        cycle++;
        calculateSingalStrength();
        drawCRT();
        register += x;
    }

    private void drawCRT() {
        int ROW_SIZE = 40;
        int pos = (cycle - 1) % ROW_SIZE;
        // if meet row size print new line
        if (pos == 0) {
            System.out.println();
        }

        // if overlap print #
        if (pos - 1 <= register && register <= pos + 1
        ) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
    }

    private void calculateSingalStrength() {
        if (targetSingalStrengthCycles.contains(cycle)) {
            signalStrength += (cycle * register);
        }
    }

    public void parseCmd(String cmd) {
        if (cmd.equals("noop")) {
            noop();
        }

        if (cmd.startsWith("addx")) {
            int val = Integer.parseInt(cmd.substring(5));
            addX(val);
        }
    }

    public void readInput() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String cmd = in.nextLine();
            parseCmd(cmd);
        }

        System.out.println(signalStrength);
    }
}
