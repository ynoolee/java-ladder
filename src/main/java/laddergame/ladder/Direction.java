package laddergame.ladder;

public interface Direction {

    Direction LEFT = new Left();
    Direction RIGHT = new Right();
    Direction NONE = new None();

    boolean isValidPoint(Direction previous, boolean isLastPosition);

    boolean isLeftComponentOfBridge();

    boolean isRightComponentOfBridge();

    boolean isBridge();

    class Left implements Direction {

        @Override
        public boolean isValidPoint(final Direction previous, final boolean isLastPosition) {
            return previous.isRightComponentOfBridge();
        }

        @Override
        public boolean isLeftComponentOfBridge() {
            return true;
        }

        @Override
        public boolean isRightComponentOfBridge() {
            return false;
        }

        @Override
        public boolean isBridge() {
            return true;
        }
    }

    class Right implements Direction {

        @Override
        public boolean isValidPoint(final Direction previous, final boolean isLastPosition) {
            return !isLastPosition && !previous.isLeftComponentOfBridge();
        }

        @Override
        public boolean isLeftComponentOfBridge() {
            return false;
        }

        @Override
        public boolean isRightComponentOfBridge() {
            return true;
        }

        @Override
        public boolean isBridge() {
            return true;
        }
    }

    class None implements Direction {

        @Override
        public boolean isValidPoint(final Direction previous, final boolean isLastPosition) {
            return !previous.isRightComponentOfBridge();
        }

        @Override
        public boolean isLeftComponentOfBridge() {
            return false;
        }

        @Override
        public boolean isRightComponentOfBridge() {
            return false;
        }

        @Override
        public boolean isBridge() {
            return false;
        }
    }
}
