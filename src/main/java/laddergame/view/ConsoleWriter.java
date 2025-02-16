package laddergame.view;

import java.io.PrintStream;

public class ConsoleWriter implements Writer {

    private final PrintStream printStream;

    public ConsoleWriter(final PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void println(final CharSequence str) {
        printStream.println(str);
    }
}
