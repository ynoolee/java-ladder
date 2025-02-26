package laddergame;

import java.util.Map;

public class Results {
    private final Map<Player, Reward> result;

    public Results(final Map<Player, Reward> result) {
        this.result = Map.copyOf(result);
    }

    public Reward resultOf(Player player) {
        return result.get(player);
    }

    public Reward resultOf(String player) {
        return result.get(new Player(player));
    }

    public Map<Player, Reward> allResults() {
        return Map.copyOf(result);
    }
}
