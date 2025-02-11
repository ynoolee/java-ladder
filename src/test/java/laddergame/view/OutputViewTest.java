package laddergame.view;

import laddergame.Player;
import laddergame.ladder.Ladder;
import laddergame.ladder.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class OutputViewTest {

    private ByteArrayOutputStream testOutputStream;
    private OutputView output;

    @BeforeEach
    void setUp() {
        testOutputStream = new ByteArrayOutputStream();
        output = new OutputView(new ConsoleWriter(new PrintStream(testOutputStream)));
    }

    @Test
    void 플레이어_이름_입력_요청을_출력한다() {
        output.printInputPlayerNames();

        final String result = testOutputStream.toString();

        Assertions.assertThat(result)
            .isEqualTo("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)\n");
    }

    @Test
    void 여러_플레이어_이름을_주어진_순서대로_출력한다() {
        output.printNames(List.of(new Player("abc"), new Player("def"), new Player("ge")));

        Assertions.assertThat(testOutputStream.toString()).isEqualTo("abc  def  ge   \n");
    }

    @Nested
    class Row_가_주어졌을때 {

        private final Row RIGHT_LEFT_NONE_ROW = Row.create(3, () -> true);

        @Test
        @DisplayName("브릿지는 5개의 유닛으로 구성하여 출력한다")
        void test() {
            output.printRow(RIGHT_LEFT_NONE_ROW);

            Assertions.assertThat(testOutputStream.toString())
                .isEqualTo("|-----|     |\n");
        }
    }

    @Test
    void Ladder_를_출력한다() {
        Ladder ladder = Ladder.builder().numberOfLines(3).height(2).bridgeDecisionMaker(() -> true).build();

        output.printLadder(ladder);

        Assertions.assertThat(testOutputStream.toString())
            .isEqualTo("|-----|     |\n|-----|     |\n");
    }
}
