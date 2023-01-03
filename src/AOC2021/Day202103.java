package AOC2021;

import java.util.ArrayList;
import java.util.List;

public class Day202103 {
    public void solve(List<String> lines) {
        int cols = lines.get(0).length();
        int rows = lines.size();

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        List<String> list = new ArrayList<>();
        for (String str : lines) {
            list.add(str);
        }

        List<String> oStr = filterWithCommon(list, 0, "oxygen");
        List<String> cStr = filterWithCommon(list, 0, "co2");

        int res = convert(oStr.get(0)) * convert(cStr.get(0));
        System.out.println(res);
    }

    private List<String> filterWithCommon(List<String> numbers, int pos, String mode) {
        if (numbers.size() == 1) {
            return numbers;
        }

        List<String> list = new ArrayList<>();
        int zeroCnt = 0;
        int oneCnt = 0;
        for (String number : numbers) {
            char c = number.charAt(pos);
            if (c == '0') {
                zeroCnt++;
            }
            if (c == '1') {
                oneCnt++;
            }
        }

        if (mode.equals("oxygen")) {
            char filter = oneCnt >= zeroCnt ? '1' : '0';
            list = filterBy(numbers, pos, filter);
        }

        if (mode.equals("co2")) {
            char filter = zeroCnt <= oneCnt ? '0' : '1';
            list = filterBy(numbers, pos, filter);
        }

        return filterWithCommon(list, pos + 1, mode);
    }

    private List<String> filterBy(List<String> list, int pos, char c) {
        return list.stream().filter(item -> item.charAt(pos) == c).toList();
    }

    private int convert(String str) {
        int n = 0;
        for (int i = 0; i < str.length(); i++) {
            n = n * 2 + (str.charAt(i) - '0');
        }
        return n;
    }
}
