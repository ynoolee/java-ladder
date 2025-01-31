package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LadderGameTest {

    @Nested
    class 사람_n명_선이_없는_사다리게임 {

        @Test
        public void 라인_1_결과_1을_반환한다() {
            final var numberOfLines = 1;
            final LadderGame game = new LadderGame(numberOfLines);

            final var line = 1;
            final int result = game.run(line);

            Assertions.assertThat(result).isEqualTo(line);
        }
    }

    @Nested
    class 사람_2명_선이_1개인_사다리게임 {
        @Test
        public void 라인_결과가_뒤바뀐다() {
            final LadderGame game = new LadderGame(2);

            final var lineOne = 1;
            final var lineTwo = 2;

            final int lineOneResult = game.run(lineOne);

            Assertions.assertThat(lineOneResult).isEqualTo(lineTwo);
        }
    }
}
