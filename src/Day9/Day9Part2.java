package Day9;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day9Part2 {

    Set<String> visited = new HashSet<>();

    public void parseCmd(String cmd, Knot head) {
        String direction = cmd.substring(0, 1);
        int step = Integer.parseInt(cmd.substring(2));
        for (int i = 0; i < step; i++) {
            if (direction.equals("L")) {
                head.moveLeft();
            } else if (direction.equals("R")) {
                head.moveRight();
            } else if (direction.equals("U")) {
                head.moveUp();
            } else {
                head.moveDown();
            }
        }
    }

    public void cntResult() {
        // add initial point
        visited.add("0,0");
        System.out.println(visited.size());
    }

    public void readInput() {
        Scanner in = new Scanner(System.in);
        Knot head = new Knot(10);
        while (in.hasNext()) {
            String cmd = in.nextLine();
            parseCmd(cmd, head);
        }
    }

    public class Knot {

        enum Type {
            Head, Tail, Node
        }

        Type type = Type.Node;
        Knot next = null;
        int x = 0;
        int y = 0;

        public Knot(int size) {
            this.type = Type.Head;
            int nodeSize = size - 2;
            Knot pre = this;
            for (int i = 0; i < nodeSize; i++) {
                Knot node = new Knot(type.Node, pre);
                pre = node;
            }
            Knot tail = new Knot(Type.Tail, pre);
        }

        public Knot(Type type, Knot pre) {
            this.type = type;
            if (type != Type.Head) {
                pre.next = this;
            }
        }

        private void recordVisited() {
            if (type == Type.Tail) {
                visited.add(x + "," + y);
            }
        }

        public void moveUp() {
            y = y + 1;
            next.keepUp(this);
        }

        public void moveDown() {
            y = y - 1;
            next.keepUp(this);
        }

        public void moveLeft() {
            x = x - 1;
            next.keepUp(this);
        }

        public void moveRight() {
            x = x + 1;
            next.keepUp(this);
        }

        private void keepUp(Knot pre) {
            if (withinReach(pre)) {
                return;
            }

            if (pre.x != x && pre.y != y) {
                // move diagonally
                if (pre.x < x && pre.y > y) {
                    x--;
                    y++;
                } else if (pre.x < x && pre.y < y) {
                    x--;
                    y--;
                } else if (pre.x > x && pre.y > y) {
                    x++;
                    y++;
                } else {
                    x++;
                    y--;
                }
            } else {
                if (pre.x == x) {
                    // move vertically
                    if (pre.y > y) {
                        y++;
                    } else {
                        y--;
                    }
                } else {
                    // move horizontally
                    if (pre.x > x) {
                        x++;
                    } else {
                        x--;
                    }
                }
            }

            if (type != Type.Tail) {
                next.keepUp(this);
            }

            recordVisited();
        }

        private boolean withinReach(Knot pre) {
            return Math.abs(pre.x - x) <= 1 && Math.abs(pre.y - y) <= 1;
        }
    }

    // print for debug
    private void print(Knot head) {
        Set<String> current = new HashSet<>();
        while (head != null) {
            current.add(head.x + "," + head.y);
            head = head.next;
        }

        for (int i = -15; i < 15; i++) {
            for (int j = -15; j < 15; j++) {
                if (current.contains(j + "," + i)) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
}
