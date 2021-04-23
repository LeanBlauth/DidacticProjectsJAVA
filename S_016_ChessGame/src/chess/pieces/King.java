package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;

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
		// Check N
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.N));
		// Check S
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.S));
		// Check W
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.W));
		// Check E
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.E));
		// Check NE
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.NE));
		// Check NW
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.NW));
		// Check SW
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.SW));
		// Check SE
		allowedMoves = stepValidation(allowedMoves, directionStep(Direction.SE));
		return allowedMoves;
	}
}
