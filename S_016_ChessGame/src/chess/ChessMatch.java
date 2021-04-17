package chess;

import chess.pieces.Rook;
import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

public class ChessMatch {

	static final int chessBoardRows = 8;
	static final int chessBoardColumns = 8;
	private Board board;
	
	public ChessMatch() {
		board = new Board(chessBoardRows, chessBoardColumns);
		initialSetup();
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
	
	private void initialSetup() {
		board.placePiece(new Rook(board,Color.BLACK), new Position(2,1));
	}
	
	
	
}
