package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    void 사다리의_높이는_1_이상이다() {
        final var invalidHeight = 0;

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> new Height(invalidHeight));
    }
}
