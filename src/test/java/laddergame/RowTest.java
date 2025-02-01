package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RowTest {

    @Test
    void row_의_n_번_지점의_방향을_알려준다() {
        Row row = new Row(
            List.of(Direction.LEFT, Direction.RIGHT, Direction.DOWN)
        );

        Direction nextDirection = row.whichDirectionOf(0);

        Assertions.assertThat(nextDirection).isEqualTo(Direction.LEFT);
    }

    @Test
    void row_에_존재하지_않는_지점의_방향을_알려달라고하면_예외를_발생시킨다() {
        Row rowOfThreeLines = new Row(
            List.of(Direction.LEFT, Direction.RIGHT, Direction.DOWN)
        );

        Assertions.assertThatThrownBy(
                () -> rowOfThreeLines.whichDirectionOf(4))
            .hasMessage("존재하지 않는 라인입니다");
    }
}
