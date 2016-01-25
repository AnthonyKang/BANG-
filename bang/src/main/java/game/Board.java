package game;

import java.util.List;

import game.cards.Card;
import game.player.Player;
import game.player.Role;
import lombok.Data;

@Data
public class Board {
    private int numPlayers;
    private List<Player> players;
    private List<Player> dead;

    public Board(List<Player> players) {
        this.players = players;
        this.numPlayers = players.size();
    }

    /**
     * Removes player from board and add to dead list.
     * 
     * @param player
     */
    public void removePlayer(Player player) {
        players.remove(player);
        dead.add(player);
        numPlayers--;
    }

    /**
     * Returns list of players within attacking distance
     * 
     * @param player
     *            specified player
     * @return
     *         list of players
     */
    public List<Player> getWithinDistancePlayers(Player player) {
        // TODO:
        return null;
    }

    /**
     * Computes distance between two players
     * 
     * @param p1
     *            first player
     * @param p2
     *            second player
     * @return
     *         distance between players
     */
    public int getDistance(Player p1, Player p2) {
        return getDistance(players.indexOf(p1), players.indexOf(p2));
    }

    /**
     * Computes distance between two positions
     * 
     * @param p1
     *            first position
     * @param p2
     *            second position
     * @return
     *         distance between positions
     */
    public int getDistance(int p1, int p2) {
        int d1 = Math.abs(p1 - p2);
        int d2 = numPlayers - d1;
        return Math.min(d1, d2);
    }

    /**
     * Gets next player whose turn is next
     * 
     * @param player
     *            if null, returns sheriff player. Else, return next player
     * @return
     *         next player
     */
    public Player getNextPlayer(Player player) {
        if (player == null) {
            return players.stream().filter(x -> x.getRole() == Role.SHERIFF).findAny().get();
        }
        else {
            int index = (players.indexOf(player) + 1) % numPlayers;
            return players.get(index);
        }
    }

    /**
     * Checks if player p2 is within attack range of player p1;
     * 
     * @param p1
     * @param p2
     * @return
     */
    public boolean isWithinDistance(Player p1, Player p2) {
        Card weapon = p1.getWeapon();
        // TODO: get weapon attack range
        int attackRange = 1;
        // TODO: check blue card modifiers

        return getDistance(p1, p2) <= attackRange;
    }

}
