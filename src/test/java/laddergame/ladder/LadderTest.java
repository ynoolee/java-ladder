package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LadderTest {

    private Ladder createLadder(int numberOfLines, final Height height) {
        return Ladder.builder()
            .numberOfLines(numberOfLines)
            .height(height)
            .bridgeDecisionMaker(() -> new Random().nextBoolean())
            .build();
    }

    private Ladder 라인개수3인_사다리를_생성(int height) {
        return Ladder.builder()
            .numberOfLines(3)
            .height(new Height(height))
            .bridgeDecisionMaker(() -> new Random().nextBoolean())
            .build();
    }

    @Test
    void 사다리_라인은_2개_이상이다() {
        // 여기서 라인은 세로 라인
        final var numberOfLines = 1;
        final var validHeight = new Height(2);

        Assertions.assertThatThrownBy(() -> createLadder(numberOfLines, validHeight))
            .hasMessage("사다리 라인은 2개 이상이어야 합니다");
    }

    @Test
    void 사다리의_높이를_알려준다() {
        // given
        final var inputHeight = 3;
        final var ladder = 라인개수3인_사다리를_생성(inputHeight);

        // when
        final var height = new Height(ladder.height());

        // then
        Assertions.assertThat(height).isEqualTo(new Height(inputHeight));
    }
}
