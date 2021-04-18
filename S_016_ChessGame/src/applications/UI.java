package applications;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public abstract class UI { // User Interface

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	private static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner scan) {
		try {
			String position = scan.nextLine();
			char column = position.charAt(0);
			int row = Integer.parseInt(position.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition");
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		clearScreen();
		System.out.println("\n--- Chess Game ---\n");
		for (int r = 0; r < pieces.length; r++) {
			System.out.print((pieces.length - r) + " ");
			for (int c = 0 + 0; c < pieces[0].length; c++) {
				printPiece(pieces[r][c]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h \n");
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		clearScreen();
		System.out.println("\n--- Chess Game ---\n");
		for (int r = 0; r < pieces.length; r++) {
			System.out.print((pieces.length - r) + " ");
			for (int c = 0 + 0; c < pieces[0].length; c++) {

				if (possibleMoves[r][c]) {
					System.out.print(ANSI_RED_BACKGROUND);
				}
				printPiece(pieces[r][c]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h \n");
	}

	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
}
