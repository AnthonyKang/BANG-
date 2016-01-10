package game.cards;

import lombok.Data;

@Data
public abstract class Card {
    private final CardColor color;
    private final int number;
    private final Suit suit;
    private final String name;
    private final String description;
    private final String effect;
}
