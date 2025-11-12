package com.william.sos.gui;

import com.william.sos.model.Board;
import com.william.sos.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardPanel extends JPanel {

    private Game game;
    private int boardSize;
    private static final int FIXED_BOARD_SIZE = 600; // fixed drawing area

    public BoardPanel(int boardSize) {
        this.boardSize = boardSize;
        this.game = new Game(boardSize);

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

        // inside BoardPanel.java
    private java.util.function.Supplier<String> letterSupplier;

    public void setLetterSupplier(java.util.function.Supplier<String> supplier) {
        this.letterSupplier = supplier;
    }

    // Update handleMove:
    private void handleMove(int row, int col) {
        String letter = letterSupplier.get(); // Get current player's choice
        boolean success = game.makeMove(row, col, letter);
        if (success) {
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move! Square already occupied.");
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = FIXED_BOARD_SIZE / boardSize;
        Board board = game.getBoard();

        // Draw grid lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= boardSize; i++) {
            g.drawLine(i * cellSize, 0, i * cellSize, FIXED_BOARD_SIZE); // vertical
            g.drawLine(0, i * cellSize, FIXED_BOARD_SIZE, i * cellSize); // horizontal
        }

        // Draw letters
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
    }

    public Game getGame() {
        return game;
    }
}
