package applications;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class ChessGame {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		ChessMatch match = new ChessMatch();

		while (true) {
			try {
				UI.printBoard(match.getChessPieces());
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scan);
				
				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition(scan);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				scan.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				scan.nextLine();
			}
		}

//		scan.close();
	}

}
