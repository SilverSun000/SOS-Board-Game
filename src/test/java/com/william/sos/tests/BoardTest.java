package com.william.sos.tests;

import com.william.sos.model.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testPlaceMoveSuccess() {
        Board board = new Board(3);

        // Place an "S" at (0,0)
        boolean moveResult = board.placeMove(0, 0, "S");
        assertTrue(moveResult, "Move should succeed on empty square");

        // Verify the board contains "S" at (0,0)
        assertEquals("S", board.getSquare(0, 0), "Square (0,0) should contain 'S'");
    }

    @Test
    void testPlaceMoveFailure() {
        Board board = new Board(3);

        // First move succeeds
        board.placeMove(0, 0, "S");

        // Second move on same square should fail
        boolean moveResult = board.placeMove(0, 0, "O");
        assertFalse(moveResult, "Move should fail on occupied square");

        // Verify the board value hasn't changed
        assertEquals("S", board.getSquare(0, 0), "Square (0,0) should still contain 'S'");
    }

    @Test
    void testGetSquareEmpty() {
        Board board = new Board(3);

        // Empty squares should return empty string
        assertEquals("", board.getSquare(1, 1), "Empty square should return empty string");
    }

    @Test
    void testBoardSize() {
        Board board = new Board(5);
        assertEquals(5, board.getSize(), "Board size should match constructor input");
    }
}
