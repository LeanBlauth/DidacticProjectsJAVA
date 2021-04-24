package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
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
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
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

	public boolean getCheckMateState() {
		return checkMate;
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

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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
		ChessPiece movingPiece = (ChessPiece) board.getPositionOccupant(target);
		check = (isInCheck(getOpponent())) ? true : false;
		if (isInCheckMate(getOpponent())) {
			checkMate = true;
		} else {
			nextTurn();
		}
		// Check for special move : En Passant
		if (movingPiece instanceof Pawn
				&& ( (target.getRow() - source.getRow() == 2) || (target.getRow() - source.getRow() == -2) )     ) {
			enPassantVulnerable = movingPiece;
		} else {
			enPassantVulnerable = null;
		}
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
		ChessPiece movingPiece = (ChessPiece) board.removePiece(source);
		movingPiece.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(movingPiece, target);
		if (capturedPiece != null) {
			piecesOnBoard.remove(capturedPiece);
			piecesCaptured.add(capturedPiece);
		}
		// Check for special move Castling
		if (movingPiece instanceof King && movingPiece.getMoveCount() == 1) {
			// Check for Castling on short side
			if (target.getColumn() == source.getColumn() + 2) {
				Position rookSource = new Position(target.getRow(), target.getColumn() + 1);
				Position rookTarget = new Position(source.getRow(), source.getColumn() + 1);
				ChessPiece movingRook = (ChessPiece) board.removePiece(rookSource);
				board.placePiece(movingRook, rookTarget);
				movingRook.increaseMoveCount();
			}
			// Check for Castling on long side
			if (target.getColumn() == source.getColumn() - 2) {
				Position rookSource = new Position(target.getRow(), target.getColumn() - 2);
				Position rookTarget = new Position(source.getRow(), source.getColumn() - 1);
				ChessPiece movingRook = (ChessPiece) board.removePiece(rookSource);
				board.placePiece(movingRook, rookTarget);
				movingRook.increaseMoveCount();
			}
		}
		// Check for special move En Passant
		if (movingPiece instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {
				Position capturedPosition;
				if (movingPiece.getColor() == Color.WHITE) {
					capturedPosition = new Position(target.getRow() + 1, target.getColumn());
				} else {
					capturedPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(capturedPosition);
				piecesCaptured.add(capturedPiece);
				piecesOnBoard.remove(capturedPiece);
			}
		}
		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece movingPiece = (ChessPiece) board.removePiece(target);
		movingPiece.decreaseMoveCount();
		board.placePiece(movingPiece, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			piecesOnBoard.add(capturedPiece);
			piecesCaptured.remove(capturedPiece);
		}
		// Check for special move castling
		if (movingPiece instanceof King && movingPiece.getMoveCount() == 0) {
			// Check for special move castling : on short side
			if (target.getColumn() == source.getColumn() + 2) {
				Position rookSource = new Position(target.getRow(), target.getColumn() + 1);
				Position rookTarget = new Position(source.getRow(), source.getColumn() + 1);
				ChessPiece movingRook = (ChessPiece) board.removePiece(rookTarget);
				board.placePiece(movingRook, rookSource);
				movingRook.decreaseMoveCount();
			}
			// Check for special move castling : on long side
			if (target.getColumn() == source.getColumn() - 2) {
				Position rookSource = new Position(target.getRow(), target.getColumn() - 2);
				Position rookTarget = new Position(source.getRow(), source.getColumn() - 1);
				ChessPiece movingRook = (ChessPiece) board.removePiece(rookTarget);
				board.placePiece(movingRook, rookSource);
				movingRook.decreaseMoveCount();
			}
		}
		// Check for special move En Passant
		if (movingPiece instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece capturedPawn = (ChessPiece) board.removePiece(target);
				Position capturedPosition;
				if (movingPiece.getColor() == Color.WHITE) {
					capturedPosition = new Position(target.getRow() + 1, target.getColumn());
				} else {
					capturedPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				board.placePiece(capturedPawn, capturedPosition);
			}
		}
	}

	private Color getOpponent() {
		return (currentPlayer == Color.BLACK) ? Color.WHITE : Color.BLACK;
	}

	private ChessPiece getKingOfGivenColor(Color color) {
		List<Piece> piecesOfGivenColor = getPiecesOfGivenColor(color);
		for (Piece piece : piecesOfGivenColor) {
			if (piece instanceof King) {
				return (ChessPiece) piece;
			}
		}
		throw new IllegalStateException("Error: there is no " + color + " king on the board");
	}

	private boolean isInCheck(Color currentPlayer) {
		Position kingPosition = getKingOfGivenColor(currentPlayer).getChessPosition().toPosition();
		List<Piece> opponentPieces = getPiecesOfOppositeColor(currentPlayer);
		for (Piece piece : opponentPieces) {
			if (piece.possibleMoves()[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean isInCheckMate(Color currentPlayer) {
		if (!isInCheck(currentPlayer)) {
			return false;
		}
		List<Piece> currentPlayerPieces = getPiecesOfGivenColor(currentPlayer);
		for (Piece piece : currentPlayerPieces) {
			boolean[][] pieceMoves = piece.possibleMoves();
			for (int r = 0; r < board.getRows(); r++) {
				for (int c = 0; c < board.getColumns(); c++) {
					if (pieceMoves[r][c]) {
						Position source = ((ChessPiece) piece).getChessPosition().toPosition();
						Position target = new Position(r, c);
						Piece capturedPiece = makeMove(source, target);
						boolean inCheck = isInCheck(currentPlayer);
						undoMove(source, target, capturedPiece);
						if (!inCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnBoard.add(piece);
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private List<Piece> getPiecesOfOppositeColor(Color color) {
		return piecesOnBoard.stream().filter(x -> ((ChessPiece) x).getColor() != color).collect(Collectors.toList());
	}

	private List<Piece> getPiecesOfGivenColor(Color color) {
		return piecesOnBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
	}

	private void initialSetup() {

		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
//        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
//        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
//        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
//        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
//        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
//		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));



		placeNewPiece('c', 4, new Pawn(board, Color.BLACK, this)); // TESTING ERASE
		
		
		
		
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
//        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
//        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
//        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
//        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
//        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}
}
