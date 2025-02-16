package laddergame;

import laddergame.ladder.Height;
import laddergame.view.ConsoleWriter;
import laddergame.view.InputView;
import laddergame.view.OutputView;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    public static void main(String[] args) {
        final var outputView = createConsoleOutputView();
        final var inputView = createConsoleInputView();

        outputView.requestToInputPlayerNames();
        final var playerNames = inputView.getNames();

        final var nameParser = new StringParser(",");
        final var names = nameParser.parse(playerNames).stream()
            .map(Name::new)
            .collect(Collectors.toList());

        outputView.requestToInputHeightOfLadder();
        final var ladderHeight = inputView.getHeight();

        outputView.printNames(names.stream().map(Name::getName).collect(Collectors.toList()));
        final var ladderGame = new LadderGame(names.size(), new Height(ladderHeight), () -> new Random().nextBoolean());
        outputView.showLadder(ladderGame.ladder());
    }

    private static OutputView createConsoleOutputView() {
        return new OutputView(new ConsoleWriter(System.out));
    }

    private static InputView createConsoleInputView() {
        return new InputView(new Scanner(System.in));
    }
}
