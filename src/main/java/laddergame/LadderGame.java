package laddergame;

import laddergame.ladder.BridgeDecisionMaker;
import laddergame.ladder.Height;
import laddergame.ladder.Ladder;

public class LadderGame {

    private final Ladder ladder;

    public LadderGame(final int numberOfLines, final Height height, final BridgeDecisionMaker decisionMaker) {
        this.ladder = Ladder.builder()
            .numberOfLines(numberOfLines)
            .height(height)
            .bridgeDecisionMaker(decisionMaker)
            .build();
    }

    public int run(int lineNumber) {
        return this.ladder.destinationLineOf(lineNumber);
    }

    public Ladder ladder() {
        return this.ladder;
    }
}
