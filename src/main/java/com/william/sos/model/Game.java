package com.william.sos.model;

public abstract class Game {

    public enum Player { BLUE, RED }

    protected Board board;
    protected Player currentTurn;

    public Game(int size) {
        this.board = new Board(size);
        this.currentTurn = Player.BLUE;
    }

    public Game(Board board) {
        this.board = board;
        this.currentTurn = Player.BLUE;
    }

    public abstract boolean makeMove(int row, int col, String letter);

    public abstract boolean isGameOver();

    public abstract Player getWinner();

    protected void switchTurn() {
        currentTurn = (currentTurn == Player.BLUE) ? Player.RED : Player.BLUE;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public Board getBoard() {
        return board;
    }

    public void reset() {
        board.reset();
        currentTurn = Player.BLUE;
    }
}
