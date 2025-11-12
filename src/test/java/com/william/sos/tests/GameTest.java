package com.william.sos.tests;

import com.william.sos.model.Game;
import com.william.sos.model.Game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game(3); // 3x3 board for testing
    }

    @Test
    void testInitialTurn() {
        assertEquals(Player.BLUE, game.getCurrentTurn(), "Blue should start first");
    }

    @Test
    void testValidMovePlacesLetter() {
        boolean moved = game.makeMove(0, 0, "S");
        assertTrue(moved, "Move should be successful");
        assertEquals("S", game.getBoard().getSquare(0, 0), "Board should contain 'S'");
    }

    @Test
    void testTurnAlternatesAfterMove() {
        game.makeMove(0, 0, "S");
        assertEquals(Player.RED, game.getCurrentTurn(), "Turn should switch to Red");
        game.makeMove(0, 1, "O");
        assertEquals(Player.BLUE, game.getCurrentTurn(), "Turn should switch back to Blue");
    }

    @Test
    void testInvalidMoveDoesNotChangeTurn() {
        game.makeMove(0, 0, "S");
        boolean moved = game.makeMove(0, 0, "O"); // square already occupied
        assertFalse(moved, "Move should fail on occupied square");
        assertEquals(Player.RED, game.getCurrentTurn(), "Turn should not change if move invalid");
    }

    @Test
    void testInvalidLetterRejected() {
        boolean moved = game.makeMove(1, 1, "X");
        assertFalse(moved, "Only S or O should be allowed");
        assertEquals(Player.BLUE, game.getCurrentTurn(), "Turn should remain the same on invalid letter");
    }

    @Test
    void testResetGame() {
        game.makeMove(0, 0, "S");
        game.resetGame(3);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals("", game.getBoard().getSquare(row, col), "All squares should be empty after reset");
            }
        }
        assertEquals(Player.BLUE, game.getCurrentTurn(), "Blue should start after reset");
    }
}
