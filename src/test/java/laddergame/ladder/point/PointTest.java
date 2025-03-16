package laddergame.ladder.point;


import laddergame.ladder.BridgeDecisionMaker;
import laddergame.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static laddergame.ladder.point.PointsFixture.우측의_포인트와_연결된_포인트_생성;

public class PointTest {

    private BridgeDecisionMaker 조건만_만족하면_항상_브릿지를_생성하는_결정자 = () -> Boolean.TRUE;


    @Test
    @DisplayName("첫 번째 포인트는 왼쪽으로 브릿지를 가질 수 없다")
    void test0() {
        var point = Point.first(조건만_만족하면_항상_브릿지를_생성하는_결정자);

        Assertions.assertThat(point.isLeftConnected())
            .isFalse();
    }

    @Test
    @DisplayName("마지막 포인트는 오른쪽으로 브릿지를 가질 수 없다")
    void test1() {
        var prePoint = Point.first(조건만_만족하면_항상_브릿지를_생성하는_결정자);
        var lastPoint = prePoint.last();

        Assertions.assertThat(lastPoint.isRightConnected())
            .isFalse();
    }


    @Test
    @DisplayName("현재 포인트를 기준으로 연속한 다음 포인트를 생성한다")
    void test2() {
        var prePoint = 우측의_포인트와_연결된_포인트_생성();

        var next = prePoint.next(조건만_만족하면_항상_브릿지를_생성하는_결정자);

        Assertions.assertThat(next)
            .isEqualTo(new Point(true, false));
    }

    @Test
    @DisplayName("양 쪽 브릿지를 가진 포인트 생성은 실패한다")
    void test3() {
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> new Point(true, true));
    }


    @Test
    @DisplayName("Position 을 이동시킨다")
    void test4() {
        var nextPoint = 우측의_포인트와_연결된_포인트_생성();
        var currentPosition = new Position(0, 1);

        var nextPosition = nextPoint.move(currentPosition);
        var expectedPosition = new Position(
            currentPosition.getRowNumber() + 1,
            currentPosition.getLineNumber() + 1
        );

        Assertions.assertThat(nextPosition)
            .isEqualTo(expectedPosition);
    }

    @Test
    @DisplayName("현재 포인트가 주어진 다음포인트와 유효한 관계로 존재할 수 있는지 여부를 알려준다")
    void test5() {
        var 좌측_포인트와_연결되는_포인트 = new Point(true, false);
        var 좌측_포인트와_연결되는_포인트2 = new Point(true, false);

        Assertions.assertThat(좌측_포인트와_연결되는_포인트.isValidRelationWithIfNextIs(좌측_포인트와_연결되는_포인트2))
            .isFalse();
    }
}
