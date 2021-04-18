package applications;

import java.util.Locale;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class ChessGame {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		ChessMatch match = new ChessMatch();

		System.out.println("\n--- Chess Game ---");

		while (true) {
			UI.printBoard(match.getChessPieces());
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(scan);
			
			System.out.print("\nTarget: ");
			ChessPosition target = UI.readChessPosition(scan);
			
			ChessPiece capturedPiece = match.performChessMove(source, target);
		}

//		scan.close();
	}

}
