package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LadderTest {

    @Test
    void 사다리_라인은_2개_이상이어야_한다() {
        // 여기서 라인은 세로 라인
        final var numberOfLines = 1;
        final var validHeight = 2;

        Assertions.assertThatThrownBy(() -> new Ladder(numberOfLines, validHeight, () -> new Random().nextBoolean()))
            .hasMessage("사다리 라인은 2개 이상이어야 합니다");
    }


    @Test
    void 사다리의_높이는_1_이상이어야_한다() {
        final var numberOfLines = 2;
        final var invalidHeight = 0;

        Assertions.assertThatThrownBy(() -> new Ladder(numberOfLines, invalidHeight, () -> new Random().nextBoolean()))
            .hasMessage("사다리 높이는 1이상 이어야 합니다");
    }
}
