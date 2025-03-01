package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

public class PlayerTest {

    @Test
    @DisplayName("이름이 5글자를 초과할 경우 플레이어 생성에 실패한다")
    void test() {
        final var tooLongName = "abcdefg";

        Assertions.assertThatThrownBy(() -> new Player(tooLongName))
            .hasMessage("플레이어 이름은 공백이 아닌 글자 1글자 이상 5글자 이하여야 합니다");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("플레이어의 이름은 공백일 수 없다")
    void test1(String playerName) {
        Assertions.assertThatThrownBy(() -> new Player(playerName))
            .hasMessage("플레이어 이름은 공백이 아닌 글자 1글자 이상 5글자 이하여야 합니다");
    }
}
