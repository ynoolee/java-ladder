package laddergame.ladder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RowsTest {

    private Row createRowConsistOfNumberOfLine(int numberOfLine) {
        return Row.builder().numberOfLines(numberOfLine).decisionMaker(() -> true).build();
    }

    @Test
    @DisplayName("서로 다른 개수의 라인으로 이루어진 Row 로 생성 시도시 에러가 발생한다")
    void test() {
        Assertions.assertThatIllegalArgumentException()
            .isThrownBy(() -> new Rows(List.of(
                createRowConsistOfNumberOfLine(2),
                createRowConsistOfNumberOfLine(3))
            ))
        ;
    }

    @Test
    @DisplayName("1 개 이상의 Row 로 이루어진 Rows 를 생성한다")
    void test1() {
        final Rows rows = new Rows(List.of(createRowConsistOfNumberOfLine(2)));

        final List<Row> copiedRows = rows.rows();

        Assertions.assertThat(copiedRows).hasSize(1);
    }
}
