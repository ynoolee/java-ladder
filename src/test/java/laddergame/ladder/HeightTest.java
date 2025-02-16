package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    void 사다리의_높이는_1_이상이어야_한다() {
        final var invalidHeight = 0;

        Assertions.assertThatThrownBy(() -> new Height(invalidHeight))
            .hasMessage("사다리 높이는 1이상 이어야 합니다");
    }
}
