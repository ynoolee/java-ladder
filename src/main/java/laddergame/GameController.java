package laddergame;

import laddergame.view.ConsoleWriter;
import laddergame.view.InputView;
import laddergame.view.OutputView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameController {
    public static void main(String[] args) {
        final OutputView outputView = new OutputView(new ConsoleWriter(System.out));
        final InputView input = new InputView();

        outputView.requestToInputPlayerNames();
        final String playerNames = input.getPlayers();

        final StringParser playerNameParser = new StringParser(",");
        final List<Player> players = playerNameParser.parse(playerNames).stream().map(Player::new).collect(Collectors.toList());

        outputView.requestToInputHeightOfLadder();
        final Integer height = input.getHeight();

        outputView.printNames(players);
        final LadderGame ladderGame = new LadderGame(players.size(), height, () -> new Random().nextBoolean());
        outputView.showLadder(ladderGame.ladder());
    }
}
