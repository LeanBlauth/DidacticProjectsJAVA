package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] allowedMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];

		// Check N
		allowedMoves = directionValidation(allowedMoves, Direction.N);

		// Check S
		allowedMoves = directionValidation(allowedMoves, Direction.S);

		// Check W
		allowedMoves = directionValidation(allowedMoves, Direction.W);

		// Check E
		allowedMoves = directionValidation(allowedMoves, Direction.E);

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
