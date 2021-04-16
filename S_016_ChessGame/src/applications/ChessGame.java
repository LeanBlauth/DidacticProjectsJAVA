package applications;

import java.util.Locale;
import java.util.Scanner;

import gameboard.Board;

public class ChessGame {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		
		Board board = new Board(5,5);
		
		
		
		
		
		scan.close();
	}

}
