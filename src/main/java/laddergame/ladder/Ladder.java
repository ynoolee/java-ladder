package laddergame.ladder;

import laddergame.Position;
import laddergame.ladder.point.PointsFactory;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {

    private static final int MIN_LINE_COUNT = 2;
    private static final String INVALID_LINE_COUNT_MESSAGE = "사다리 라인은 2개 이상이어야 합니다";
    private static final String EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE = "브릿지 생성 전략이 주어지지 않았습니다";

    private final Rows rows;
    private final int numberOfLines;

    public Ladder(final List<Row> rows, int numberOfLines) {
        this.rows = new Rows(rows);
        this.numberOfLines = numberOfLines;
    }

    @Builder
    private Ladder(final int numberOfLines, Height height, BridgeDecisionMaker bridgeDecisionMaker) {
        validateInputs(numberOfLines, bridgeDecisionMaker);
        this.numberOfLines = numberOfLines;
        this.rows = new Rows(createRows(numberOfLines, height.getHeight(), bridgeDecisionMaker));
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

    private static List<Row> createRows(final int numberOfLines, final int height, final BridgeDecisionMaker bridgeDecisionMaker) {
        return Stream.generate(() -> Row.builder()
                .numberOfLines(numberOfLines)
                .pointsFactory(new PointsFactory(bridgeDecisionMaker))
                .build())
            .limit(height)
            .collect(Collectors.toList());
    }

    public int destinationLineOf(int startLine) {
        Position currentPosition = new Position(0, startLine);
        int currentLine = startLine;

        for (Row row : rows.rows()) {
            currentPosition = row.nextPositionOf(currentLine, currentPosition);
            currentLine = currentPosition.getLineNumber();
        }

        return currentLine;
    }


    public List<Row> allLadderRows() {
        return this.rows.rows();
    }

    public int height() {
        return this.rows.size();
    }

    public int numberOfLines() {
        return this.numberOfLines;
    }
}
