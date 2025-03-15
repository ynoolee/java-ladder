package laddergame.ladder.point;

import laddergame.Position;
import laddergame.ladder.BridgeDecisionMaker;

import java.util.Objects;

public class Point {

    private final boolean isLeftConnected;
    private final boolean isRightConnected;

    Point(final boolean isLeftConnected, final boolean isRightConnected) {
        if (isLeftConnected && isRightConnected) {
            throw new IllegalArgumentException("연속한 브릿지가 존재할 수 없습니다");
        }
        this.isLeftConnected = isLeftConnected;
        this.isRightConnected = isRightConnected;
    }

    static Point first(BridgeDecisionMaker bridgeDecider) {
        return new Point(false, bridgeDecider.decide());
    }

    Point next(BridgeDecisionMaker bridgeDecider) {
        if (isRightConnected()) {
            return new Point(true, false);
        }

        return new Point(false, bridgeDecider.decide());
    }

    Point last() {
        if (isRightConnected()) {
            return new Point(true, false);
        }

        return new Point(false, false);
    }

    public final boolean isLeftConnected() {
        return isLeftConnected;
    }

    public final boolean isRightConnected() {
        return isRightConnected;
    }

    public final Position move(final Position current) {
        if (isLeftConnected) {
            return current.moveLeftAndDown();
        }

        if (isRightConnected) {
            return current.moveRightAndDown();
        }

        return current.moveDown();
    }

    public boolean isValidRelationWithIfNextIs(Point nextPoint) {
        return this.isRightConnected && nextPoint.isLeftConnected()
            || !this.isRightConnected && !nextPoint.isLeftConnected();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        final Point point = (Point) o;
        return isLeftConnected == point.isLeftConnected && isRightConnected == point.isRightConnected;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLeftConnected, isRightConnected);
    }
}
