package laddergame.ladder;

import java.util.List;

public class Rows {
    private final List<Row> rows;

    public Rows(final List<Row> rows) {
        validateNotEmptyRows(rows);
        validateConsistentRows(rows);
        this.rows = rows;
    }

    private void validateNotEmptyRows(List<Row> rows) {
        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateConsistentRows(List<Row> rows) {
        final var distinctCountOfNumberOfLinesOfEachRow = rows.stream()
            .map(Row::numberOfLines)
            .distinct()
            .count();

        if (distinctCountOfNumberOfLinesOfEachRow > 1) {
            throw new IllegalArgumentException();
        }
    }

    public List<Row> rows() {
        return List.copyOf(this.rows);
    }

    public int size() {
        return this.rows.size();
    }
}
