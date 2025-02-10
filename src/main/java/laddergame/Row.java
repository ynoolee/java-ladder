package laddergame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Row {

    private final List<Direction> points;

    // todo : directions 라는 네이밍이 잘못됐음
    Row(List<Direction> directions) {
        this.points = directions;
    }

    public static Row create(int numberOfLines, BridgeDecisionMaker decisionMaker) {
        Direction[] points = init(numberOfLines);

        for (int idx = 0; idx < numberOfLines; idx++) {
            if (isPossibleToMakeBridge(idx, points) && decisionMaker.decide()) {
                createBridgeAt(idx, points);
            }
        }

        return new Row(Arrays.stream(points).collect(Collectors.toList()));
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
        points[index] = Direction.LEFT;
        points[index + 1] = Direction.RIGHT;
    }

    public Direction nextMoveDirection(final int lineNumber) {
        if (points.isEmpty() || points.size() <= lineNumber) {
            throw new IllegalArgumentException("존재하지 않는 라인입니다");
        }
        return points.get(lineNumber);
    }
}
