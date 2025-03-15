package laddergame;

import laddergame.ladder.Height;
import laddergame.ladder.Ladder;
import laddergame.ladder.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static laddergame.ladder.point.PointsFixture.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LadderGameTest {

    private LadderPlayers createPlayers(String... names) {
        return new LadderPlayers(Arrays.stream(names)
            .map(Player::new)
            .collect(Collectors.toList())
        );
    }

    private LadderRewards createRewards(String... rewards) {
        return new LadderRewards(Arrays.stream(rewards)
            .map(Reward::of)
            .collect(Collectors.toList()))
            ;
    }

    private LadderGame 사람n명인_사다리게임_생성() {
        /*
         * |   |   |
         * */
        return new LadderGame(
            new Ladder(List.of(
                new Row(List.of(
                    어떤_것과도_연결되지_않은_포인트_생성(),
                    어떤_것과도_연결되지_않은_포인트_생성(),
                    어떤_것과도_연결되지_않은_포인트_생성())
                )
            ), 3)
        );
    }

    private LadderGame 사람2명_브릿지1개인_사다리게임_생성() {
        return new LadderGame(2, new Height(1), () -> true);
    }

    private LadderGame 사람2명_높이2_브릿지2개인_사다리게임_생성() {
        /**
         * |---|
         * |---|
         * */
        return new LadderGame(
            new Ladder(List.of(
                new Row(List.of(우측의_포인트와_연결된_포인트_생성(), 좌측의_포인트와_연결된_포인트_생성())),
                new Row(List.of(우측의_포인트와_연결된_포인트_생성(), 좌측의_포인트와_연결된_포인트_생성()))
            ), 2)
        );
    }

    private LadderGame 사람3명_높이2_브릿지2개가_엇갈려있는_사다리게임_생성() {
        /**
         * |---|   |
         * |   |---|
         * */
        return new LadderGame(
            new Ladder(List.of(
                new Row(List.of(우측의_포인트와_연결된_포인트_생성(), 좌측의_포인트와_연결된_포인트_생성(), 어떤_것과도_연결되지_않은_포인트_생성())),
                new Row(List.of(어떤_것과도_연결되지_않은_포인트_생성(), 우측의_포인트와_연결된_포인트_생성(), 좌측의_포인트와_연결된_포인트_생성()))
            ), 3)
        );
    }

    @Test
    @DisplayName("플레이어의 수 보다 적은 수의 보상을 주면서 게임을 실행할 경우 에러가 발생한다")
    void test() {
        final var ladderGame = 사람n명인_사다리게임_생성();

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() ->
                ladderGame.runWith(
                    createPlayers("jose", "abc"),
                    createRewards("1000"))
            );
    }

    @Nested
    class 사람_n명_선이_없는_사다리게임 {

        private final LadderGame game = 사람n명인_사다리게임_생성();

        @Test
        public void 라인_0_도착지점은_0이다() {
            final var result = game.runWith(
                createPlayers("play1", "play2", "play3"),
                createRewards("line1", "line2", "line3")
            );

            Assertions.assertThat(result.resultOf("play1"))
                .isEqualTo(Reward.of("line1"));
        }
    }

    @Nested
    class 사람_2명_선이_1개인_사다리게임 {
        private final LadderGame game = 사람2명_브릿지1개인_사다리게임_생성();

        @Test
        public void 라인_0과_1의_도착지점_결과가_뒤바뀐다() {
            final var result = game.runWith(
                createPlayers("play1", "play2"),
                createRewards("line1", "line2")
            );

            Assertions.assertThat(result.resultOf("play1"))
                .isEqualTo(Reward.of("line2"));
        }
    }

    @Nested
    class 사람_2명_높이_2_선이_2개인_사다리게임 {
        private final LadderGame game = 사람2명_높이2_브릿지2개인_사다리게임_생성();

        @Test
        public void 라인_1의_결과는_1이다() {
            final var result = game.runWith(
                createPlayers("play1", "play2"),
                createRewards("line1", "line2")
            );

            Assertions.assertThat(result.resultOf("play1"))
                .isEqualTo(Reward.of("line1"));
        }
    }

    @Nested
    class 사람_3명_높이_2_선이_2개가_엇갈려있는_사다리게임 {
        /**
         * |---|   |
         * |   |---|
         */

        private final LadderGame game = 사람3명_높이2_브릿지2개가_엇갈려있는_사다리게임_생성();

        @Test
        public void 라인_0_의_결과는_2이다() {
            final var result = game.runWith(
                createPlayers("play1", "play2", "play3"),
                createRewards("line1", "line2", "line3")
            );

            Assertions.assertThat(result.resultOf("play1"))
                .isEqualTo(Reward.of("line3"));
        }
    }
}
