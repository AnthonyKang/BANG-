package game.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import game.cards.Card;
import game.cards.CardColor;
import game.cards.Suit;

public class PlayerTest {

    @Test
    public void testGetMaxLife() {
        Player player = new Player();
        Character character = new Character("Test Name", "Test Ability", 4);
        player.setCharacter(character);
        player.setRole(Role.OUTLAW);

        assertEquals(player.getPlayerMaxLife(), 4);

        player.setRole(Role.SHERIFF);
        assertEquals(player.getPlayerMaxLife(), 5);
    }

    @Test
    public void testAddCard() {
        Player player = new Player();

        Card card1 = new Card(CardColor.BROWN, 1, Suit.SPADE, "Brown", "", "");
        Card card2 = new Card(CardColor.GREEN, 1, Suit.SPADE, "Green1", "", "");
        Card card3 = new Card(CardColor.GREEN, 1, Suit.SPADE, "Green1", "", "");
        Card card4 = new Card(CardColor.GREEN, 1, Suit.SPADE, "Green2", "", "");
        Card card5 = new Card(CardColor.BLUE, 1, Suit.SPADE, "Blue1", "", "");
        Card card6 = new Card(CardColor.BLUE, 1, Suit.SPADE, "Blue1", "", "");
        Card card7 = new Card(CardColor.BLUE, 1, Suit.SPADE, "Blue2", "", "");

        // add cards to hand
        assertTrue(player.addCard(card1, false));
        assertTrue(player.addCard(card2, false));
        assertTrue(player.addCard(card3, false));
        assertTrue(player.addCard(card4, false));
        assertTrue(player.addCard(card5, false));
        assertTrue(player.addCard(card6, false));
        assertTrue(player.addCard(card7, false));
        assertEquals(player.getHand().size(), 7);

        // add cards to field
        assertFalse(player.addCard(card1, true));
        assertTrue(player.addCard(card2, true));
        assertFalse(player.addCard(card3, true));
        assertTrue(player.addCard(card4, true));
        assertTrue(player.addCard(card5, true));
        assertFalse(player.addCard(card6, true));
        assertTrue(player.addCard(card7, true));
    }

    @Test
    public void testDiscardCard() {
        Player player = new Player();

        Card card1 = new Card(CardColor.BROWN, 1, Suit.SPADE, "Brown", "", "");
        Card card2 = new Card(CardColor.GREEN, 1, Suit.SPADE, "Green", "", "");
        Card card3 = new Card(CardColor.BLUE, 1, Suit.SPADE, "Blue", "", "");

        // add cards to hand
        player.addCard(card1, false);
        player.addCard(card2, false);
        player.addCard(card3, false);
        player.addCard(card2, true);
        player.addCard(card3, true);

        assertEquals(player.getHand().size(), 3);
        assertEquals(player.getField().size(), 2);

        Card dCard1 = new Card(CardColor.BROWN, 1, Suit.SPADE, "Brown", "", "");
        Card dCard2 = new Card(CardColor.BROWN, 1, Suit.SPADE, "Brown", "test", "");
        Card dCard3 = new Card(CardColor.BLUE, 1, Suit.SPADE, "Blue", "", "");

        assertEquals(dCard1, player.discardCard(dCard1, false));
        assertEquals(null, player.discardCard(dCard1, false));
        assertEquals(null, player.discardCard(dCard2, false));
        assertEquals(null, player.discardCard(dCard2, false));
        assertEquals(dCard3, player.discardCard(dCard3, true));
        assertEquals(null, player.discardCard(dCard1, true));

        assertEquals(player.getHand().size(), 2);
        assertEquals(player.getField().size(), 1);
    }

}
