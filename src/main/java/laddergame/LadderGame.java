package laddergame;

import laddergame.ladder.BridgeDecisionMaker;
import laddergame.ladder.Height;
import laddergame.ladder.Ladder;

import java.util.HashMap;
import java.util.Map;

public class LadderGame {

    private final Ladder ladder;

    public LadderGame(final Ladder ladder) {
        this.ladder = ladder;
    }

    public LadderGame(final int numberOfLines, final Height height, final BridgeDecisionMaker decisionMaker) {
        this.ladder = Ladder.builder()
            .numberOfLines(numberOfLines)
            .height(height)
            .bridgeDecisionMaker(decisionMaker)
            .build();
    }

    public Results runWith(LadderPlayers players, LadderRewards rewards) {
        if (players == null || rewards == null) {
            throw new IllegalArgumentException("게임 실행시 플레이어와 보상은 반드시 필요합니다");
        }
        if (players.size() > ladder.numberOfLines()) {
            throw new IllegalArgumentException("라인 수 보다 많은 플레이어는 불가합니다");
        }
        if (players.size() > rewards.size()) {
            throw new IllegalArgumentException("보상은 반드시 플레이어 수 이상만큼 주어져야 합니다");
        }

        Map<Player, Reward> results = new HashMap<>();

        for (int i = 0; i < ladder.numberOfLines(); i++) {
            final int resultLine = ladder.destinationLineOf(i);
            final Reward reward = rewards.rewardOfLine(resultLine);

            results.put(players.playerOfLine(i), reward);
        }

        return new Results(results);
    }

    public Ladder ladder() {
        return this.ladder;
    }
}
