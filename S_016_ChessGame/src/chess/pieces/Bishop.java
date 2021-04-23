package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] allowedMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// Check NW
		allowedMoves = directionValidation(allowedMoves, Direction.NW);
		
		// Check NE
		allowedMoves = directionValidation(allowedMoves, Direction.NE);

		// Check SW
		allowedMoves = directionValidation(allowedMoves, Direction.SW);
		
		// Check SE
		allowedMoves = directionValidation(allowedMoves, Direction.SE);

		return allowedMoves;
	}
}
