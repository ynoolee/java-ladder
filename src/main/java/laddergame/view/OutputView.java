package laddergame.view;

import laddergame.ladder.Ladder;
import laddergame.ladder.Row;

import java.util.List;

public class OutputView {

    private static final int SPACE_LENGTH = 5;
    private static final String WHITESPACE = " ";
    private static final String BRIDGE_UNIT = "-";
    private static final String HEIGHT_UNIT = "|";
    private static final String REQUEST_MESSAGE_TO_INPUT_PLAYERS = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String REQUEST_MESSAGE_TO_INPUT_HEIGHT = "최대 사다리 높이는 몇 개인가요?";

    private final Writer writer;

    public OutputView(final Writer writer) {
        this.writer = writer;
    }

    public void requestToInputPlayerNames() {
        writer.println(REQUEST_MESSAGE_TO_INPUT_PLAYERS);
    }

    public void requestToInputHeightOfLadder() {
        writer.println(REQUEST_MESSAGE_TO_INPUT_HEIGHT);
    }

    public void showLadder(final Ladder ladder) {
        final var height = ladder.height();

        for (int currentHeight = 0; currentHeight < height; currentHeight++) {
            printRow(ladder.rowOfHeight(currentHeight));
        }
    }

    private void printRow(final Row row) {
        var rowBuilder = new StringBuilder();

        var lastLineIndex = row.numberOfLines() - 1;

        for (var currentLine = 0; currentLine < lastLineIndex; currentLine++) {
            appendLine(rowBuilder);
            appendLineConnection(row, currentLine, rowBuilder);
        }
        appendLastLine(rowBuilder);

        writer.println(rowBuilder.toString());
    }

    private void appendLineConnection(final Row row, final int currentLine, final StringBuilder sb) {
        if (row.isStartOfBridge(currentLine)) {
            sb.append(makeBridge());
        } else {
            sb.append(makeSpaceBetweenLines());
        }
    }

    private void appendLine(final StringBuilder sb) {
        sb.append(HEIGHT_UNIT);
    }

    private void appendLastLine(final StringBuilder sb) {
        sb.append(HEIGHT_UNIT);
    }

    private String makeBridge() {
        return BRIDGE_UNIT.repeat(SPACE_LENGTH);
    }

    private String makeSpaceBetweenLines() {
        return WHITESPACE.repeat(SPACE_LENGTH);
    }

    public void printNames(final List<String> names) {
        var namesBuilder = new StringBuilder();

        names.stream()
            .map(this::paddingName)
            .forEach(namesBuilder::append);

        writer.println(namesBuilder.toString());
    }

    private String paddingName(String name) {
        var diff = SPACE_LENGTH - name.length() + 1;

        return name + WHITESPACE.repeat(Math.max(0, diff));
    }
}
