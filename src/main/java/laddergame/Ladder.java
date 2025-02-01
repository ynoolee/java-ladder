package laddergame;

import lombok.Builder;

public class Ladder {

    private static final int MIN_LINE_COUNT = 2;
    private static final int MIN_HEIGHT = 1;
    private static final String INVALID_LINE_COUNT_MESSAGE = "사다리 라인은 2개 이상이어야 합니다";
    private static final String INVALID_HEIGHT_MESSAGE = "사다리 높이는 1이상 이어야 합니다";

    private final int numberOfLines;
    private final int height;

    @Builder
    Ladder(final int numberOfLines, final int height) {
        if (numberOfLines < MIN_LINE_COUNT) {
            throw new IllegalArgumentException(INVALID_LINE_COUNT_MESSAGE);
        }
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_HEIGHT_MESSAGE);
        }

        this.numberOfLines = numberOfLines;
        this.height = height;
    }
}
