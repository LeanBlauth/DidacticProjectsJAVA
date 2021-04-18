package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Position;

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
		Position assessedPosition = new Position(position.getRow(), position.getColumn());

		// Check above
		assessedPosition.setPosition(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(assessedPosition) && !getBoard().isPositionOccupied(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
			assessedPosition.setRow(assessedPosition.getRow() - 1);
		}
		if (getBoard().positionExists(assessedPosition) && isThereOpponentPiece(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}

		// Check below
		assessedPosition.setPosition(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(assessedPosition) && !getBoard().isPositionOccupied(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
			assessedPosition.setRow(assessedPosition.getRow() + 1);
		}
		if (getBoard().positionExists(assessedPosition) && isThereOpponentPiece(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}

		// Check to the left
		assessedPosition.setPosition(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(assessedPosition) && !getBoard().isPositionOccupied(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
			assessedPosition.setColumn(assessedPosition.getColumn() - 1);
		}
		if (getBoard().positionExists(assessedPosition) && isThereOpponentPiece(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		
		// Check to the right
		assessedPosition.setPosition(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(assessedPosition) && !getBoard().isPositionOccupied(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
			assessedPosition.setColumn(assessedPosition.getColumn() + 1);
		}
		if (getBoard().positionExists(assessedPosition) && isThereOpponentPiece(assessedPosition)) {
			allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}

		return allowedMoves;
	}

}
