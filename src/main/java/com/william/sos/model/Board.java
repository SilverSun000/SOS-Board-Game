package com.william.sos.model;

public class Board {
	private int[][] grid;
	private char turn = 'X';


	public Board() {
		grid = new int[3][3];
	}

	public int getCell(int row, int column) {
		return 	(row >= 0 && row < 3 && column >= 0 && column < 3) ? grid[row][column] : -1;
	}
		
	public char getTurn() {
		return turn;	
	}
		
}
