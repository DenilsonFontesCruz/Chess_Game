package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Progam {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner inp = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!chessMatch.getCheckMate()) {
		try {
			
			UI.clearScreen();
			UI.printMatch(chessMatch, captured);
			
			System.out.print(UI.ANSI_GREEN + "\nOrigem: " + UI.ANSI_YELLOW);
			ChessPosition source = UI.readChessPosition(inp);
			
			boolean[][] possibleMoves = chessMatch.possibleMoves(source);
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces(), possibleMoves);
			
			System.out.print(UI.ANSI_GREEN + "\n\nDestino: " + UI.ANSI_YELLOW);
			ChessPosition target = UI.readChessPosition(inp);
			
			ChessPiece capturedChessPiece = chessMatch.performChessMove(source, target);
		
			if( capturedChessPiece != null) {
				captured.add(capturedChessPiece);
			}
			
		}
		catch(ChessException e){
			System.out.println(e.getMessage());
			inp.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			inp.nextLine();
		}
		
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	}
}
