package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
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

		return allowedMoves;
	}
}
