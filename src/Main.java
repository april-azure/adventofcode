//import Day16.Day16;

import Y2022.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/linafang/IdeaProjects/adventofcode/src/test.txt"));
//        Day1 day1 = new Day1();
//        day1.solve(lines);
//        Day2 day2 = new Day2();
//        day2.solve(lines);

//        Day3 day3 = new Day3();
//        day3.solve(lines);

//        Day4 day4 = new Day4();
//        day4.solve(lines);

//        Day5 day5 = new Day5();
//        day5.solve(lines);

//        Day6 day6 = new Day6();
//        day6.solve(lines);

        Day23 day23 = new Day23();
        day23.solve(lines);

//        Day25 day25 = new Day25();
//        day25.solve(lines);
    }
}