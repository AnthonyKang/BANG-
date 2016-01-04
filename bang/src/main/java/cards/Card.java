package cards;

import lombok.Data;

@Data
public abstract class Card {
    private final int number;
    private final Suit suit;
    private final String name;
    private final String effect;
}
