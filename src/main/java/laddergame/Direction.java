package laddergame;

public enum Direction {
    LEFT,
    RIGHT,
    NONE;

    public boolean isBridge() {
        return !Direction.NONE.equals(this);
    }
}
