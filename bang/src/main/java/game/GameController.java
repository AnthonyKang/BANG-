package game;

import java.util.List;

import game.cards.Card;
import game.deck.Deck;
import game.player.Player;
import lombok.Data;

@Data
public class GameController {
    private final Board board;
    private final Deck deck;

    private GamePhase phase = null;
    private Player currentPlayer = null;

    public GameController(List<Player> players) {
        this.board = new Board(players);
        this.deck = new Deck();

        initialize();
    }

    private void initialize() {
        // deal cards to players
        board.getPlayers().stream().forEach(x -> {
            for (int i = 0; i < x.getLife(); i++) {
                Card card = deck.draw();
                x.addCard(card, false);
            }
        });

        this.phase = GamePhase.BEGINNING;
        currentPlayer = getNextPlayer(currentPlayer);
    }

    public Player getNextPlayer(Player current) {
        return board.getNextPlayer(current);
    }
}