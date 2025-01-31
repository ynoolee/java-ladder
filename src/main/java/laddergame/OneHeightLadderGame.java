package laddergame;


public class OneHeightLadderGame {

    private int[] ladder;

    public OneHeightLadderGame(int[] ladder) {
        this.ladder = ladder;
    }

    public int run(final int lineNumber) {
        int result = lineNumber;
        if (ladder[lineNumber] == 1) {
            if (lineNumber > 0 && ladder[lineNumber - 1] == 1) {
                result--;
            }
            if (lineNumber < ladder.length - 1 && ladder[lineNumber + 1] == 1) {
                result++;
            }
        }

        return result;
    }
}
