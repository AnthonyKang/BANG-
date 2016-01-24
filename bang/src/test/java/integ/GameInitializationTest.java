package integ;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.GameController;
import game.GamePhase;
import game.deck.Deck;
import game.player.Character;
import game.player.Player;
import game.player.Role;

public class GameInitializationTest {
    private List<Player> players;
    private int i = 0;

    @Before
    public void before() {
        players = new ArrayList<>();
        players.add(new Player(Role.SHERIFF, getTestCharacter()));
        players.add(new Player(Role.OUTLAW, getTestCharacter()));
        players.add(new Player(Role.RENEGADE, getTestCharacter()));
    }

    @Test
    public void testThreePlayer() {

        GameController controller = new GameController(players);

        // check number of cards dealt to each player
        for (Player p : players) {
            if (p.getRole() == Role.SHERIFF) {
                assertEquals(4, p.getHand().size());
            }
            else {
                assertEquals(3, p.getHand().size());
            }
        }

        // check deck size
        Deck deck = controller.getDeck();
        assertEquals(40 - 3 - 3 - 4, deck.getDeck().size());
        assertEquals(0, deck.getDiscard().size());

        assertEquals(GamePhase.BEGINNING, controller.getPhase());
        assertEquals(players.get(0), controller.getCurrentPlayer());
    }

    @Test
    public void testGamePhase() {
        GameController controller = new GameController(players);

        assertEquals(GamePhase.BEGINNING, controller.getPhase());
        assertEquals(players.get(0), controller.getCurrentPlayer());

        controller.nextPhase();
        assertEquals(GamePhase.DRAW, controller.getPhase());

        controller.nextPhase();
        assertEquals(GamePhase.PLAY, controller.getPhase());

        controller.nextPhase();
        assertEquals(GamePhase.END, controller.getPhase());

        controller.nextPhase();
        assertEquals(GamePhase.BEGINNING, controller.getPhase());
        assertEquals(players.get(1), controller.getCurrentPlayer());
    }

    private Character getTestCharacter() {
        return new Character("Character #" + (i++), "Test Ability", 3);
    }
}
