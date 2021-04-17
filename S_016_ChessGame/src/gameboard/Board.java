package gameboard;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece getPositionOccupant(int row, int column) {
		if (!positionExists(row,column)) {
			throw new BoardException("Error: position does not exist on the current board");
		}
		return pieces[row][column];
	}
	
	public Piece getPositionOccupant(Position position) {
		return pieces[position.getRow()][position.getColumn()]; 
	}
	
	public void placePiece(Piece piece, Position position) {
		if (isPositionOccupied(position)) {
			throw new BoardException("The position "+position+" is already occupied");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position; 
	}
	
	public boolean positionExists(int row, int column) {
		return row >= 0 && row <= rows && column >= 0 && column <= columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean isPositionOccupied(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error: position does not exist on the current board");
		}
		return getPositionOccupant(position) != null;
		
	}
}
