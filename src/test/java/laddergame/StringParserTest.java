package laddergame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StringParserTest {

    @Nested
    class CommaParser {
        private final StringParser parser = new StringParser(",");

        @Test
        @DisplayName("콤마를 기준으로 문자열을 파싱한다")
        void test() {
            String threeNames = "a,b,c";

            final List<String> names = parser.parse(threeNames);

            Assertions.assertThat(names).hasSize(3);
        }
    }
}
