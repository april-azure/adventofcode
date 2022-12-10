package Day9;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day9 {
    int headX;
    int headY;
    int tailX;
    int tailY;

    Set<String> visited = new HashSet<>();

    private void recordTailVisit() {
        visited.add(tailX + "," + tailY);
    }

    public void moveUp() {
        headY++;
        if (headY > tailY + 1) {
            tailY = headY - 1;
            tailX = headX;
        }
        recordTailVisit();
    }

    public void moveDown() {
        headY--;
        if (headY < tailY - 1) {
            tailY = headY + 1;
            tailX = headX;
        }
        recordTailVisit();
    }

    public void moveLeft() {
        headX--;
        if (headX < tailX - 1) {
            tailX = headX + 1;
            tailY = headY;
        }
        recordTailVisit();
    }

    public void moveRight() {
        headX++;
        if (headX > tailX + 1) {
            tailX = headX - 1;
            tailY = headY;
        }
        recordTailVisit();
    }

    public void parseCmd(String cmd) {
        String direction = cmd.substring(0, 1);
        int step = Integer.parseInt(cmd.substring(2));
        for (int i = 0; i < step; i++) {
            if (direction.equals("L")) {
                moveLeft();
            } else if (direction.equals("R")) {
                moveRight();
            } else if (direction.equals("U")) {
                moveUp();
            } else {
                moveDown();
            }
//            System.out.println(tailX + "," +tailY);
        }
    }

    public void readInput() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String cmd = in.nextLine();
            parseCmd(cmd);
        }
    }

    public void printVisitedTailPosCount() {
        System.out.println("visited " + visited.size());
    }
}
