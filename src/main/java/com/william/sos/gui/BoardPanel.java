package com.william.sos.gui;

import com.william.sos.model.Board;
import com.william.sos.model.Game;
import com.william.sos.model.SimpleGame;
import com.william.sos.model.GeneralGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardPanel extends JPanel {

    private Game game;
    private int boardSize;
    private static final int FIXED_BOARD_SIZE = 600; // fixed drawing area

    private String currentLetter = "S";

    public BoardPanel(int boardSize, String gameMode) {
        this.boardSize = boardSize;

        startNewGame(gameMode);

        setPreferredSize(new Dimension(FIXED_BOARD_SIZE, FIXED_BOARD_SIZE));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cellSize = FIXED_BOARD_SIZE / boardSize;
                int row = e.getY() / cellSize;
                int col = e.getX() / cellSize;
                handleMove(row, col);
            }
        });
    }

    public void startNewGame(String gameMode) {
        if (gameMode.equalsIgnoreCase("Simple")) {
            this.game = new SimpleGame(boardSize);
        } else {
            this.game = new GeneralGame(boardSize);
        }
        this.currentLetter = "S";
        repaint();
    }

    public void setCurrentLetter(String letter) {
        if (letter != null && (letter.equalsIgnoreCase("S") || letter.equalsIgnoreCase("O"))) {
            this.currentLetter = letter.toUpperCase();
        }
    }

    private void handleMove(int row, int col) {
        boolean success = game.makeMove(row, col, currentLetter);

        if (success) {
            repaint();

            if (game.isGameOver()) {
                Game.Player winner = game.getWinner();
                String message = (winner != null)
                        ? "Game over! Winner: " + winner
                        : "Game over! It's a draw.";
                JOptionPane.showMessageDialog(this, message);
            }
            SwingUtilities.getWindowAncestor(this).repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move! Square already occupied or invalid.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = FIXED_BOARD_SIZE / boardSize;
        Board board = game.getBoard();

        g.setColor(Color.BLACK);
        for (int i = 0; i <= boardSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, FIXED_BOARD_SIZE);
            g.drawLine(0, i * cellSize, FIXED_BOARD_SIZE, i * cellSize);
        }

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                String letter = board.getSquare(row, col);
                if (!letter.isEmpty()) {
                    int fontSize = cellSize / 2;
                    g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
                    FontMetrics fm = g.getFontMetrics();
                    int x = col * cellSize + (cellSize - fm.stringWidth(letter)) / 2;
                    int y = row * cellSize + ((cellSize - fm.getHeight()) / 2) + fm.getAscent();
                    g.drawString(letter, x, y);
                }
            }
        }

        for (Board.SOSLine line : board.getCompletedSOS()) {
            int startX = line.startCol * cellSize + cellSize / 2;
            int startY = line.startRow * cellSize + cellSize / 2;
            int endX = line.endCol * cellSize + cellSize / 2;
            int endY = line.endRow * cellSize + cellSize / 2;

            g.setColor(line.playerColor.equals("Blue") ? Color.BLUE : Color.RED);
            g.drawLine(startX, startY, endX, endY);
        }
    }

    public Game getGame() {
        return game;
    }
}
