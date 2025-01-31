package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LadderGameTest {

    @Nested
    class 사람_1명_높이_1인_사다리게임 {
        final LadderGame game = new LadderGame(1);

        @Test
        public void 라인_1_결과_1을_반환한다() {
            final var line = 1;
            final int result = game.run(line);

            Assertions.assertThat(result).isEqualTo(line);
        }
    }
}
