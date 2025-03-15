package laddergame.ladder;

import laddergame.Position;
import laddergame.ladder.point.Point;
import laddergame.ladder.point.PointsFactory;
import lombok.Builder;

import java.util.List;

public class Row {

    private static final int MIN_LINE_COUNT = 2;
    private static final String LESS_THAN_MIN_LINE_COUNT = "최소 2개의 라인이 존재해야 합니다";

    private final List<Point> points;

    public Row(final List<Point> points) {
        validate(points);
        this.points = points;
    }

    @Builder
    public Row(int numberOfLines, PointsFactory pointsFactory) {
        if (numberOfLines < MIN_LINE_COUNT) {
            throw new IllegalArgumentException(LESS_THAN_MIN_LINE_COUNT);
        }
        final List<Point> points = pointsFactory.createRowConsistOf(numberOfLines);
        validate(points);
        this.points = points;
    }

    private void validate(List<Point> points) {
        if (points.isEmpty() || points.size() < MIN_LINE_COUNT) {
            throw new IllegalArgumentException(LESS_THAN_MIN_LINE_COUNT);
        }

        final Point firstPoint = points.get(0);
        if (firstPoint.isLeftConnected()) {
            throw new IllegalArgumentException("첫 번째 원소는 좌측에 브릿지를 가질 수 없습니다");
        }

        for (int i = 0; i < points.size() - 1; i++) {
            var prePoint = points.get(i);
            var nextPoint = points.get(i + 1);

            if (!prePoint.isValidRelationWithIfNextIs(nextPoint)) {
                throw new IllegalArgumentException("유효하지 않은 포인트가 주어졌습니다");
            }
        }
    }

    public boolean isStartOfBridge(final int lineNumber) {
        validateValidLineNumber(lineNumber);

        return points.get(lineNumber).isRightConnected();
    }

    private void validateValidLineNumber(int lineNumber) {
        if (points.isEmpty() || points.size() <= lineNumber) {
            throw new IllegalArgumentException("존재하지 않는 라인입니다");
        }
    }

    public Position nextPositionOf(final int lineNumber, final Position currentPosition) {
        validateValidLineNumber(lineNumber);

        return this.points.get(lineNumber).move(currentPosition);
    }

    public int numberOfLines() {
        return this.points.size();
    }
}
