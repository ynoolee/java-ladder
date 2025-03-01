package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

class LadderPlayersTest {

    private LadderPlayers createPlayers(String... names) {
        return new LadderPlayers(Arrays.stream(names)
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("특정 라인에서 시작하는 플레이어가 누구인지 알려준다")
    void test() {
        final LadderPlayers players = createPlayers("joy", "kelly");

        final Player playerOfOneLine = players.playerOfLine(0);

        Assertions.assertThat(playerOfOneLine)
            .isEqualTo(new Player("joy"));
    }

    @Test
    @DisplayName("존재하지 않는 라인에서 시작하는 플레이어를 질의하면 실패한다")
    void test4() {
        final LadderPlayers players = createPlayers("joy", "kelly");

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> players.playerOfLine(5))
            ;
    }
}
