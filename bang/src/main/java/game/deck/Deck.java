package game.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import game.cards.Card;
import lombok.Data;

@Data
public class Deck {
    private final Stack<Card> deck;
    private final List<Card> discard;

    public Deck() {
        deck = new Stack<>();
        discard = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        CardListParser parser = new CardListParser();
        List<Card> cards = parser.getCards();
        cards.stream().forEach(x -> deck.add(x));
        shuffle(false);
    }

    public Card draw() {
        if (this.isEmpty()) {
            shuffle(true);
        }
        return deck.pop();
    }

    public void discard(Card card) {
        discard.add(card);
    }

    public void shuffle(boolean fromDiscard) {
        if (fromDiscard) {
            Collections.shuffle(discard);
            for (Card c : discard) {
                deck.add(c);
            }
        }
        else {
            Collections.shuffle(deck);
        }
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
