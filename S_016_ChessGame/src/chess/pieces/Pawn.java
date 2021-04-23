package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;
import gameboard.Position;

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
			allowedMoves = stepValidation(allowedMoves, directionStep(Direction.N));
			if (getMoveCount() == 0 && getBoard().getPositionOccupant(directionStep(Direction.N)) == null) {
				// Check 2 steps N
				allowedMoves = stepValidation(allowedMoves, directionStep(Direction.N).getRow() + 1, 0);
			}
			// Check 1 step NW
			allowedMoves = stepValidationCapturedOly(allowedMoves, directionStep(Direction.NW));
			// Check 1 step NE
			allowedMoves = stepValidationCapturedOly(allowedMoves, directionStep(Direction.NE));
		} else {
			// Check 1 step S
			allowedMoves = stepValidation(allowedMoves, directionStep(Direction.S));
			if (getMoveCount() == 0 && getBoard().getPositionOccupant(directionStep(Direction.S)) == null ) {
				// Check 2 steps below
				allowedMoves = stepValidation(allowedMoves, directionStep(Direction.S).getRow() + 1, 0);
			}
			// Check 1 step SW
			allowedMoves = stepValidationCapturedOly(allowedMoves, directionStep(Direction.SW));
			// Check 1 step SE
			allowedMoves = stepValidationCapturedOly(allowedMoves, directionStep(Direction.SE));			
		}
		return allowedMoves;
	}
}
