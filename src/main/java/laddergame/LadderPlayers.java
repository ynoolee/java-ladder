package laddergame;

import java.util.ArrayList;
import java.util.List;

public class LadderPlayers {
    private final List<Player> players;

    public LadderPlayers(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public int size() {
        return this.players.size();
    }

    public Player playerOfLine(int lineNumber) {
        if (players.size() <= lineNumber) {
            throw new IllegalArgumentException("");
        }
        return players.get(lineNumber);
    }

    public List<Player> allPlayers() {
        return List.copyOf(this.players);
    }
}
