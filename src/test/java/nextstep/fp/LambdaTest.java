package nextstep.fp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LambdaTest {
    private List<Integer> numbers;

    @BeforeEach
    public void setup() {
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void printAllOld() throws Exception {
        Lambda.printAllOld(numbers);
    }

    @Test
    public void printAllLambda() throws Exception {
        Lambda.printAllLambda(numbers);
    }

    @Test
    public void runThread() throws Exception {
        Lambda.runThread();
    }

    // ==== sumXX ===
    // 1) 익명클래스 사용 버전 2) lambda 구현버전
    @Test
    public void sumAll() throws Exception {
        int sum = Lambda.sumOnlyConditionSatisfiedNumbers(numbers, new Lambda.NumberConditional() {
            @Override
            public boolean isSatisfied(final int number) {
                return true;
            }
        });
        assertThat(sum).isEqualTo(21);
    }

    @Test
    public void sumAll_with_lambda() throws Exception {
        int sum = Lambda.sumOnlyConditionSatisfiedNumbers(numbers, (int number) -> true);
        assertThat(sum).isEqualTo(21);
    }


    @Test
    public void sumAllEven() throws Exception {
        int sum = Lambda.sumOnlyConditionSatisfiedNumbers(numbers, new Lambda.NumberConditional() {
            @Override
            public boolean isSatisfied(final int number) {
                return number % 2 == 0;
            }
        });
        assertThat(sum).isEqualTo(12);
    }

    @Test
    public void sumAllEven_with_lambda() throws Exception {
        int sum = Lambda.sumOnlyConditionSatisfiedNumbers(numbers, number -> number % 2 == 0);
        assertThat(sum).isEqualTo(12);
    }

    @Test
    public void sumAllOverThree() throws Exception {
        int sum = Lambda.sumOnlyConditionSatisfiedNumbers(numbers, new Lambda.NumberConditional() {
            @Override
            public boolean isSatisfied(final int number) {
                return number > 3;
            }
        });
        assertThat(sum).isEqualTo(15);
    }

    @Test
    public void sumAllOverThree_with_lambda() throws Exception {
        int sum = Lambda.sumOnlyConditionSatisfiedNumbers(numbers, number -> number > 3);
        assertThat(sum).isEqualTo(15);
    }
}
