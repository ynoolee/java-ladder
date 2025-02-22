package laddergame;

import laddergame.ladder.Height;
import laddergame.ladder.Ladder;
import laddergame.view.ConsoleWriter;
import laddergame.view.InputView;
import laddergame.view.OutputView;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {

    private final OutputView outputView;
    private final InputView inputView;

    public GameController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public static void main(String[] args) {
        final var commaParser = new StringParser(",");
        final var gameLauncher = new GameController(createConsoleOutputView(), createConsoleInputView());

        final var players = gameLauncher.getPlayers(commaParser);
        final var rewards = gameLauncher.getRewards(commaParser);

        final var randomLadderGame = new LadderGame(
            players.size(),
            gameLauncher.getHeight(),
            () -> new Random().nextBoolean()
        );

        gameLauncher.printLadder(players, randomLadderGame.ladder(), rewards);

        gameLauncher.outputView.printResult(
            randomLadderGame.runWith(players, rewards),
            gameLauncher.askWhoseGameResult()
        );
    }

    private static OutputView createConsoleOutputView() {
        return new OutputView(new ConsoleWriter(System.out));
    }

    private static InputView createConsoleInputView() {
        return new InputView(new Scanner(System.in));
    }

    private LadderPlayers getPlayers(final StringParser commaParser) {
        final var playerNameStrings = getPlayerNameStrings();

        return new LadderPlayers(commaParser.parse(playerNameStrings).stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private String getPlayerNameStrings() {
        outputView.requestToInputPlayerNames();

        return inputView.getOneLine();
    }

    private LadderRewards getRewards(final StringParser commaParser) {
        final var rewardStrings = getRewardStrings();

        return new LadderRewards(commaParser.parse(rewardStrings).stream()
            .map(Reward::of)
            .collect(Collectors.toList()));
    }

    private String getRewardStrings() {
        outputView.requestToInputRewards();

        return inputView.getOneLine();
    }

    private Height getHeight() {
        outputView.requestToInputHeightOfLadder();

        return new Height(inputView.getHeight());
    }

    private void printLadder(final LadderPlayers ladderPlayers, final Ladder ladder, final LadderRewards ladderRewards) {
        outputView.printPaddingElements(convertToPlayerNames(ladderPlayers));
        outputView.showLadder(ladder);
        outputView.printPaddingElements(convertToRewardTitles(ladderRewards));
    }

    private List<String> convertToRewardTitles(final LadderRewards ladderRewards) {
        return ladderRewards.allRewards().stream()
            .map(Reward::getValue)
            .collect(Collectors.toList());
    }

    private List<String> convertToPlayerNames(final LadderPlayers ladderPlayers) {
        return ladderPlayers.allPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    private String askWhoseGameResult() {
        outputView.printRequestWhom();

        return inputView.getOneLine();
    }
}
