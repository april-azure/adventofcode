import AOC2021.Day202109;
import Day10.Day10;
import Day11.Day11;
import Day12.Day12;
import Day14.Day14;
import Day15.Day15;
import Day9.Day9;
import Day9.Day9Part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/linafang/IdeaProjects/adventofcode/src/test.txt"));
//        Day7 day7 = new Day7();
//        day7.findTotalSizeSmallerThan10000();

//        Day8.findHighestScenicScore();

//        Day9 day9 = new Day9();
//        day9.readInput();
//        day9.printVisitedTailPosCount();

//        Day9Part2 part2 = new Day9Part2();
//        part2.readInput();
//        part2.cntResult();

//        Day10 day10 = new Day10();
//        day10.readInput();

//        Day11 day11 = new Day11();
//        day11.solve();

//        Day12 day12 = new Day12();
//        day12.solve();

//        Day14 day14 = new Day14();
//        day14.solve(lines);

//        Day202109 day202109 = new Day202109();
//        day202109.solve(lines);

        Day15 day15 = new Day15();
        day15.solve(lines);
    }
}