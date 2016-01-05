package deck;

import java.util.ArrayList;
import java.util.Stack;

import cards.Card;
import lombok.Data;

@Data
public class Deck {
    private final Stack<Card> deck;
    private final ArrayList<Card> discard;
}
