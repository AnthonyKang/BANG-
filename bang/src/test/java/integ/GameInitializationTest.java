package integ;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.GameController;
import game.GamePhase;
import game.deck.Deck;
import game.player.Character;
import game.player.Player;
import game.player.Role;

public class GameInitializationTest {
    int i = 0;

    @Test
    public void testThreePlayer() {
        List<Player> players = new ArrayList<>();
        players.add(new Player(Role.SHERIFF, getTestCharacter()));
        players.add(new Player(Role.OUTLAW, getTestCharacter()));
        players.add(new Player(Role.RENEGADE, getTestCharacter()));

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

    private Character getTestCharacter() {
        return new Character("Character #" + (i++), "Test Ability", 3);
    }
}
