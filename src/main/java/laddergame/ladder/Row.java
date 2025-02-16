package laddergame.ladder;

import lombok.Builder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Row {

    private static final int MIN_LINE_COUNT = 2;
    private static final String LESS_THAN_MIN_LINE_COUNT = "최소 2개의 라인이 존재해야 합니다";
    private static final String EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE = "브릿지 생성 전략이 주어지지 않았습니다";
    private final List<Direction> points;

    @Builder
    private Row(int numberOfLines, BridgeDecisionMaker decisionMaker) {
        if (MIN_LINE_COUNT > numberOfLines) {
            throw new IllegalArgumentException(LESS_THAN_MIN_LINE_COUNT);
        }
        if (decisionMaker == null) {
            throw new IllegalArgumentException(EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE);
        }
        var points = init(numberOfLines);

        for (var pointIndex = 0; pointIndex < numberOfLines; pointIndex++) {
            if (isPossibleToMakeBridge(pointIndex, points) && decisionMaker.decide()) {
                createBridgeAt(pointIndex, points);
            }
        }

        this.points = Arrays.stream(points).collect(Collectors.toList());
    }

    private static Direction[] init(int numberOfLines) {
        return Stream.generate(() -> Direction.NONE)
            .limit(numberOfLines)
            .collect(Collectors.toList())
            .toArray(new Direction[numberOfLines]);
    }

    private static boolean isPossibleToMakeBridge(int index, Direction... points) {
        return !isAlreadyBridge(index, points) && !isLast(index, points);
    }

    private static boolean isAlreadyBridge(int index, Direction... points) {
        return points[index] != null && points[index].isBridge();
    }

    private static boolean isLast(int index, Direction... points) {
        return index == points.length - 1;
    }

    private static void createBridgeAt(int index, Direction... points) {
        points[index] = Direction.RIGHT;
        points[index + 1] = Direction.LEFT;
    }

    public Direction nextMoveDirection(final int lineNumber) {
        if (points.isEmpty() || points.size() <= lineNumber) {
            throw new IllegalArgumentException("존재하지 않는 라인입니다");
        }
        return points.get(lineNumber);
    }

    public boolean isStartOfBridge(final int lineNumber) {
        if (points.isEmpty() || points.size() <= lineNumber) {
            throw new IllegalArgumentException("존재하지 않는 라인입니다");
        }

        return Direction.RIGHT.equals(points.get(lineNumber));
    }

    public int numberOfLines() {
        return this.points.size();
    }
}
