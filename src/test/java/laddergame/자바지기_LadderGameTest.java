package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class 자바지기_LadderGameTest {

    @Nested
    class 사람_n명_선이_없는_사다리게임 {

        private int[] ladder;

        @BeforeEach
        void setUp() {
            ladder = new int[1];
        }

        @Test
        public void 라인_1_도착지점은_1이다() {
            final OneHeightLadderGame game = new OneHeightLadderGame(ladder);

            final var line = 0;
            final int result = game.run(line);

            Assertions.assertThat(result).isEqualTo(line);
        }
    }

    @Nested
    class 사람_2명_선이_1개인_사다리게임 {

        private int[] matrix;

        @BeforeEach
        void setUp() {
            matrix = new int[2];
            matrix[0] = 1;
            matrix[1] = 1;
        }

        @Test
        public void 라인_1과_2의_도착지점_결과가_뒤바뀐다() {
            final OneHeightLadderGame game = new OneHeightLadderGame(matrix);

            final var lineOne = 0;
            final var lineTwo = 1;

            final int lineOneResult = game.run(lineOne);

            Assertions.assertThat(lineOneResult).isEqualTo(lineTwo);
        }
    }
}
