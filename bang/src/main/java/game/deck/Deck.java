package game.deck;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

import game.cards.Card;
import game.cards.CardColor;
import game.cards.CardSuit;
import lombok.Data;

@Data
public class Deck {
    /**
     * Used to maintain mapping between card type and its card list
     */
    private final static class CardLists {
        private final static Map<String, String> cardLists = new HashMap<>();

        private CardLists() {
            // TODO: add card lists
            // cardLists.put("card type", "filename");
        }

        public static Map<String, String> getCardLists() {
            return cardLists;
        }
    }

    private final Stack<Card> deck;
    private final Stack<Card> discard;

    public Deck() {
        deck = new Stack<>();
        discard = new Stack<>();
        initializeDeck();
    }

    /**
     * Build deck using card lists
     */
    private void initializeDeck() {
        IntStream.range(0, 20).forEach(x -> {
            deck.add(new Card(CardColor.BROWN, 1, CardSuit.SPADE, "Bang", "Bang", "Bang"));
            deck.add(new Card(CardColor.BROWN, 1, CardSuit.SPADE, "Miss", "Miss", "Miss"));
        });
        shuffle(false);
    }

    /**
     * Draw a card from deck
     * 
     * @return
     *         drawn card
     */
    public Card draw() {
        // shuffle cards if deck is empty
        if (this.isEmpty()) {
            shuffle(true);
        }
        return deck.pop();
    }

    /**
     * Add card back to top of deck
     * 
     * @param card
     *            card to be added to deck
     */
    public void addCard(Card card) {
        deck.push(card);
    }

    /**
     * Add card to discard pile
     * 
     * @param card
     *            discarded card
     */
    public void discard(Card card) {
        discard.add(card);
    }

    /**
     * Shuffles deck
     * 
     * @param fromDiscard
     *            if set, adds discards to deck
     */
    public void shuffle(boolean fromDiscard) {
        if (fromDiscard) {
            // add cards from discard to deck and clear discards
            deck.addAll(discard);
            discard.clear();
        }
        Collections.shuffle(deck);
    }

    /**
     * Check if deck is empty
     * 
     * @return true if deck is empty
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
