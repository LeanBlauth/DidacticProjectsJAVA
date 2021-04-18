package chess;

import chess.pieces.King;
import chess.pieces.Rook;
import gameboard.Board;
import gameboard.Piece;
import gameboard.Position;

public class ChessMatch {

	static final int chessBoardRows = 8;
	static final int chessBoardColumns = 8;
	private Board board;

	public ChessMatch() {
		board = new Board(chessBoardRows, chessBoardColumns);
		initialSetup();
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

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturedPiece = makeMove(source,target);
		return (ChessPiece) capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!board.isPositionOccupied(position)) {
			throw new ChessException("Invalid move: there's no piece on source position");
		}
		if (!board.getPositionOccupant(position).isTherePossibleMove()) {
			throw new ChessException("Invalid move: currently no possible moves for this piece");
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
