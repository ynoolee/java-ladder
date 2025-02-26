package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Row 클래스 테스트")
class RowTest {

    @Nested
    @DisplayName("Row 생성 검증 - Builder 패턴 사용 시")
    class RowBuilderValidation {

        @Test
        @DisplayName("라인 개수가 2개 미만이면 예외가 발생한다")
        void shouldThrowExceptionWhenNumberOfLinesIsLessThanTwo() {
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
        @DisplayName("브릿지 생성 전략이 null이면 예외가 발생한다")
        void shouldThrowExceptionWhenDecisionMakerIsNull() {
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
        @DisplayName("유효한 파라미터로 Row가 정상 생성된다")
        void shouldCreateRowWithValidParameters() {
            // given
            int validNumberOfLines = 3;

            // when
            Row row = Row.builder()
                .numberOfLines(validNumberOfLines)
                .decisionMaker(() -> true)
                .build();

            // then
            assertThat(row.numberOfLines()).isEqualTo(validNumberOfLines);
        }
    }

    @Nested
    @DisplayName("Row 생성 검증 - Direction 리스트 사용 시")
    class RowDirectionListValidation {

        @Test
        @DisplayName("연속된 브릿지는 존재할 수 없다")
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
        void shouldThrowExceptionWhenRightAndLeftAreNotPaired(Direction first, Direction second) {
            // given
            List<Direction> invalidPair = List.of(first, second);

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(invalidPair));
        }

        @Test
        @DisplayName("RIGHT는 단독으로 존재할 수 없다")
        void shouldThrowExceptionWhenRightExistsAlone() {
            // given
            List<Direction> rightAlone = List.of(Direction.RIGHT, Direction.NONE);

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(rightAlone));
        }

        @Test
        @DisplayName("LEFT는 단독으로 존재할 수 없다")
        void shouldThrowExceptionWhenLeftExistsAlone() {
            // given
            List<Direction> leftAlone = List.of(Direction.NONE, Direction.LEFT);

            // when & then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(leftAlone));
        }

        @ParameterizedTest
        @DisplayName("다양한 유효한 Row 생성 케이스")
        @CsvSource({
            "'NONE,NONE,NONE', 3, '브릿지가 없는 Row'",
            "'NONE,RIGHT,LEFT', 3, '가장 오른쪽에 브릿지가 있는 Row'",
            "'RIGHT,LEFT,NONE', 3, '가장 왼쪽에 브릿지가 있는 Row'"
        })
        void shouldCreateValidRows(String directionString, int expectedNumberOfLines, String description) {
            // given
            List<Direction> directions = parseDirections(directionString);

            // when
            Row row = new Row(directions);

            // then
            assertThat(row.numberOfLines()).isEqualTo(expectedNumberOfLines);
        }

        private List<Direction> parseDirections(String directionString) {
            return List.of(directionString.split(",")).stream()
                .map(Direction::valueOf)
                .collect(Collectors.toList());
        }
    }

    @Nested
    class RIGHT_LEFT_NONE_으로_구성된_ROW {
        private final Row RIGHT_LEFT_NONE_ROW = RIGHT_LEFT_NONE_인_ROW_생성();

        @Test
        void row_의_n_번_지점의_방향을_알려준다() {
            Direction nextDirection = RIGHT_LEFT_NONE_ROW.nextMoveDirection(0);

            Assertions.assertThat(nextDirection).isEqualTo(Direction.RIGHT);
        }

        @Test
        void row_에_존재하지_않는_지점의_방향을_알려달라고하면_예외를_발생시킨다() {
            Assertions.assertThatThrownBy(
                    () -> RIGHT_LEFT_NONE_ROW.nextMoveDirection(4))
                .hasMessage("존재하지 않는 라인입니다");
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        void row_의_n_번_지점이_브릿지의_시작인지_여부를_알려준다(int numberOfLine) {
            Assertions.assertThat(RIGHT_LEFT_NONE_ROW.isStartOfBridge(numberOfLine))
                .isFalse();
        }

        @Test
        void row_의_라인수를_알려준다() {
            final int numberOfLines = RIGHT_LEFT_NONE_ROW.numberOfLines();

            Assertions.assertThat(numberOfLines).isEqualTo(3);
        }
    }

    private Row RIGHT_LEFT_NONE_인_ROW_생성() {
        return Row.builder().numberOfLines(3).decisionMaker(() -> Boolean.TRUE).build();
    }
}
