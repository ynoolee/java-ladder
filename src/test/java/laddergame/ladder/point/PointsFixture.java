package laddergame.ladder.point;

import java.util.List;

public class PointsFixture {
    public static Point 우측의_포인트와_연결된_포인트_생성() {
        return new Point(false, true);
    }

    public static Point 좌측의_포인트와_연결된_포인트_생성() {
        return new Point(true, false);
    }

    public static Point 어떤_것과도_연결되지_않은_포인트_생성() {
        return new Point(false, false);
    }

    public static List<Point> 첫번째_두번째_라인에_브릿지가존재하는_ROW_생성() {
        return List.of(
            new Point(false, true),
            new Point(true, false),
            new Point(false, false)
        );
    }
}
