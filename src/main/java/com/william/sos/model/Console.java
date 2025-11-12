package com.william.sos.model;

public class Console {
	private Board board;

	public Console(Board board) {
		this.board = board;
	}
	
	public void displayBoard() {
		for (int row = 0; row<3; row++) {
			System.out.println("-------");
			System.out.print("|"+board.getSquare(row, 0));
			System.out.print("|"+board.getSquare(row, 1));
			System.out.print("|"+board.getSquare(row, 2));
			System.out.println("|");
		}
		System.out.println("-------");		
	}
}
