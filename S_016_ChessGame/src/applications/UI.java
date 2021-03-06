package applications;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
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
	public static void clearScreen() {
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
	
	public static void printMatch(ChessMatch match, List<ChessPiece> capturedList) {
		printBoard(match.getChessPieces());
		System.out.println();
		printCapturedPieces(capturedList);
		System.out.println("\nTurn: " + match.getTurn());
		
		if (!match.getCheckMateState()) {
			System.out.print("\nPlayer: ");
			printCurrentPlayer(match);
			if (match.getCheckState()) {
				System.out.println("--- Your king is in check. Watch out ! \n");
			}
		} else {
			System.out.println("\n--- C H E C K M A T E ---\n");
			System.out.print("Winner: ");
			printCurrentPlayer(match);
		}
	}

	private static void printCurrentPlayer(ChessMatch match) {
		if (match.getCurrentPlayer() == Color.BLACK) {
			System.out.print(ANSI_YELLOW);
		}
		System.out.println(match.getCurrentPlayer() + "\n" + ANSI_RESET);
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
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("--- Captured pieces \n");
		System.out.print("White: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.println(ANSI_RESET);
		System.out.print("Black: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.println(ANSI_RESET);
		
	}
	
public static char getValidAnswer(char[] validSet, Scanner scan) {
		
		Boolean adequate = false;
		char answer;
		
		do {
			answer = (scan.next()).toUpperCase().charAt(0);
			scan.nextLine();
			
			for (char valid : validSet) {
				if (answer == valid) {
					adequate = true;
					break;
				}	
			}
			if (!adequate) System.out.print("\nPlease provide a valid answer: ");
		} while (!adequate); 
		return answer;
	}
}
