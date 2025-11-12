package com.william.sos.tests;

import com.william.sos.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleGameTest {

    private SimpleGame game;

    @BeforeEach
    void setup() {
        game = new SimpleGame(3);
    }

    @Test
    void testInitialTurnIsBlue() {
        assertEquals(Game.Player.BLUE, game.getCurrentTurn());
    }

    @Test
    void testSwitchTurnAfterValidMove() {
        game.makeMove(0, 0, "S");
        assertEquals(Game.Player.RED, game.getCurrentTurn());
    }

    @Test
    void testGameOverOnFirstSOS() {
        game.makeMove(0, 0, "S");
        game.makeMove(1, 0, "O");
        game.makeMove(2, 0, "S");

        assertTrue(game.isGameOver());
        assertEquals(Game.Player.BLUE, game.getWinner());
    }

    @Test
    void testDrawWhenBoardFullWithoutSOS() {
        game.makeMove(0, 0, "S");
        game.makeMove(0, 1, "O");
        game.makeMove(0, 2, "S");
        game.makeMove(1, 0, "O");
        game.makeMove(1, 1, "S");
        game.makeMove(1, 2, "O");
        game.makeMove(2, 0, "S");
        game.makeMove(2, 1, "O");
        game.makeMove(2, 2, "S");

        assertTrue(game.isGameOver());
        assertNull(game.getWinner());
    }
}
