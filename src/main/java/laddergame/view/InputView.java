package laddergame.view;

import java.util.Scanner;

public class InputView {
    public String getPlayers() {
        final Scanner reader = new Scanner(System.in);

        return reader.next();
    }

    public Integer getHeight() {
        final Scanner reader = new Scanner(System.in);

        return Integer.parseInt(reader.next());
    }
}
