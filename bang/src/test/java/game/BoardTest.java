package game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.player.Player;

public class BoardTest {

    @Test
    public void testGetDistance() {
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());
        players.add(new Player());

        // 4 players
        Board board1 = new Board(players);
        assertEquals(board1.getDistance(1, 2), 1);
        assertEquals(board1.getDistance(0, 3), 1);
        assertEquals(board1.getDistance(3, 1), 2);

        // 5 players
        players.add(new Player());
        Board board2 = new Board(players);
        assertEquals(board2.getDistance(1, 2), 1);
        assertEquals(board2.getDistance(0, 3), 2);
        assertEquals(board2.getDistance(4, 1), 2);

        // 6 players
        players.add(new Player());
        Board board3 = new Board(players);
        assertEquals(board3.getDistance(1, 2), 1);
        assertEquals(board3.getDistance(0, 3), 3);
        assertEquals(board3.getDistance(4, 1), 3);
        assertEquals(board3.getDistance(5, 3), 2);

        // 7 players
        players.add(new Player());
        Board board4 = new Board(players);
        assertEquals(board4.getDistance(1, 2), 1);
        assertEquals(board4.getDistance(0, 3), 3);
        assertEquals(board4.getDistance(4, 1), 3);
        assertEquals(board4.getDistance(6, 0), 1);
        assertEquals(board4.getDistance(1, 6), 2);
    }

}
