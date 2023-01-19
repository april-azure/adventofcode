package AOC2020;

import Helper.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day202022 {

    public class Player {
        List<Integer> deck = new LinkedList<>();

        public void addCard(int[] vals) {
            for (int val : vals) {
                deck.add(val);
            }
        }

        public int play() {
            int card = deck.remove(0);
            return card;
        }

        public boolean hasNoCard() {
            return deck.size() == 0;
        }

        public int calculatePoint() {
            int score = 0;
            for (int i = 0; i < deck.size(); i++) {
                score += (deck.get(i) * (deck.size() - i));
            }
            return score;
        }
    }

    public void game(Player p1, Player p2) {
        while (!p1.hasNoCard() && !p2.hasNoCard()) {
            int v1 = p1.play();
            int v2 = p2.play();
            if (v1 > v2) {
                p1.addCard(new int[]{v1, v2});
            } else {
                p2.addCard(new int[]{v2, v1});
            }
        }

        if (p1.hasNoCard()) {
            System.out.println(p2.calculatePoint());
        } else {
            System.out.println(p1.calculatePoint());
        }
    }

    public void solve(List<String> lines) {
        int indexForP1 = Utils.getLineIndexStartWith(lines, "Player 1:") + 1;
        int indexForP2 = Utils.getLineIndexStartWith(lines, "Player 2:") + 1;
        Player p1 = createPlayer(lines, indexForP1, indexForP2 - 3);
        Player p2 = createPlayer(lines, indexForP2, lines.size() - 1);

        game(p1, p2);
    }

    private Player createPlayer(List<String> lines, int i, int j) {
        Player p = new Player();
        for (int k = i; k <= j; k++) {
            p.addCard(new int[]{Integer.parseInt(lines.get(k))});
        }
        return p;
    }
}
