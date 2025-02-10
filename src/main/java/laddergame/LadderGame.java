package laddergame;

public class LadderGame {

    private final Ladder ladder;

    public LadderGame(final int numberOfLines, final int height, final BridgeDecisionMaker decisionMaker) {
        this.ladder = Ladder.builder().numberOfLines(numberOfLines).height(height).bridgeDecisionMaker(decisionMaker).build();
    }

    public int run(int lineNumber) {
        return this.ladder.destinationLineOf(lineNumber);
    }
}
