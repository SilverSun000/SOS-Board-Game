package com.william.sos.model;

import java.util.List;

public class SimpleGame extends Game {

    private Player winner;
    private boolean gameOver;

    public SimpleGame(int size) {
        super(size);
        winner = null;
        gameOver = false;
    }

    @Override
    public boolean makeMove(int row, int col, String letter) {
        if (gameOver) return false;
        boolean placed = board.placeMove(row, col, letter, currentTurn);
        if (!placed) return false;

        List<Board.SOSLine> lines = board.getCompletedSOS();
        boolean scored = false;
        for (Board.SOSLine line : lines) {
            if ((currentTurn == Player.BLUE && line.playerColor.equals("Blue")) ||
                (currentTurn == Player.RED && line.playerColor.equals("Red"))) {
                scored = true;
                break;
            }
        }

        if (scored) {
            winner = currentTurn;
            gameOver = true;
        } else if (board.countFilledSquares() == board.getSize() * board.getSize()) {
            gameOver = true;
            winner = null;
        } else {
            switchTurn();
        }

        return true;
    }

    @Override
    public boolean isGameOver() { return gameOver; }

    @Override
    public Player getWinner() { return winner; }
}
