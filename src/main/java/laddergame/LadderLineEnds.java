package laddergame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderLineEnds {
    private final Map<Player, Integer> lineMappedPlayers;
    private final List<Reward> rewards;

    public LadderLineEnds(final List<Player> players, final List<Reward> rewards) {
        this.lineMappedPlayers = createPlayerLines(players);
        this.rewards = List.copyOf(rewards);
    }

    private Map<Player, Integer> createPlayerLines(List<Player> players) {
        Map<Player, Integer> map = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            map.put(players.get(i), i);
        }
        return Map.copyOf(map);
    }


    public int lineOfPlayer(final Player player) {
        Integer line = lineMappedPlayers.get(player);
        if (line == null) {
            throw new IllegalArgumentException("Invalid player");
        }
        return line + 1;
    }

    public Reward rewardOfLine(final int numberOfLine) {
        var convertedNumberOfLine  = numberOfLine - 1;
        if (convertedNumberOfLine < 0  || convertedNumberOfLine > (rewards.size() - 1) ) {
            throw new IllegalArgumentException("Invalid line number");
        }
        return rewards.get(convertedNumberOfLine);
    }
}
