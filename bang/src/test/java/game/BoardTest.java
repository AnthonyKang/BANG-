package game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testGetDistance() {
        Board board1 = new Board(4, null);
        assertEquals(board1.getDistance(1, 2), 1);
        assertEquals(board1.getDistance(0, 3), 1);
        assertEquals(board1.getDistance(3, 1), 2);

        Board board2 = new Board(5, null);
        assertEquals(board2.getDistance(1, 2), 1);
        assertEquals(board2.getDistance(0, 3), 2);
        assertEquals(board2.getDistance(4, 1), 2);

        Board board3 = new Board(6, null);
        assertEquals(board3.getDistance(1, 2), 1);
        assertEquals(board3.getDistance(0, 3), 3);
        assertEquals(board3.getDistance(4, 1), 3);
        assertEquals(board3.getDistance(5, 3), 2);

        Board board4 = new Board(7, null);
        assertEquals(board4.getDistance(1, 2), 1);
        assertEquals(board4.getDistance(0, 3), 3);
        assertEquals(board4.getDistance(4, 1), 3);
        assertEquals(board4.getDistance(6, 0), 1);
        assertEquals(board4.getDistance(1, 6), 2);
    }

}
