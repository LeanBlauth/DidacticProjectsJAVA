package chess.pieces;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;
import gameboard.Position;

public class Pawn extends ChessPiece {

	private ChessMatch match;

	public Pawn(Board board, Color color, ChessMatch match) {
		super(board, color);
		this.match = match;
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
			// Check for special move En Passant for the white pieces
			if (position.getRow() == 3) {
				Position left = directionStep(Direction.W);
				Position right = directionStep(Direction.E);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().getPositionOccupant(left) == match.getEnPassantVulnerable()) {
					allowedMoves[left.getRow() - 1][left.getColumn()] = true;
				}
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().getPositionOccupant(right) == match.getEnPassantVulnerable()) {
					allowedMoves[right.getRow() - 1][right.getColumn()] = true;
				}
			}

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
			// Check for special move En Passant for the black pieces
			if (position.getRow() == 4) {
				Position left = directionStep(Direction.W);
				Position right = directionStep(Direction.E);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left)
						&& getBoard().getPositionOccupant(left) == match.getEnPassantVulnerable()) {
					allowedMoves[left.getRow() + 1][left.getColumn()] = true;
				}
				if (getBoard().positionExists(right) && isThereOpponentPiece(right)
						&& getBoard().getPositionOccupant(right) == match.getEnPassantVulnerable()) {
					allowedMoves[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		return allowedMoves;
	}
}
