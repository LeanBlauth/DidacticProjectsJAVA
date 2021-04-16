package applications;

import chess.ChessPiece;

public abstract class UI { // User Interface

	private ChessPiece[][] chessPieces;
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		for (int r = pieces.length; r > 0 ;  r--) {
			System.out.print(r + " ");
			for (int c = 0 + 0 ; c < pieces[0].length; c++) {
				printPiece(pieces[r-1][c]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
