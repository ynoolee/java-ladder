package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RowTest {

    @Test
    @DisplayName("Row 는 2개 이상 라인이 필요하다")
    void test() {
        var numberOfLine = 1;

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> Row.builder().numberOfLines(numberOfLine).decisionMaker(() -> true).build());
    }

    @Test
    @DisplayName("Row 의 브릿지 생성 전략이 필요하다")
    void test2() {
        var anyNumberOfLine = 2;

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> Row.builder().numberOfLines(anyNumberOfLine).decisionMaker(null).build());
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
