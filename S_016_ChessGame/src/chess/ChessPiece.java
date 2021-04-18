package chess;

import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece assessedPiece = (ChessPiece) getBoard().getPositionOccupant(position);
		if (assessedPiece.getColor() != color)
			return true;
		else
			return false;
	}
}
