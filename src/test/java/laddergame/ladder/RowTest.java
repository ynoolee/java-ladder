package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RowTest {

    @Nested
    @DisplayName("자동 생성 시")
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
                    .decisionMaker(() -> true)
                    .build());
        }

        @Test
        @DisplayName("브릿지 생성 전략이 주어지지 않으면 생성에 실패한다")
        void whenDecisionMakerIsNull() {
            // given
            int validNumberOfLines = 2;

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> Row.builder()
                    .numberOfLines(validNumberOfLines)
                    .decisionMaker(null)
                    .build());
        }

        @Test
        @DisplayName("연속된 브릿지를 갖지 않는 Row 를 생성한다")
        @RepeatedTest(value = 10)
        void createRandomRow() {
            // given
            int numberOfLinesForPotentialConsecutiveBridges = 5;

            // when
            Row row = Row.builder()
                .numberOfLines(numberOfLinesForPotentialConsecutiveBridges)
                .decisionMaker(() -> true)
                .build();

            // then
            assertThat(row.numberOfLines()).isEqualTo(numberOfLinesForPotentialConsecutiveBridges);
        }
    }

    @Nested
    @DisplayName("Row 생성에 주어지는 포인트 리스트는 ")
    class RowDirectionListValidation {

        @Test
        @DisplayName("연속된 브릿지가 존재할 수 없다")
        void shouldThrowExceptionWhenBridgesAreConsecutive() {
            // given
            List<Direction> consecutiveBridges = List.of(
                Direction.RIGHT, Direction.LEFT, Direction.RIGHT, Direction.RIGHT
            );

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(consecutiveBridges));
        }

        @ParameterizedTest
        @DisplayName("RIGHT와 LEFT는 항상 쌍으로 존재해야 한다")
        @CsvSource({
            "RIGHT,RIGHT",
            "LEFT,LEFT"
        })
        void shouldThrowExceptionWhenRightAndLeftAreNotPaired(
            @ConvertWith(DirectionArgumentsConverter.class)
            Direction left,
            @ConvertWith(DirectionArgumentsConverter.class)
            Direction right
        ) {
            // given
            List<Direction> invalidPair = List.of(left, right);

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(invalidPair));
        }

        @Test
        @DisplayName("RIGHT 가 단독으로 존재할 수 없다")
        void shouldThrowExceptionWhenRightExistsAlone() {
            // given
            List<Direction> rightAlone = List.of(Direction.RIGHT, Direction.NONE);

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(rightAlone));
        }

        @Test
        @DisplayName("LEFT 가 단독으로 존재할 수 없다")
        void shouldThrowExceptionWhenLeftExistsAlone() {
            // given
            List<Direction> leftAlone = List.of(Direction.NONE, Direction.LEFT);

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(leftAlone));
        }


        @Test
        @DisplayName("브릿지가 없는 ROW 생성에 성공한다")
        void test() {
            final var row = new Row(List.of(Direction.NONE, Direction.NONE, Direction.NONE));

            assertThat(row.numberOfLines())
                .isEqualTo(3);
        }

        @Test
        @DisplayName("가장 오른쪽에 브릿지가 있는 Row 생성에 성공한다")
        void test1() {
            final var row = new Row(List.of(Direction.NONE, Direction.RIGHT, Direction.LEFT));

            assertThat(row.numberOfLines())
                .isEqualTo(3);
        }

        @Test
        @DisplayName("가장 왼쪽에 브릿지가 있는 Row 생성에 성공한다")
        void test2() {
            final var row = new Row(List.of(Direction.RIGHT, Direction.LEFT, Direction.NONE));

            assertThat(row.numberOfLines())
                .isEqualTo(3);
        }
    }

    private Row RIGHT_LEFT_NONE_인_ROW_생성() {
        return Row.builder().numberOfLines(3).decisionMaker(() -> Boolean.TRUE).build();
    }

    @Test
    void n_번_라인의_방향을_알려준다() {
        Row RIGHT_LEFT_NONE_ROW = RIGHT_LEFT_NONE_인_ROW_생성();

        Direction nextDirection = RIGHT_LEFT_NONE_ROW.nextMoveDirection(0);

        Assertions.assertThat(nextDirection).isEqualTo(Direction.RIGHT);
    }

    @Test
    void 존재하지_않는_라인의_방향을_물어보면_실패한다() {
        Row RIGHT_LEFT_NONE_ROW = RIGHT_LEFT_NONE_인_ROW_생성();

        Assertions.assertThatThrownBy(
                () -> RIGHT_LEFT_NONE_ROW.nextMoveDirection(4))
            .hasMessage("존재하지 않는 라인입니다");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void n_번_라인이_브릿지의_시작인지_여부를_알려준다(int numberOfLine) {
        Row RIGHT_LEFT_NONE_ROW = RIGHT_LEFT_NONE_인_ROW_생성();

        Assertions.assertThat(RIGHT_LEFT_NONE_ROW.isStartOfBridge(numberOfLine))
            .isFalse();
    }

    @Test
    void 라인_개수를_알려준다() {
        Row RIGHT_LEFT_NONE_ROW = RIGHT_LEFT_NONE_인_ROW_생성();

        final int numberOfLines = RIGHT_LEFT_NONE_ROW.numberOfLines();

        Assertions.assertThat(numberOfLines).isEqualTo(3);
    }
}
