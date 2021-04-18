package gameboard;

public abstract class Piece {

	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isTherePossibleMove() {
		boolean[][] moves = possibleMoves();
		for (int r = 0 ; r < moves.length ; r++) {
			for (int c = 0 ; c < moves[0].length ; c++) {
				if (moves[r][c]) return true;
			}
		}
		return false;
	}
}
