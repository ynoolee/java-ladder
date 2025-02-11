package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RowTest {


    @Nested
    class RIGHT_LEFT_NONE_으로_구성된_ROW {
        private final Row threeLinesOfRow = Row.create(3, () -> Boolean.TRUE);

        @Test
        void row_의_n_번_지점의_방향을_알려준다() {
            Direction nextDirection = threeLinesOfRow.nextMoveDirection(0);

            Assertions.assertThat(nextDirection).isEqualTo(Direction.RIGHT);
        }

        @Test
        void row_에_존재하지_않는_지점의_방향을_알려달라고하면_예외를_발생시킨다() {
            Assertions.assertThatThrownBy(
                    () -> threeLinesOfRow.nextMoveDirection(4))
                .hasMessage("존재하지 않는 라인입니다");
        }

        @ParameterizedTest
        @ValueSource(ints = {1,2})
        void row_의_n_번_지점이_브릿지의_시작인지_여부를_알려준다(int numberOfLine) {
            Assertions.assertThat(threeLinesOfRow.isStartOfBridge(numberOfLine))
                .isFalse();
        }
    }
}
