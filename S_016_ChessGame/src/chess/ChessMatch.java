package chess;

import chess.pieces.King;
import chess.pieces.Rook;
import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

public class ChessMatch {

	static final int chessBoardRows = 8;
	static final int chessBoardColumns = 8;
	private int turn;
	private Color currentPlayer;
	private Board board;

	public ChessMatch() {
		board = new Board(chessBoardRows, chessBoardColumns);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessPiece[][] getChessPieces() {
		ChessPiece[][] pieceMatrix = new ChessPiece[board.getRows()][board.getColumns()];
		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getColumns(); c++) {
				pieceMatrix[r][c] = (ChessPiece) board.getPositionOccupant(r, c);
			}
		}
		return pieceMatrix;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		validateSourcePosition(sourcePosition.toPosition());
		return board.getPositionOccupant(sourcePosition.toPosition()).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.isPositionOccupied(position)) {
			throw new ChessException("Invalid move: there's no piece on source position");
		}
		if (currentPlayer != ((ChessPiece) board.getPositionOccupant(position)).getColor()) {
			throw new ChessException("Invalid piece: chosen piece belongs to opponent");
		}
		if (!board.getPositionOccupant(position).isTherePossibleMove()) {
			throw new ChessException("Invalid move: currently no allowed moves for this piece");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.getPositionOccupant(source).possibleMoves()[target.getRow()][target.getColumn()]) {
			throw new ChessException("Invalid move: target position not allowed");
		}
	}

	private Piece makeMove(Position source, Position target) {
		Piece movingPiece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(movingPiece, target);
		return capturedPiece;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void initialSetup() {

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

	}

}
