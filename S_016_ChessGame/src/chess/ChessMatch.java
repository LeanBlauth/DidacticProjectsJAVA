package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	private boolean check;
	private List<Piece> piecesOnBoard = new ArrayList<>();
	private List<Piece> piecesCaptured = new ArrayList<>();

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
	
	public boolean getCheckState() {
		return check;
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
		validateMove(source, target, capturedPiece);
		check = (isInCheck(getOpponent())) ? true : false;
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

	private void validateMove(Position source, Position target, Piece capturedPiece) {
		if (isInCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Invalid move: one is not allowed to put oneself in check");
		}
	}

	private Piece makeMove(Position source, Position target) {
		Piece movingPiece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(movingPiece, target);
		if (capturedPiece != null) {
			piecesOnBoard.remove(capturedPiece);
			piecesCaptured.add(capturedPiece);
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece movingPiece = board.removePiece(target);
		board.placePiece(movingPiece, source);
		if (capturedPiece != null) {
			piecesOnBoard.add(capturedPiece);
			piecesCaptured.remove(capturedPiece);
		}
	}

	private Color getOpponent(/* Color currentPlayer */) {
		return currentPlayer == Color.BLACK ? Color.WHITE : Color.BLACK;
	}

	private ChessPiece getKingOfGivenColor(Color color) {
		List<Piece> piecesOfGivenColor = piecesOnBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece piece : piecesOfGivenColor) {
			if (piece instanceof King) {
				return (ChessPiece) piece;
			}
		}
		throw new IllegalStateException("Error: there is no " + color + " king on the board");
	}

	private boolean isInCheck(Color currentPlayerColor) {
		Position kingPosition = getKingOfGivenColor(currentPlayerColor).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() != currentPlayerColor).collect(Collectors.toList());
		for (Piece piece : opponentPieces) {
			if (piece.possibleMoves()[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnBoard.add(piece);
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
