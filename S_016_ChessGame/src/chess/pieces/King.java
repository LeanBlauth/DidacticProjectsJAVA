package chess.pieces;

import chess.Castling;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Direction;
import gameboard.Position;

public class King extends ChessPiece {

	private ChessMatch match;

	public King(Board board, Color color, ChessMatch match) {
		super(board, color);
		this.match = match;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean testRookCastling(Castling castlingType) {
		Position rookPosition;
		if (castlingType == Castling.SHORT) {
			rookPosition = directionStep(Direction.E, 3);
		} else {
			rookPosition = directionStep(Direction.W, 4);
		}
		ChessPiece assessedPiece = ((ChessPiece) (getBoard().getPositionOccupant(rookPosition)));
		if (assessedPiece != null && (assessedPiece instanceof Rook) && assessedPiece.getColor() == getColor()
				&& assessedPiece.getMoveCount() == 0) {
			return true;
		} else {
			return false;
		}
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
		// Check for special move castling
		if (getMoveCount() == 0 && !match.getCheckState()) {
			// Check short castling : King side
			if (	testRookCastling(Castling.SHORT)
					&& !getBoard().isPositionOccupied(directionStep(Direction.E, 1))
					&& !getBoard().isPositionOccupied(directionStep(Direction.E, 2))) {
				allowedMoves = stepValidation(allowedMoves, Direction.E, 2);
			}
			// Check long castling : Queen side
			if (	testRookCastling(Castling.LONG)
					&& !getBoard().isPositionOccupied(directionStep(Direction.W, 1))
					&& !getBoard().isPositionOccupied(directionStep(Direction.W, 2))
					&& !getBoard().isPositionOccupied(directionStep(Direction.W, 3))) {
				allowedMoves = stepValidation(allowedMoves, Direction.W, 2);
			}
		}

		return allowedMoves;
	}
}
