package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DirectionTest {

    @Test
    @DisplayName("Direction 을 통해 Bridge 인지 알 수 있다")
    void test() {
        Direction noneBridge = Direction.NONE;

        Assertions.assertThat(noneBridge.isBridge()).isFalse();
    }
}
