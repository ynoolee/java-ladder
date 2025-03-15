package laddergame;


public class Position {
    private final int rowNumber;
    private final int lineNumber;

    public Position(final int rowNumber, final int lineNumber) {
        this.rowNumber = rowNumber;
        this.lineNumber = lineNumber;
    }

    public Position moveDown() {
        return new Position(rowNumber + 1, lineNumber);
    }

    public Position moveRightAndDown() {
        return new Position(rowNumber + 1, lineNumber + 1);
    }

    public Position moveLeftAndDown() {
        return new Position(rowNumber + 1, lineNumber - 1);
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        final Position position = (Position) o;

        if (rowNumber != position.rowNumber) return false;
        return lineNumber == position.lineNumber;
    }

    @Override
    public int hashCode() {
        int result = rowNumber;
        result = 31 * result + lineNumber;
        return result;
    }
}
