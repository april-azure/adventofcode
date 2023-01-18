package Helper;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static void print(List<Integer> list) {
        for (int val : list) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static int[] format(String str) {
        String[] pts = str.split(",");
        int[] res = new int[pts.length];
        for (int i = 0; i < pts.length; i++) {
            res[i] = Integer.parseInt(pts[i]);
        }
        return res;
    }

    public static String convert(int[] vals) {
        StringBuilder sb = new StringBuilder();
        for (int val : vals) {
            sb.append(val);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static int getLineIndexFor(List<String> lines, String exactMatch) {
        for (int i=0;i<lines.size();i++) {
            if (lines.get(i).equals(exactMatch)) {
                return i;
            }
        }
        return -1;
    }

    public static int getLineIndexStartWith(List<String> lines, String startWith) {
        for (int i=0;i<lines.size();i++) {
            if (lines.get(i).startsWith(startWith)) {
                return i;
            }
        }
        return -1;
    }
}
