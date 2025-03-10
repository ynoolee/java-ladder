package laddergame.ladder;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private static final int MIN_LINE_COUNT = 2;
    private static final String LESS_THAN_MIN_LINE_COUNT = "최소 2개의 라인이 존재해야 합니다";
    private static final String EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE = "브릿지 생성 전략이 주어지지 않았습니다";

    private final List<Direction> points;

    public Row(List<Direction> points) {
        validatePoints(points);
        this.points = points;
    }

    @Builder
    private Row(int numberOfLines, BridgeDecisionMaker decisionMaker) {
        validateRowCreationParams(numberOfLines, decisionMaker);

        this.points = createValidatedPoints(numberOfLines, decisionMaker);
    }

    private void validatePoints(List<Direction> points) {
        if (points == null || MIN_LINE_COUNT > points.size()) {
            throw new IllegalArgumentException(LESS_THAN_MIN_LINE_COUNT);
        }

        for (int i = 1; i < points.size(); i++) {
            Direction current = points.get(i);
            Direction previous = points.get(i - 1);
            boolean isLastPosition = (i == points.size() - 1);

            if (!current.isValidPoint(previous, isLastPosition)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateRowCreationParams(int numberOfLines, BridgeDecisionMaker decisionMaker) {
        if (MIN_LINE_COUNT > numberOfLines) {
            throw new IllegalArgumentException(LESS_THAN_MIN_LINE_COUNT);
        }
        if (decisionMaker == null) {
            throw new IllegalArgumentException(EMPTY_BRIDGE_CREATION_STRATEGY_MESSAGE);
        }
    }

    private List<Direction> createValidatedPoints(int numberOfLines, BridgeDecisionMaker decisionMaker) {
        var points = createInitializedPoints(numberOfLines);
        addBridges(points, decisionMaker);

        validatePoints(points);
        return points;
    }

    private List<Direction> createInitializedPoints(int numberOfLines) {
        var initialPoints = new ArrayList<Direction>();
        for (int i = 0; i < numberOfLines; i++) {
            initialPoints.add(Direction.NONE);
        }
        return initialPoints;
    }

    private void addBridges(List<Direction> points, BridgeDecisionMaker decisionMaker) {
        for (int i = 0; i < points.size() - 1; i++) {
            if (isPossibleToMakeBridge(points, i) && decisionMaker.decide()) {
                createBridgeFrom(points, i);
            }
        }
    }

    private void createBridgeFrom(List<Direction> points, int leftIndexOfBridge) {
        var rightIndexOfBridge = leftIndexOfBridge + 1;
        points.set(leftIndexOfBridge, Direction.RIGHT);
        points.set(rightIndexOfBridge, Direction.LEFT);
    }

    private boolean isPossibleToMakeBridge(List<Direction> points, int index) {
        return !isAlreadyBridge(points, index) && !isLast(index, points);
    }

    private boolean isAlreadyBridge(List<Direction> points, int index) {
        return points.get(index) != null && points.get(index).isBridge();
    }

    private boolean isLast(int index, List<Direction> points) {
        return index == points.size() - 1;
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
