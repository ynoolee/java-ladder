package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class LadderPlayersTest {

    @Test
    @DisplayName("유효한 라인넘버로 플레이어을 찾을 수 있다")
    void test() {
        final LadderPlayers players = new LadderPlayers(List.of(new Player("joy"), new Player("kelly")));

        final Player playerOfOneLine = players.playerOfLine(0);

        Assertions.assertThat(playerOfOneLine)
            .isEqualTo(new Player("joy"));
    }

    @Test
    @DisplayName("존재하지 않는 라인에 대한 보상을 요청할 경우 에러를 발생한다")
    void test2() {
        final LadderPlayers players = new LadderPlayers(List.of(new Player("joy"), new Player("kelly")));

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> players.playerOfLine(4));
    }
}
