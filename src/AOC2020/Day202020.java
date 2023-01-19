package AOC2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day202020 {

    Map<Integer, Tile> tileMap = new HashMap<>();

    public class Tile {
        Map<String, String> map = new HashMap();

        public Tile(
                String up,
                String right,
                String bottom,
                String left
        ) {
            map.put("up", up);
            map.put("right", right);
            map.put("bottom", bottom);
            map.put("left", left);
        }

        public List<String> defaultOrientation() {
            List<String> list = new ArrayList<>();
            list.add("up");
            list.add("right");
            list.add("bottom");
            list.add("left");

            return list;
        }

//        public List<String> getAllOrientations() {
//
//        }
    }

    public void solve(List<String> lines) {

    }

    public void bfs(int tile) {

    }
}
