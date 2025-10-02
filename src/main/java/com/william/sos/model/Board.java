package com.william.sos.model;

public class Board {
	private String[][] grid;
	private int size; 


	// Initliaze size and grid with empty values
	public Board(int size) {
		this.size = size;
		
		grid = new String[size][size];
		for (int row = 0; row < size; row++){
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

	public boolean placeMove(int row, int col, String letter) {
		if (grid[row][col].equals("")) {
			grid[row][col] = letter;
			return true;
		}
		return false;
	}

	public String getCell(int row, int col) {
		return 	grid[row][col];
	}	
}
