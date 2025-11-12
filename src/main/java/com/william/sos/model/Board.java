package com.william.sos.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private String[][] grid;
    private int size;
    private final List<SOSLine> completedSOS;

    public Board(int size) {
        this.size = size;
        grid = new String[size][size];
        completedSOS = new ArrayList<>();
        reset();
    }

    public static class SOSLine {
        public final int startRow, startCol;
        public final int endRow, endCol;
        public final String playerColor;

        public SOSLine(int startRow, int startCol, int endRow, int endCol, String playerColor) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.playerColor = playerColor;
        }
    }

    public int getSize() { return size; }
    public String getSquare(int row, int col) { return grid[row][col]; }
    public List<SOSLine> getCompletedSOS() { return new ArrayList<>(completedSOS); }

    public boolean placeMove(int row, int col, String letter) {
        return placeMove(row, col, letter, Game.Player.BLUE);
    }

    public boolean placeMove(int row, int col, String letter, Game.Player player) {
        if (row < 0 || row >= size || col < 0 || col >= size) return false;
        if (!grid[row][col].isEmpty()) return false;

        letter = letter.toUpperCase();
        if (!letter.equals("S") && !letter.equals("O")) return false;

        grid[row][col] = letter;
        checkForSOS(row, col, player);
        return true;
    }

    public void reset() {
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                grid[r][c] = "";
        completedSOS.clear();
    }

    private void checkForSOS(int row, int col, Game.Player player) {
        String letter = grid[row][col];
        String color = (player == Game.Player.BLUE) ? "Blue" : "Red";

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {1, 1}, {-1, 1}, {1, -1}
        };

        for (int[] dir : directions) {
            int dr = dir[0], dc = dir[1];

            if (letter.equals("O")) {
                int r1 = row - dr, c1 = col - dc;
                int r2 = row + dr, c2 = col + dc;
                if (inBounds(r1, c1) && inBounds(r2, c2)) {
                    if (grid[r1][c1].equals("S") && grid[r2][c2].equals("S")) {
                        completedSOS.add(new SOSLine(r1, c1, r2, c2, color));
                    }
                }

            } else if (letter.equals("S")) {
                int r1 = row + dr, c1 = col + dc;
                int r2 = row + 2 * dr, c2 = col + 2 * dc;
                if (inBounds(r1, c1) && inBounds(r2, c2)) {
                    if (grid[r1][c1].equals("O") && grid[r2][c2].equals("S")) {
                        completedSOS.add(new SOSLine(row, col, r2, c2, color));
                    }
                }

                int rb1 = row - dr, cb1 = col - dc;
                int rb2 = row - 2 * dr, cb2 = col - 2 * dc;
                if (inBounds(rb1, cb1) && inBounds(rb2, cb2)) {
                    if (grid[rb1][cb1].equals("O") && grid[rb2][cb2].equals("S")) {
                        completedSOS.add(new SOSLine(rb2, cb2, row, col, color));
                    }
                }
            }
        }
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    public int countFilledSquares() {
        int count = 0;
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (!grid[r][c].isEmpty()) count++;
        return count;
    }
}
