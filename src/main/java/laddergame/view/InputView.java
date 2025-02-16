package laddergame.view;

import java.util.Scanner;

public class InputView {

    private final Scanner reader;

    public InputView(final Scanner scanner) {
        this.reader = scanner;
    }

    public String getNames() {
        return reader.next();
    }

    public Integer getHeight() {
        return Integer.parseInt(reader.next());
    }
}
