package chess;

import gameboard.Board;
import gameboard.Direction;
import gameboard.Piece;
import gameboard.Position;

public abstract class ChessPiece extends Piece {

	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.toChessPosition(position);
	}
	
	public Position getRelativePosition(int rowStep, int columnStep) {
		Position relativePosition = new Position(position.getRow() + rowStep, position.getColumn() + columnStep);
		return relativePosition;
	}
	
	public Position directionStep(Direction direction) {
		switch (direction) {
		case N : return getRelativePosition(-1, 0);
		case S : return getRelativePosition(+1, 0);
		case W : return getRelativePosition( 0,-1);
		case E : return getRelativePosition( 0,+1);
		case NW: return getRelativePosition(-1,-1);
		case NE: return getRelativePosition(-1,+1);
		case SW: return getRelativePosition(+1,-1);
		case SE: return getRelativePosition(+1,+1);
		default: return null;
		}
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece assessedPiece = (ChessPiece) getBoard().getPositionOccupant(position);
		if (assessedPiece.getColor() != color)
			return true;
		else
			return false;
	}

	public boolean[][] stepValidation(boolean[][] mat, int rowStep, int columnStep) {
		return null;
	}
	
	public boolean[][] stepValidation(boolean[][] mat, Position assessedPosition) {
		if (getBoard().positionExists(assessedPosition) && (!getBoard().isPositionOccupied(assessedPosition)||isThereOpponentPiece(assessedPosition))) {		
			mat[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return mat;
	}
	
	public boolean[][] stepValidationCapturedOly(boolean[][] mat, Position assessedPosition) {
		if (getBoard().positionExists(assessedPosition) && (isThereOpponentPiece(assessedPosition))) {		
			mat[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return mat;
	}
	
	public boolean[][] directionValidation(boolean[][] allowedMoves, Direction direction) {
		int step = 0;
		Position assessedPosition = new Position(position.getRow(), position.getColumn());
		assessedPosition.setPosition(directionStep(direction));
		while (isPositionValidToMove(assessedPosition)) {
			allowedMoves = validatePosition(allowedMoves, assessedPosition);
			switch (direction) {
				case N : assessedPosition.setPosition(directionStep(direction).getRow() + step--, position.getColumn()); break;
				case S : assessedPosition.setPosition(directionStep(direction).getRow() + step++, position.getColumn()); break;
				case W : assessedPosition.setPosition(position.getRow(), directionStep(direction).getColumn() + step--); break;
				case E : assessedPosition.setPosition(position.getRow(), directionStep(direction).getColumn() + step++); break;
				default: break;
			}
		}
		return allowedMoves;
	}
		
	public boolean isPositionValidToMove(Position assessedPosition) {
		if(getBoard().positionExists(assessedPosition) && (!getBoard().isPositionOccupied(assessedPosition) || isThereOpponentPiece(assessedPosition))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean[][] validatePosition(boolean[][] allowedMoves, Position assessedPosition) {
		allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		return allowedMoves;
	}
}
