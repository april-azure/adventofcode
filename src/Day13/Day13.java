package Day13;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public class Packet {
        boolean isInteger = false;
        int val = 0;
        List<Integer> list = new ArrayList<>();
        List<Packet> next = new ArrayList<>();


    }

    public static boolean compare(Packet l, Packet r) {
        if (l.isInteger && r.isInteger) {
            return l.val < r.val;
        }

        if (!l.isInteger && !r.isInteger) {
            int i = 0;
            while (i < l.list.size() && i < r.list.size()) {
                if (l.list.get(i) < r.list.get(i)) {
                    return true;
                }
            }
        }

        return true;
    }

    public void solve() {

    }
}
