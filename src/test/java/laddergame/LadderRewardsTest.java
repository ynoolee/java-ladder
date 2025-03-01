package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

class LadderRewardsTest {

    private LadderRewards createRewards(String... rewards) {
        return new LadderRewards(Arrays.stream(rewards)
            .map(Reward::of)
            .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("n 번 라인의 보상이 무엇인지 알려준다")
    void test() {
        final LadderRewards rewards = createRewards("꽝","1000");

        final Reward rewardOfLineOne = rewards.rewardOfLine(0);

        Assertions.assertThat(rewardOfLineOne)
            .isEqualTo(Reward.of("꽝"));
    }

    @Test
    @DisplayName("존재하지 않는 라인에 대한 보상을 요청할 경우 실패한다")
    void test2() {
        final LadderRewards rewards = createRewards("꽝","1000");

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> rewards.rewardOfLine(3));
    }
}
