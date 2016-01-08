package game.player;

import lombok.Data;

@Data
public class Character {
    private final String name;
    private final String ability;
    private final int maxLife;
}
