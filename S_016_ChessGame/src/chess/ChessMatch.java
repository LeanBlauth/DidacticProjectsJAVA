package chess;

import gameboard.Board;

public class ChessMatch {

	static final int chessBoardRows = 8;
	static final int chessBoardColumns = 8;
	private Board board;
	
	public ChessMatch() {
		board = new Board(chessBoardRows, chessBoardColumns);
	}
	
	public ChessPiece[][] getChessPieces(){
		ChessPiece[][] pieceMatrix = new ChessPiece[board.getRows()][board.getColumns()];
		for (int r = 0 ; r < board.getRows() ; r++) {
			for (int c = 0 ; c < board.getColumns() ; c++) {
				pieceMatrix[r][c] = (ChessPiece) board.getPositionOccupant(r, c);
			}
		}
		return pieceMatrix;
	}
	
	
	
	
	
}
