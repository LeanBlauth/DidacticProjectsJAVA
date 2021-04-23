package gameboard;

public class Position {

	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Position(Position position) {
		this.row = position.getRow();
		this.column = position.getColumn();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setPosition(int row, int column) {
		setRow(row);
		setColumn(column);
	}
	
	public void setPosition(Position position) {
		setRow(position.row);
		setColumn(position.column);
	}
	
	@Override
	public String toString() {
		return "("+row + ", " + column+")";
	}
}
