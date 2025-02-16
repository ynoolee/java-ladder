package laddergame.ladder;

import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {

    private static final int MIN_LINE_COUNT = 2;
    private static final String INVALID_LINE_COUNT_MESSAGE = "사다리 라인은 2개 이상이어야 합니다";
    private static final String EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE = "브릿지 생성 전략이 주어지지 않았습니다";

    private final List<Row> rows;

    @Builder
    private Ladder(final int numberOfLines, Height height, BridgeDecisionMaker bridgeDecisionMaker) {
        validateInputs(numberOfLines, bridgeDecisionMaker);

        this.rows = createRows(numberOfLines, height.getHeight(), bridgeDecisionMaker);
    }

    private void validateInputs(
        final int numberOfLines, final BridgeDecisionMaker bridgeDecisionMaker
    ) {
        if (numberOfLines < MIN_LINE_COUNT) {
            throw new IllegalArgumentException(INVALID_LINE_COUNT_MESSAGE);
        }

        if (bridgeDecisionMaker == null) {
            throw new IllegalArgumentException(EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE);
        }
    }

    private List<Row> createRows(final int numberOfLines, final int height, final BridgeDecisionMaker bridgeDecisionMaker) {
        return Stream.generate(() -> Row.builder().numberOfLines(numberOfLines).decisionMaker(bridgeDecisionMaker).build())
            .limit(height)
            .collect(Collectors.toList());
    }

    // todo : 1층에 대해서만 하는 상태
    public int destinationLineOf(int startLine) {
        var resultLine = startLine;

        var nextDirection = rowOfHeight(0).nextMoveDirection(startLine);
        if (nextDirection.isBridge()) {
            if (Direction.LEFT.equals(nextDirection)) {
                resultLine--;
            } else if (Direction.RIGHT.equals(nextDirection)) {
                resultLine++;
            }
        }

        return resultLine;
    }

    public Row rowOfHeight(int height) {
        if (height >= height()) {
            throw new IllegalArgumentException("사다리에 존재하지 않는 높이입니다");
        }
        return this.rows.get(height);
    }

    public int height() {
        return rows.size();
    }
}
