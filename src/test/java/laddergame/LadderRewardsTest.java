package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class LadderRewardsTest {
    @Test
    @DisplayName("유효한 라인넘버로 보상을 찾을 수 있다")
    void test() {
        final LadderRewards rewards = new LadderRewards(List.of(Reward.of("꽝"), Reward.of("1000")));

        final Reward rewardOfLineOne = rewards.rewardOfLine(0);

        Assertions.assertThat(rewardOfLineOne)
            .isEqualTo(Reward.of("꽝"));
    }

    @Test
    @DisplayName("존재하지 않는 라인에 대한 보상을 요청할 경우 에러를 발생한다")
    void test2() {
        final LadderRewards rewards = new LadderRewards(List.of(Reward.of("꽝"), Reward.of("1000")));

        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> rewards.rewardOfLine(3));
    }
}
