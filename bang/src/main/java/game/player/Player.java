package game.player;

import java.util.ArrayList;
import java.util.HashSet;

import game.cards.Card;
import lombok.Data;

@Data
public class Player {
    private final Role role;
    private final Character character;
    private int life;
    private boolean jailed;
    private boolean dynamite;
    private final ArrayList<Card> hand;
    private final HashSet<Card> field;

    /**
     * Adds card to player's hand or field
     * 
     * @param card
     *            card to add
     * @param field
     *            true for field, false for hand
     */
    public void addCard(Card card, boolean field) {
        // TODO
    }

    /**
     * Discards a card from player's hand or field
     * 
     * @param card
     *            card to discard
     * @param field
     *            true for field, false for hand
     * @return discarded card
     */
    public Card discardCard(Card card, boolean field) {
        // TODO
        return null;
    }

    /**
     * Start player's turn.
     */
    public void startTurn() {
        // TODO
    }

    /**
     * Ends player's turn
     */
    public void endTurn() {
        // TODO
    }

    /**
     * Prompt player to select card to play or discard
     * 
     * @return selected card
     */
    public Card selectCard() {
        // TODO
        return null;
    }

    /**
     * Returns how many discards player needs to make
     * 
     * @return number of discards
     */
    public int checkNumDiscards() {
        return Math.max(life - hand.size(), 0);
    }
}
