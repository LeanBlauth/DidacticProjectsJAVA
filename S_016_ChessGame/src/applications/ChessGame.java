package applications;

import java.util.Locale;
import java.util.Scanner;

import chess.ChessMatch;

public class ChessGame {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		ChessMatch match = new ChessMatch();
		
		System.out.println("\n--- Chess Game ---\n");
		
		UI.printBoard(match.getChessPieces());
		
		scan.close();
	}

}
