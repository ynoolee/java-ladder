package laddergame.view;

import laddergame.ladder.Height;
import laddergame.ladder.Ladder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OutputViewTest {

    private ByteArrayOutputStream testOutputStream;
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        testOutputStream = new ByteArrayOutputStream();
        outputView = new OutputView(new ConsoleWriter(new PrintStream(testOutputStream)));
    }

    @Test
    void 플레이어_이름_입력_요청을_출력한다() {
        outputView.requestToInputPlayerNames();

        var resultOutput = testOutputStream.toString();

        Assertions.assertThat(resultOutput)
            .isEqualTo("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)\n");
    }

    @Test
    void 여러_플레이어_이름을_주어진_순서대로_출력한다() {
        outputView.printNames(List.of("abc", "def", "ge"));

        var resultOutput = testOutputStream.toString();

        Assertions.assertThat(resultOutput).isEqualTo("abc   def   ge    \n");
    }

    @Test
    void Ladder_를_출력한다() {
        var ladder = Ladder.builder().numberOfLines(3).height(new Height(2)).bridgeDecisionMaker(() -> true).build();

        outputView.showLadder(ladder);

        var resultOutput = testOutputStream.toString();

        Assertions.assertThat(resultOutput)
            .isEqualTo("|-----|     |\n|-----|     |\n");
    }
}
