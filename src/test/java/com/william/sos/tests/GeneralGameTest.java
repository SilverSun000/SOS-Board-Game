package com.william.sos.tests;

import com.william.sos.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeneralGameTest {

    private GeneralGame game;

    @BeforeEach
    void setup() {
        game = new GeneralGame(3);
    }

    @Test
    void testInitialScoresAreZero() {
        assertEquals(0, game.getBlueScore());
        assertEquals(0, game.getRedScore());
    }

    @Test
    void testBlueScoresAndKeepsTurn() {
        game.makeMove(0, 0, "S");
        game.makeMove(0, 1, "O");
        game.makeMove(0, 2, "S");

        assertEquals(1, game.getBlueScore());
        assertEquals(Game.Player.BLUE, game.getCurrentTurn());
    }

    @Test
    void testTurnSwitchesIfNoSOS() {
        game.makeMove(0, 0, "S");
        assertEquals(Game.Player.RED, game.getCurrentTurn());
    }

    @Test
    void testRedScoresAndKeepsTurn() {
        // Blue move
        game.makeMove(0, 0, "S");
        // Red sequence that forms SOS
        game.makeMove(1, 0, "S");
        game.makeMove(1, 1, "O");
        game.makeMove(1, 2, "S");

        assertEquals(1, game.getRedScore());
        assertEquals(Game.Player.RED, game.getCurrentTurn());
    }

    @Test
    void testWinnerDetermination() {
        // Blue wins
        game.makeMove(0, 0, "S");
        game.makeMove(0, 1, "O");
        game.makeMove(0, 2, "S");

        // Fill rest of board so game ends
        for (int r = 1; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (game.getBoard().getSquare(r, c).isEmpty())
                    game.makeMove(r, c, "S");

        assertTrue(game.isGameOver());
        assertEquals(Game.Player.BLUE, game.getWinner());
    }
}
