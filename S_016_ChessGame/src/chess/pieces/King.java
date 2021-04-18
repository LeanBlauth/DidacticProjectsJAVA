package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Position;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] allowedMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		// Check above
		allowedMoves = stepValidation(allowedMoves, -1, +0);
		
		// Check below
		allowedMoves = stepValidation(allowedMoves, +1, +0);

		// Check to the left
		allowedMoves = stepValidation(allowedMoves, 0, -1);
		
		// Check to the right
		allowedMoves = stepValidation(allowedMoves, +0, +1);
		
		// Check right & above
		allowedMoves = stepValidation(allowedMoves, -1, +1);
		
		// Check right & below
		allowedMoves = stepValidation(allowedMoves, +1, +1);
		
		// Check left & above
		allowedMoves = stepValidation(allowedMoves, -1, -1);		
		
		// Check left & below
		allowedMoves = stepValidation(allowedMoves, +1, -1);
		
		return allowedMoves;
	}
	
	public boolean[][] stepValidation(boolean[][] mat, int rowStep, int columnStep) {
		Position assessedPosition = new Position(position.getRow() + rowStep, position.getColumn() + columnStep);
		if (getBoard().positionExists(assessedPosition) && (!getBoard().isPositionOccupied(assessedPosition)||isThereOpponentPiece(assessedPosition))) {		
			mat[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return mat;
	}
}
