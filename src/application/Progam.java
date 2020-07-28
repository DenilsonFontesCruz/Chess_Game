package application;

import java.util.InputMismatchException;
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
		
		while(true) {
		try {
			
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces());
			
			System.out.print("\n\nOrigem: ");
			ChessPosition source = UI.readChessPosition(inp);
			
			boolean[][] possibleMoves = chessMatch.possibleMoves(source);
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces(), possibleMoves);
			
			System.out.print("\n\nDestino: ");
			ChessPosition target = UI.readChessPosition(inp);
			
			ChessPiece capturedChessPiece = chessMatch.performChessMove(source, target);
		
		}
		catch(ChessException e){
			System.out.println(e.getMessage());
			inp.next();
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			inp.next();
		}
		
		}

	}
}
