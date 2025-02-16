package laddergame;

import laddergame.ladder.Height;
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
        public void 라인_1_도착지점은_1이다() {
            final LadderGame game = 사람1명인_사다리게임_생성();

            final var line = 0;
            final int result = game.run(line);

            Assertions.assertThat(result).isEqualTo(line);
        }
    }

    @Nested
    class 사람_2명_선이_1개인_사다리게임 {

        @Test
        public void 라인_1과_2의_도착지점_결과가_뒤바뀐다() {
            final LadderGame game = 사람2명_브릿지1개인_사다리게임_생성();

            final var lineOne = 0;
            final var lineTwo = 1;

            final int lineOneResult = game.run(lineOne);

            Assertions.assertThat(lineOneResult).isEqualTo(lineTwo);
        }
    }

    private LadderGame 사람1명인_사다리게임_생성() {
        return new LadderGame(3, new Height(1), () -> false);
    }

    private LadderGame 사람2명_브릿지1개인_사다리게임_생성() {
        return new LadderGame(3, new Height(1), () -> true);
    }
}
