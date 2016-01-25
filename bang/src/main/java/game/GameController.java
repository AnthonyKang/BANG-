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
    private int bangCount = 0;

    public GameController(List<Player> players) {
        this.board = new Board(players);
        this.deck = new Deck();

        initialize();
    }

    /**
     * Deals out game to players
     */
    private void initialize() {
        dealInitialCards();
        this.phase = GamePhase.BEGINNING;
        currentPlayer = getNextPlayer(currentPlayer);
    }

    /**
     * Deals out initial cards to every players. Players receive same number of
     * cards as their life
     */
    public void dealInitialCards() {
        board.getPlayers().stream().forEach(x -> {
            for (int i = 0; i < x.getLife(); i++) {
                Card card = deck.draw();
                x.addCard(card, false);
            }
        });
    }

    /**
     * Change game state to next phase
     */
    public void nextPhase() {
        switch (phase) {
        case BEGINNING:
            phase = GamePhase.DRAW;
            break;
        case DRAW:
            phase = GamePhase.PLAY;
            break;
        case PLAY:
            phase = GamePhase.END;
            break;
        case END:
            phase = GamePhase.BEGINNING;
            currentPlayer = getNextPlayer(currentPlayer);
            break;
        }
    }

    /**
     * Gets next player
     * 
     * @param current
     *            current player
     * @return
     *         next player
     */
    public Player getNextPlayer(Player current) {
        return board.getNextPlayer(current);
    }
}