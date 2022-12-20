//package Day13;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Day13 {
//
//    public class Packet {
//        boolean isInteger = false;
//        int val = 0;
//        List<Packet> list = new ArrayList<>();
//
//        public List<Packet> convertToList() {
//            if (!isInteger) {
//                return list;
//            }
//
////            list.add(val);
//            return list;
//        }
//    }
//
//    public static boolean compare(Packet l, Packet r) {
//        if (l != null && r == null) {
//            return false;
//        }
//
//        if (l == null && r != null) {
//            return true;
//        }
//
//        if (l.isInteger && r.isInteger) {
//            return l.val < r.val;
//        }
//
//        if (!l.isInteger && !r.isInteger) {
//            int i = 0;
//            while (i < l.list.size() && i < r.list.size()) {
//                if (l.list.get(i) < r.list.get(i)) {
//                    return true;
//                }
//            }
//        }
//
//        if (l.isInteger) {
//            l.convertToList();
//        }
//        if (r.isInteger) {
//            r.convertToList();
//        }
//        List<Integer> left = l.list;
//        List<Integer> right = r.list;
//        for (int i = 0; i < left.size() && i < right.size(); i++) {
//            if (left.get(i) > right.get(i)) {
//                return false;
//            }
//        }
//        if (left.size() > right.size()) {
//            return false;
//        }
//        return compare(l.next, r.next);
//    }
//
//    public void solve(List<String> lines) {
//        for (int i = 0; i < lines.size(); i = i + 3) {
//            String firstLine = lines.get(i);
//            String secondLine = lines.get(i + 1);
//
//            Packet left = format(firstLine);
//            Packet right = format(secondLine);
//            if (compare(left, right)) {
//                System.out.println(i / 3);
//            }
//        }
//    }
//
//    private Packet format(String str) {
//        if (str.startsWith("[")) {
//            Packet p = new Packet();
//            p.isInteger = false;
//            p.next = format(str.substring(1, str.length() - 1));
//            return p;
//        }
//
//        return null;
//    }
//}
