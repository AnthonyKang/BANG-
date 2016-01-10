package game.deck;

import java.util.ArrayList;
import java.util.List;

import game.cards.Card;

public class CardListParser {
    private final String cardType;
    private final String cardListFile;

    public CardListParser(String cardType, String cardListFile) {
        this.cardType = cardType;
        this.cardListFile = cardListFile;
    }

    public List<Card> getCards() {
        List<Card> cardList = new ArrayList<>();

        switch (cardType.toLowerCase()) {
        case "brown":
            // TODO
            break;
        case "blue":
            // TODO
            break;
        case "green":
            // TODO
            break;
        case "gun":
            // TODO
            break;
        case "high noon":
            // TODO
            break;
        case "fistful":
            // TODO
            break;
        case "special":
            // Jail and dynamite
            // TODO
            break;
        default:
            throw new RuntimeException("Invalid card type");
        }
        return null;
    }
}
