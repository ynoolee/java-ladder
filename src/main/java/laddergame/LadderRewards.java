package laddergame;

import java.util.ArrayList;
import java.util.List;

public class LadderRewards {
    private final List<Reward> rewards;

    public LadderRewards(final List<Reward> rewards) {
        this.rewards = new ArrayList<>(rewards);
    }

    public Reward rewardOfLine(int lineNumber) {
        if (rewards.size() <= lineNumber) {
            throw new IllegalArgumentException("");
        }
        return rewards.get(lineNumber);
    }

    public int size() {
        return rewards.size();
    }

    public List<Reward> allRewards() {
        return List.copyOf(this.rewards);
    }
}
