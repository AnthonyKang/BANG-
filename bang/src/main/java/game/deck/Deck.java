package game.deck;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import game.cards.Card;
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
    private final Stack<Card> specialCards; // High Noon and Fistful of Cards

    public Deck() {
        deck = new Stack<>();
        discard = new Stack<>();
        specialCards = new Stack<>();
        initializeDeck();
        initializeSpecialCards();
    }

    /**
     * Build deck using card lists
     */
    private void initializeDeck() {
        Map<String, String> cardLists = CardLists.getCardLists();
        cardLists.entrySet().stream().filter(x -> !x.getKey().equals("High Noon") && !x.getKey().equals("Fistful"))
                .forEach(x -> {
                    CardListParser parser = new CardListParser(x.getKey(), x.getValue());
                    List<Card> cards = parser.getCards();
                    deck.addAll(cards);
                });
        shuffle(false);
    }

    /**
     * Build deck for High Noon and Fistful of Cards card lists.
     */
    private void initializeSpecialCards() {
        Map<String, String> cardLists = CardLists.getCardLists();
        cardLists.entrySet().stream().filter(x -> x.getKey().equals("High Noon") || x.getKey().equals("Fistful"))
                .forEach(x -> {
                    CardListParser parser = new CardListParser(x.getKey(), x.getValue());
                    List<Card> cards = parser.getCards();
                    specialCards.addAll(cards);
                });
        Collections.shuffle(specialCards);
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
