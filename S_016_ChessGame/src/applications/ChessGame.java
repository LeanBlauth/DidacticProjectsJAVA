package applications;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		List<ChessPiece> capturedList = new ArrayList<>();
		ChessMatch match = new ChessMatch();

		while (!match.getCheckMateState()) {
			try {
				UI.printMatch(match, capturedList);
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(scan);
				
				boolean[][] possibleMoves = match.possibleMoves(source);
				UI.printBoard(match.getChessPieces(), possibleMoves);
				
				System.out.print("\nTarget: ");
				ChessPosition target = UI.readChessPosition(scan);
				
				ChessPiece capturedPiece = match.performChessMove(source, target);
					
				if (capturedPiece != null) {
					capturedList.add(capturedPiece);
				}
				
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
		UI.clearScreen();
		UI.printMatch(match, capturedList);
		scan.close();
	}

}
