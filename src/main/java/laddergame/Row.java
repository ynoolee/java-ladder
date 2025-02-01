package laddergame;

import java.util.List;

public class Row {

    private final List<Direction> pointers;

    public Row(List<Direction> directions) {
        this.pointers = directions;
    }

    public Direction whichDirectionOf(final int lineNumber) {
        if (pointers.isEmpty() || pointers.size() <= lineNumber) {
            throw new IllegalArgumentException("존재하지 않는 라인입니다");
        }
        return pointers.get(lineNumber);
    }
}
