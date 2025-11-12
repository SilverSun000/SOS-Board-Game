package com.william.sos.model;

import java.util.List;

public class GeneralGame extends Game {

    private int blueScore;
    private int redScore;

    public GeneralGame(int size) {
        super(size);
        blueScore = 0;
        redScore = 0;
    }

    @Override
    public boolean makeMove(int row, int col, String letter) {
        boolean placed = board.placeMove(row, col, letter, currentTurn);
        if (!placed) return false;

        List<Board.SOSLine> lines = board.getCompletedSOS();
        int newSOS = 0;
        for (Board.SOSLine line : lines) {
            if ((currentTurn == Player.BLUE && line.playerColor.equals("Blue")) ||
                (currentTurn == Player.RED && line.playerColor.equals("Red"))) {
                newSOS++;
            }
        }

        if (currentTurn == Player.BLUE) blueScore += newSOS;
        else redScore += newSOS;

        if (newSOS == 0) switchTurn();

        return true;
    }

    @Override
    public boolean isGameOver() {
        return board.countFilledSquares() == board.getSize() * board.getSize();
    }

    @Override
    public Player getWinner() {
        if (!isGameOver()) return null;
        if (blueScore > redScore) return Player.BLUE;
        else if (redScore > blueScore) return Player.RED;
        else return null;
    }

    public int getBlueScore() { return blueScore; }
    public int getRedScore() { return redScore; }
}
