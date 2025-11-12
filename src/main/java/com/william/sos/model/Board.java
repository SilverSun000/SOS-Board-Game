package com.william.sos.model;

public class Board {
    private String[][] grid;
    private int size;
    private boolean blueTurn; // true = Blue, false = Red

    // Initialize size and grid with empty values
    public Board(int size) {
        this.size = size;
        this.grid = new String[size][size];
        this.blueTurn = true; // Blue always starts

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = ""; // empty square
            }
        }
    }

    public int getSize() {
        return size;
    }

    public String getSquare(int row, int col) {
        return grid[row][col];
    }

    public String getCell(int row, int col) {
        return grid[row][col];
    }

    /**
     * Places a letter (S or O) on the board.
     * Returns true if successful; false if cell occupied or invalid.
     * Automatically switches the turn if successful.
     */
    public boolean placeMove(int row, int col, String letter) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (!grid[row][col].equals("")) return false;

        grid[row][col] = letter.toUpperCase();
        blueTurn = !blueTurn; // switch turn
        return true;
    }

    public boolean isBlueTurn() {
        return blueTurn;
    }

    public void reset() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = "";
            }
        }
        blueTurn = true;
    }
}
