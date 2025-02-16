package laddergame.ladder;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Height {

    private static final int MIN_HEIGHT = 1;
    private static final String INVALID_HEIGHT_MESSAGE = "사다리 높이는 1이상 이어야 합니다";

    private final int height;

    public Height(final int height) {
        validateInput(height);
        this.height = height;
    }

    private void validateInput(final int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_HEIGHT_MESSAGE);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Height)) return false;
        final Height height1 = (Height) o;
        return height == height1.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }
}
