package com.william.sos.gui;

import com.william.sos.model.*;
import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private static final int CELL_SIZE = 60;
    private Board board;
    private JButton[][] buttons;

    public BoardPanel(int boardSize) {
        setBoard(boardSize);
    }

    public void setBoard(int boardSize) {
        removeAll();

        board = new Board(boardSize);
		setLayout(new GridLayout(boardSize, boardSize));
		buttons = new JButton[boardSize][boardSize];

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				JButton btn = new JButton("");
				btn.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

				int r = row;
				int c = col;
				btn.addActionListener(e -> handleClick(r, c));

				buttons[row][col] = btn;
				add(btn);
			}
		}

        revalidate();
        repaint();
    }

    private void handleClick(int row, int col) {
		String letter = "S";

		boolean success = board.placeMove(row, col, letter);
		if (success) {
			buttons[row][col].setText(letter);
		} else {
			JOptionPane.showMessageDialog(this, "Square already taken!");
		}
	}

    public Board getBoard() {
        return board;
    }

    public void resetBoard(int boardSize) {
        setBoard(boardSize);
    }
}
