package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;
import gameboard.Position;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}
	
	private boolean[][] moveValidationN(boolean[][] allowedMoves, int rowSteps, int columnSteps) {
		Position assessedPosition = getRelativePosition(rowSteps, columnSteps);
		if (getBoard().positionExists(assessedPosition) && (!isThereMyPiece(assessedPosition))) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return allowedMoves;
	}
	
	/*	Knight (N) Positions
	 * 	- 8 - 1 -
	 *	7 - - - 2
	 *	- - N - -
	 *	6 - - - 3
	 *	- 5 - 4 -
	 */
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] allowedMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		// Check position 1
		allowedMoves = moveValidationN(allowedMoves, -2, +1);
		// Check position 2
		allowedMoves = moveValidationN(allowedMoves, -1, +2);
		// Check position 3
		allowedMoves = moveValidationN(allowedMoves, +1, +2);
		// Check position 4
		allowedMoves = moveValidationN(allowedMoves, +2, +1);
		// Check position 5
		allowedMoves = moveValidationN(allowedMoves, +2, -1);
		// Check position 6
		allowedMoves = moveValidationN(allowedMoves, +1, -2);
		// Check position 7
		allowedMoves = moveValidationN(allowedMoves, -1, -2);
		// Check position 8
		allowedMoves = moveValidationN(allowedMoves, -2, -1);
		
		return allowedMoves;
	}

}
