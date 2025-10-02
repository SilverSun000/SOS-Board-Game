package com.william.sos.gui;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	
	private static final int MIN_BOARD_SIZE = 3;
	private static final int DEFAULT_BOARD_SIZE = 3;
	
	private JTextField sizeInput;
	private JButton startButton;
	private BoardPanel boardPanel;

	public MainFrame() {
		setTitle("SOS Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Board Size: "));

		sizeInput = new JTextField(5);
		sizeInput.setText(String.valueOf(DEFAULT_BOARD_SIZE));
		topPanel.add(sizeInput);

		startButton = new JButton("Start");
		startButton.addActionListener(e -> rebuildBoard());
		topPanel.add(startButton);

		add(topPanel, BorderLayout.NORTH);

		boardPanel = new BoardPanel(DEFAULT_BOARD_SIZE);
		add(boardPanel, BorderLayout.CENTER);

		pack();
		setVisible(true);
	}
	
	private void rebuildBoard() {
		int boardSize;
		try {
			boardSize = Integer.parseInt(sizeInput.getText());
			if (boardSize < MIN_BOARD_SIZE) {
				JOptionPane.showMessageDialog(this, "Board size must be >= 3");
				return;
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Invalid board size!");
			boardSize = DEFAULT_BOARD_SIZE;
			return;
		}

		remove(boardPanel);
		boardPanel = new BoardPanel(boardSize);
		add(boardPanel, BorderLayout.CENTER);
		
		// Refresh window
		pack();
		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainFrame());
	}
}