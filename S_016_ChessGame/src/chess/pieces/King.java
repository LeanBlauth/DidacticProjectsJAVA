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
		allowedMoves = stepValidation(allowedMoves, Direction.N);
		// Check S
		allowedMoves = stepValidation(allowedMoves, Direction.S);
		// Check W
		allowedMoves = stepValidation(allowedMoves, Direction.W);
		// Check E
		allowedMoves = stepValidation(allowedMoves, Direction.E);
		// Check NE
		allowedMoves = stepValidation(allowedMoves, Direction.NE);
		// Check NW
		allowedMoves = stepValidation(allowedMoves, Direction.NW);
		// Check SW
		allowedMoves = stepValidation(allowedMoves, Direction.SW);
		// Check SE
		allowedMoves = stepValidation(allowedMoves, Direction.SE);
		return allowedMoves;
	}
}
