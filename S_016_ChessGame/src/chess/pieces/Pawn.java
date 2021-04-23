package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] allowedMoves = new boolean[getBoard().getRows()][getBoard().getColumns()];

		if (getColor() == Color.WHITE) {
			// Check 1 step north
			allowedMoves = stepValidation(allowedMoves, Direction.N);
			// If 1st step is empty and it is the first move of the piece, check 2nd step
			// step
			if (!getBoard().isPositionOccupied(directionStep(Direction.N))) {
				if (getMoveCount() == 0) {
					allowedMoves = stepValidation(allowedMoves, Direction.N, 2);
				}
			}
			// Check 1 step NW
			allowedMoves = stepValidationCapturedOly(allowedMoves, Direction.NW);
			// Check 1 step NE
			allowedMoves = stepValidationCapturedOly(allowedMoves, Direction.NE);
		} else {
			// Check 1 step S
			allowedMoves = stepValidation(allowedMoves, Direction.S);
			// If 1st step is empty and it is the first move of the piece, check 2nd step
			if (!getBoard().isPositionOccupied(directionStep(Direction.S))) {
				if (getMoveCount() == 0) {
					allowedMoves = stepValidation(allowedMoves, Direction.S, 2);
				}
			}
			// Check 1 step SW
			allowedMoves = stepValidationCapturedOly(allowedMoves, Direction.SW);
			// Check 1 step SE
			allowedMoves = stepValidationCapturedOly(allowedMoves, Direction.SE);
		}
		return allowedMoves;
	}
}
