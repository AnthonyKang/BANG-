package game;

import java.util.List;

import game.player.Player;
import game.player.Role;
import lombok.Data;

@Data
public class Board {
    private int numPlayers;
    private List<Player> players;
    private List<Player> dead;

    public Board(int numPlayers, List<Player> players) {
        this.numPlayers = numPlayers;
        this.players = players;
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
        int index = 0;
        if (player == null) {
            for (int i = 0; i < numPlayers; i++) {
                if (players.get(i).getRole() == Role.SHERIFF) {
                    index = i;
                    break;
                }
            }
        }
        else {
            index = (players.indexOf(player) + 1) % numPlayers;
        }

        return players.get(index);
    }
}
