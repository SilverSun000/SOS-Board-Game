package com.william.sos.model;

public class Game {

    public enum Player {
        BLUE, RED
    }

    private Board board;
    private Player currentTurn;

    public Game(int boardSize) {
        this.board = new Board(boardSize);
        this.currentTurn = Player.BLUE; // Blue always starts
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Attempt to make a move at the given row/col with letter "S" or "O".
     * If the move is valid, updates the board and switches turn.
     * Returns true if the move was successful.
     */
    public boolean makeMove(int row, int col, String letter) {
        if (!letter.equalsIgnoreCase("S") && !letter.equalsIgnoreCase("O")) {
            return false; // invalid letter
        }

        if (board.placeMove(row, col, letter.toUpperCase())) {
            switchTurn();
            return true;
        }

        return false; // square already occupied
    }

    private void switchTurn() {
        currentTurn = (currentTurn == Player.BLUE) ? Player.RED : Player.BLUE;
    }

    public void resetGame(int boardSize) {
        this.board = new Board(boardSize);
        this.currentTurn = Player.BLUE;
    }
}
