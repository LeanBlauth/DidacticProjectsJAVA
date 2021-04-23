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
		case N:
			return getRelativePosition(-1, 0);
		case S:
			return getRelativePosition(+1, 0);
		case W:
			return getRelativePosition(0, -1);
		case E:
			return getRelativePosition(0, +1);
		case NW:
			return getRelativePosition(-1, -1);
		case NE:
			return getRelativePosition(-1, +1);
		case SW:
			return getRelativePosition(+1, -1);
		case SE:
			return getRelativePosition(+1, +1);
		default:
			return null;
		}
	}

	public Position directionStep(Direction direction, int numberOfSteps) {
		switch (direction) {
		case N:
			return getRelativePosition(-numberOfSteps, 0);
		case S:
			return getRelativePosition(+numberOfSteps, 0);
		case W:
			return getRelativePosition(0, -numberOfSteps);
		case E:
			return getRelativePosition(0, +numberOfSteps);
		case NW:
			return getRelativePosition(-numberOfSteps, -numberOfSteps);
		case NE:
			return getRelativePosition(-numberOfSteps, +numberOfSteps);
		case SW:
			return getRelativePosition(+numberOfSteps, -numberOfSteps);
		case SE:
			return getRelativePosition(+numberOfSteps, +numberOfSteps);
		default:
			return null;
		}
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece assessedPiece = (ChessPiece) getBoard().getPositionOccupant(position);
		if (assessedPiece != null && assessedPiece.getColor() != color) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isThereMyPiece(Position position) {
		ChessPiece assessedPiece = (ChessPiece) getBoard().getPositionOccupant(position);
		if (assessedPiece == null) {
			return false;
		} else {

			if (assessedPiece.getColor() == color)
				return true;
			else
				return false;
		}
	}

	protected boolean[][] stepValidation(boolean[][] mat, Direction direction) {
		Position assessedPosition = directionStep(direction);
		if (getBoard().positionExists(assessedPosition) && (!isThereMyPiece(assessedPosition))) {
			mat[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return mat;
	}

	protected boolean[][] stepValidation(boolean[][] mat, Direction direction, int numberOfSteps) {
		Position assessedPosition = directionStep(direction, numberOfSteps);
		if (getBoard().positionExists(assessedPosition) && (!isThereMyPiece(assessedPosition))) {
			mat[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return mat;
	}

	public boolean[][] stepValidationCapturedOly(boolean[][] mat, Direction direction) {
		Position assessedPosition = directionStep(direction);
		if (getBoard().positionExists(assessedPosition) && (isThereOpponentPiece(assessedPosition))) {
			mat[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		}
		return mat;
	}

	public boolean[][] directionValidation(boolean[][] allowedMoves, Direction direction) {
		int rowStep = 0, colStep = 0;
		Position assessedPosition = new Position(position.getRow(), position.getColumn());
		assessedPosition.setPosition(directionStep(direction));
		while (isPositionValidToMove(assessedPosition) && isPreviousPositionEmpty(assessedPosition, direction)) {
			allowedMoves = validatePosition(allowedMoves, assessedPosition);
			switch (direction) {
			case N:
				assessedPosition.setPosition(directionStep(direction).getRow() + rowStep--, assessedPosition.getColumn());
				break;
			case S:
				assessedPosition.setPosition(directionStep(direction).getRow() + rowStep++, assessedPosition.getColumn());
				break;
			case W:
				assessedPosition.setPosition(assessedPosition.getRow(), directionStep(direction).getColumn() + colStep--);
				break;
			case E:
				assessedPosition.setPosition(assessedPosition.getRow(), directionStep(direction).getColumn() + colStep++);
				break;
			case NE:
				assessedPosition.setPosition(directionStep(direction).getRow() + rowStep--, directionStep(direction).getColumn() + colStep++);
				break;
			case NW:
				assessedPosition.setPosition(directionStep(direction).getRow() + rowStep--, directionStep(direction).getColumn() + colStep--);
				break;
			case SE:
				assessedPosition.setPosition(directionStep(direction).getRow() + rowStep++, directionStep(direction).getColumn() + colStep++);
				break;
			case SW:
				assessedPosition.setPosition(directionStep(direction).getRow() + rowStep++, directionStep(direction).getColumn() + colStep--);
				break;
			}
		}
		return allowedMoves;
	}

	public boolean isPositionValidToMove(Position assessedPosition) {
		if (getBoard().positionExists(assessedPosition) && (!isThereMyPiece(assessedPosition))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPreviousPositionEmpty(Position assessedPosition, Direction direction) {
		Position previousPosition = new Position(assessedPosition);
		switch (direction) {
		case N:
			previousPosition.setRow(assessedPosition.getRow() + 1);
			break;
		case S:
			previousPosition.setRow(assessedPosition.getRow() - 1);
			break;
		case W:
			previousPosition.setColumn(assessedPosition.getColumn() + 1);
			break;
		case E:
			previousPosition.setColumn(assessedPosition.getColumn() - 1);
			break;
		case NE:
			previousPosition.setPosition(assessedPosition.getRow() + 1, assessedPosition.getColumn() - 1);
			break;
		case NW:
			previousPosition.setPosition(assessedPosition.getRow() + 1, assessedPosition.getColumn() + 1);
			break;
		case SE:
			previousPosition.setPosition(assessedPosition.getRow() - 1, assessedPosition.getColumn() - 1);
			break;
		case SW:
			previousPosition.setPosition(assessedPosition.getRow() - 1, assessedPosition.getColumn() + 1);
			break;
		}
		if (isThereOpponentPiece(previousPosition)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean[][] validatePosition(boolean[][] allowedMoves, Position assessedPosition) {
		allowedMoves[assessedPosition.getRow()][assessedPosition.getColumn()] = true;
		return allowedMoves;
	}
}
