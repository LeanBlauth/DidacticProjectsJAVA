package chess;

import gameboard.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row > ChessMatch.chessBoardRows) {
			throw new ChessException("Error instantiating ChessPosition: invalid position");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position(ChessMatch.chessBoardRows - row , column - 'a');
	}
	
	protected ChessPosition fromPosition(Position position) {
		return new ChessPosition( (char) (position.getColumn() + 'a') , ChessMatch.chessBoardRows - position.getRow() );
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
