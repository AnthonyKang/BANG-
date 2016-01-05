package player;

import java.util.ArrayList;
import java.util.HashSet;

import cards.Card;
import lombok.Data;

@Data
public class Player {
    private final Role role;
    private int life;
    private boolean jailed;
    private boolean dynamite;
    private final ArrayList<Card> hand;
    private final HashSet<Card> field;
}
