package game.player;

import java.util.ArrayList;
import java.util.List;

import game.cards.Card;
import lombok.Data;

@Data
public class Player {
    private final Role role;
    private final Character character;
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
}
