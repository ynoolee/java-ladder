package laddergame.ladder.point;

import laddergame.ladder.BridgeDecisionMaker;

import java.util.ArrayList;
import java.util.List;

public class PointsFactory {

    private final BridgeDecisionMaker decisionMaker;

    public PointsFactory(final BridgeDecisionMaker decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public List<Point> createRowConsistOf(int numberOfLines) {
        List<Point> points = new ArrayList<>(numberOfLines);

        Point prePoint = Point.first(decisionMaker);
        points.add(prePoint);
        for (int i = 1; i < numberOfLines - 1; i++) {

            final Point nextPoint = prePoint.next(decisionMaker);
            prePoint = nextPoint;
            points.add(nextPoint);
        }
        points.add(prePoint.last());

        return points;
    }

}
