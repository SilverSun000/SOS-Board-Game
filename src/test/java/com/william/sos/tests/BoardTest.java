package com.william.sos.tests;

import com.william.sos.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = new Board(5);
    }

    @Test
    void testEmptyBoardAtStart() {
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                assertEquals("", board.getSquare(r, c), "All squares should start empty");
            }
        }
    }

    @Test
    void testValidMove() {
        assertTrue(board.placeMove(1, 1, "S"));
        assertEquals("S", board.getSquare(1, 1));
    }

    @Test
    void testInvalidMoveOutOfBounds() {
        assertFalse(board.placeMove(-1, 0, "S"));
        assertFalse(board.placeMove(5, 5, "O"));
    }

    @Test
    void testInvalidMoveOnFilledSquare() {
        board.placeMove(2, 2, "S");
        assertFalse(board.placeMove(2, 2, "O"));
    }

    @Test
    void testDetectHorizontalSOS() {
        board.placeMove(0, 0, "S");
        board.placeMove(0, 1, "O");
        board.placeMove(0, 2, "S");

        List<Board.SOSLine> lines = board.getCompletedSOS();
        assertEquals(1, lines.size());
        Board.SOSLine line = lines.get(0);
        assertEquals(0, line.startRow);
        assertEquals(0, line.startCol);
        assertEquals(0, line.endRow);
        assertEquals(2, line.endCol);
    }

    @Test
    void testDetectDiagonalSOS() {
        board.placeMove(0, 0, "S");
        board.placeMove(1, 1, "O");
        board.placeMove(2, 2, "S");

        List<Board.SOSLine> lines = board.getCompletedSOS();
        assertEquals(1, lines.size());
    }

    @Test
    void testResetBoard() {
        board.placeMove(1, 1, "S");
        board.reset();
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                assertEquals("", board.getSquare(r, c));
            }
        }
        assertTrue(board.getCompletedSOS().isEmpty());
    }
}
