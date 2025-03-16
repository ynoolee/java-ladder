package laddergame.ladder;

import laddergame.Position;
import laddergame.ladder.point.Point;
import laddergame.ladder.point.PointsFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;

import static laddergame.ladder.point.PointsFixture.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RowTest {

    @Nested
    @DisplayName("라인 개수를 주고 Row 생성 시")
    class RowBuilderValidation {

        @Test
        @DisplayName("라인 개수가 2개 미만이면 생성에 실패한다")
        void whenNumberOfLinesIsLessThanTwo() {
            // given
            int invalidNumberOfLines = 1;

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> Row.builder()
                    .numberOfLines(invalidNumberOfLines)
                    .pointsFactory(new PointsFactory(() -> true))
                    .build());
        }
    }

    @Nested
    @DisplayName("Row 생성에 주어지는 포인트 리스트는 ")
    class RowDirectionListValidation {

        @Test
        @DisplayName("이전포인트가 다음포인트와 연결되어있으면 다음포인트는 이전포인트와 연결되어 있어야 한다")
        void shouldThrowExceptionWhenRightAndLeftAreNotPaired() {
            // given
            List<Point> invalidPair = List.of(
                우측의_포인트와_연결된_포인트_생성(),
                우측의_포인트와_연결된_포인트_생성()
            );

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(invalidPair));
        }

        @Test
        @DisplayName("브릿지가 없는 ROW 생성에 성공한다")
        void test() {
            final var row = new Row(List.of(
                어떤_것과도_연결되지_않은_포인트_생성(),
                어떤_것과도_연결되지_않은_포인트_생성(),
                어떤_것과도_연결되지_않은_포인트_생성()
            ));

            assertThat(row.numberOfLines())
                .isEqualTo(3);
        }

        @Test
        @DisplayName("가장 오른쪽에 브릿지가 있는 Row 생성에 성공한다")
        void test1() {
            final var row = new Row(List.of(
                어떤_것과도_연결되지_않은_포인트_생성(),
                우측의_포인트와_연결된_포인트_생성(),
                좌측의_포인트와_연결된_포인트_생성())
            );

            assertThat(row.numberOfLines())
                .isEqualTo(3);
        }

        @Test
        @DisplayName("가장 왼쪽에 브릿지가 있는 Row 생성에 성공한다")
        void test2() {
            final var row = new Row(List.of(
                우측의_포인트와_연결된_포인트_생성(),
                좌측의_포인트와_연결된_포인트_생성(),
                어떤_것과도_연결되지_않은_포인트_생성()
            ));

            assertThat(row.numberOfLines())
                .isEqualTo(3);
        }
    }

    @Test
    @DisplayName("n 번 라인의 다음 Position 을 알려준다")
    void test6() {
        var 첫번째_두번째_라인에_브릿지가존재하는_ROW = new Row(첫번째_두번째_라인에_브릿지가존재하는_ROW_생성());
        var currentPosition = new Position(0, 1);

        var nextPosition = 첫번째_두번째_라인에_브릿지가존재하는_ROW.nextPositionOf(currentPosition.getLineNumber(), currentPosition);

        Assertions.assertThat(nextPosition)
            .isEqualTo(new Position(
                currentPosition.getRowNumber() + 1,
                currentPosition.getLineNumber() - 1))
        ;
    }

    @Test
    @DisplayName("n 번 라인이 브릿지의 시작인지 여부를 알려준다")
    void test7() {
        var 첫번째_두번째_라인에_브릿지가존재하는_ROW = new Row(첫번째_두번째_라인에_브릿지가존재하는_ROW_생성());

        Assertions.assertThat(첫번째_두번째_라인에_브릿지가존재하는_ROW.isStartOfBridge(0))
            .isTrue();
    }

    @Test
    void 라인_개수를_알려준다() {
        Row 첫번째_두번째_라인에_브릿지가존재하는_ROW = new Row(첫번째_두번째_라인에_브릿지가존재하는_ROW_생성());

        final int numberOfLines = 첫번째_두번째_라인에_브릿지가존재하는_ROW.numberOfLines();

        Assertions.assertThat(numberOfLines).isEqualTo(3);
    }
}
