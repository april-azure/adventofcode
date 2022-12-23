//import Day16.Day16;
import Y2022.Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/Users/linafang/IdeaProjects/adventofcode/src/test.txt"));
        Day1 day1 = new Day1();
        day1.solve(lines);
    }
}