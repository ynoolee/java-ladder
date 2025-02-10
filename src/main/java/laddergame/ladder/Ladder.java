package laddergame.ladder;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {

    private static final int MIN_LINE_COUNT = 2;
    private static final int MIN_HEIGHT = 1;
    private static final String INVALID_LINE_COUNT_MESSAGE = "사다리 라인은 2개 이상이어야 합니다";
    private static final String INVALID_HEIGHT_MESSAGE = "사다리 높이는 1이상 이어야 합니다";
    public static final String EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE = "브릿지 생성 전략이 주어지지 않았습니다";

    private final int numberOfLines;
    private final int height;
    private final List<Row> rows;


    @Builder(access = AccessLevel.PUBLIC)
        // todo : 빌더만 접근 가능, 생성자는 접근 불가하도록 변경
    Ladder(final int numberOfLines, final int height, BridgeDecisionMaker bridgeDecisionMaker) {
        if (numberOfLines < MIN_LINE_COUNT) {
            throw new IllegalArgumentException(INVALID_LINE_COUNT_MESSAGE);
        }
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_HEIGHT_MESSAGE);
        }

        if (bridgeDecisionMaker == null) {
            throw new IllegalArgumentException(EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE);
        }

        this.numberOfLines = numberOfLines;
        this.height = height;
        this.rows = createRows(numberOfLines, height, bridgeDecisionMaker);
    }

    private static List<Row> createRows(final int numberOfLines, final int height, final BridgeDecisionMaker bridgeDecisionMaker) {
        return Stream.generate(() -> Row.create(numberOfLines, bridgeDecisionMaker))
            .limit(height)
            .collect(Collectors.toList());
    }

    public int destinationLineOf(int startLine) {
        int resultLine = startLine;

        Direction nextDirection = rowOfHeight(0).nextMoveDirection(startLine);
        if (nextDirection.isBridge()) {
            if (Direction.LEFT.equals(nextDirection)) {
                resultLine--;
            } else if (Direction.RIGHT.equals(nextDirection)) {
                resultLine++;
            }
        }

        return resultLine;
    }

    private Row rowOfHeight(int height) {
        if (height > this.height) {
            throw new IllegalArgumentException("사다리에 존재하지 않는 높이입니다");
        }
        return this.rows.get(height);
    }
}
