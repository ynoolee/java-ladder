package laddergame.ladder;

public enum Direction {
    LEFT,
    RIGHT,
    NONE;

    public boolean isBridge() {
        return !Direction.NONE.equals(this);
    }
}
