package com.william.sos.gui;

import java.awt.*;
import javax.swing.*;
import com.william.sos.model.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static final int CELL_SIZE = 100;
	public static final int GRID_WIDTH = 8;
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;

	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
	public static final int SYMBOL_STROKE_WDITH = 8;

	private int CANVAS_WIDTH;
	public int CANVAS_HEIGHT;

	private GameBoardCanvas gameboardCanvas;

	private Board board;

	public MainFrame(Board board) {
		this.board = board;
		setContentPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setTitle("Tic Tac Toe");
		setVisible(true);
	}

	public Board getBoard(){
		return board;
	}

	private void setContentPanel() {
		gameboardCanvas = new GameBoardCanvas();
		CANVAS_WIDTH = CELL_SIZE * 3;
		CANVAS_HEIGHT = CELL_SIZE * 3;
		gameboardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameboardCanvas, BorderLayout.CENTER);
	}

	class GameBoardCanvas extends JPanel {
		GameBoardCanvas(){}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.WHITE);
			drawGridLines(g);
		}

		private void drawGridLines(Graphics g) {
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < 3; row++) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDTH_HALF, CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < 3; col++) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDTH_HALF, 0, GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(new Board());
			}
		});
	}
		
}
