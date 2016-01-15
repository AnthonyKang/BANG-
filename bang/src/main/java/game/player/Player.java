package game.player;

import java.util.ArrayList;
import java.util.List;

import game.cards.Card;
import game.cards.CardColor;
import lombok.Data;

@Data
public class Player {
    private Role role;
    private Character character;
    private int life;
    private Card weapon;
    private Card jail;
    private Card dynamite;
    private final List<Card> hand;
    private final List<Card> field;

    /**
     * Constructor for player
     * 
     * @param role
     *            player's role
     * @param character
     *            player's character
     */
    public Player(Role role, Character character) {
        this.role = role;
        this.character = character;
        // initial life = character max life (+1 if player is sheriff)
        this.life = getPlayerMaxLife();
        this.weapon = null;
        this.jail = null;
        this.dynamite = null;
        this.hand = new ArrayList<>();
        this.field = new ArrayList<>();
    }

    /**
     * Constructor for no parameters (used mainly for testing purposes)
     */
    public Player() {
        this.role = null;
        this.character = null;
        // initial life = character max life (+1 if player is sheriff)
        this.life = 0;
        this.weapon = null;
        this.jail = null;
        this.dynamite = null;
        this.hand = new ArrayList<>();
        this.field = new ArrayList<>();
    }

    /**
     * Adds card to player's hand or field
     * 
     * @param card
     *            card to add
     * @param toField
     *            true for field, false for hand
     * @return
     *         true if success
     */
    public boolean addCard(Card card, boolean toField) {
        if (toField) {
            if (card.getColor() == CardColor.BROWN) {
                return false;
            }
            if (isDuplicateField(card)) {
                return false;
            }
            field.add(card);
        }
        else {
            hand.add(card);
        }
        return true;
    }

    /**
     * Discards a card from player's hand or field
     * 
     * @param card
     *            card to discard
     * @param fromField
     *            true for field, false for hand
     * @return discarded card; null if card does not exist
     */
    public Card discardCard(Card card, boolean fromField) {
        boolean success = false;
        if (fromField) {
            success = field.remove(card);
        }
        else {
            success = hand.remove(card);
        }
        return success ? card : null;
    }

    /**
     * Check if player is dead
     * 
     * @return true if dead
     */
    public boolean isDead() {
        return life == 0;
    }

    /**
     * Returns player's max life
     * 
     * @return player's max life
     */
    public int getPlayerMaxLife() {
        return character.getMaxLife() + (role == Role.SHERIFF ? 1 : 0);
    }

    /**
     * Heals player by specified amount
     * 
     * @param amount
     *            heal amount
     */
    public void heal(int amount) {
        this.life = Math.max(life + amount, getPlayerMaxLife());
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
     * Removes jail card from player
     * 
     * @return
     *         jail card
     */
    public Card removeJail() {
        // TODO: check if jail exists?
        Card jail = getJail();
        this.jail = null;
        return jail;
    }

    /**
     * Removes dynamite card from player
     * 
     * @return
     *         dynamite card
     */
    public Card removeDynamite() {
        // TODO: check if dynamite exists?
        Card dynamite = getDynamite();
        this.dynamite = null;
        return dynamite;
    }

    /**
     * Returns how many discards player needs to make
     * 
     * @return number of discards
     */
    public int checkNumDiscards() {
        return Math.max(life - hand.size(), 0);
    }

    /**
     * Damage player for specified amount
     * 
     * @param amount
     *            amount of damage
     */
    public void damage(int amount) {
        this.life = Math.max(0, life - amount);
    }

    /**
     * Check for duplicate field card
     */
    private boolean isDuplicateField(Card card) {
        String cardName = card.getName();
        return field.stream().anyMatch(x -> x.getName().equals(cardName));
    }
}
