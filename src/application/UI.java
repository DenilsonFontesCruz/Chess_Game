package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
		
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
	
	public static void clearScreen() {
		 System.out.print("\033[H\033[2J");
		 System.out.flush();
		}
		
	public static ChessPosition readChessPosition(Scanner inp) {
		try {
		String s = inp.nextLine().toLowerCase();
		char column = s.charAt(0);
		int row = Integer.parseInt(s.substring(1));
		
		return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error: Valor invalido");
		}
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println("\n");
		
		printCapturedPieces(captured);
		
		if(!chessMatch.getCheckMate()) {
			System.out.println(ANSI_GREEN + "Jogador atual: " + ANSI_YELLOW + ((chessMatch.getCurrentPlayer() == Color.WHITE) ? "Roxo" : "Vermelho") + ANSI_RESET);
			
			if (chessMatch.getCheck()) {
				System.out.println(ANSI_CYAN + "\nVoce esta em Check!!" + ANSI_RESET);
			}
		}
		else {
			System.out.println(ANSI_CYAN + "CHECKMATE!!" + ANSI_RESET);
			
			if(chessMatch.getCurrentPlayer() == Color.WHITE) {
				System.out.println(ANSI_PURPLE + "\nVencedor: Roxo" + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_RED + "\nVencedor: Vermelho" + ANSI_RESET);
			}
		}
	}
		
	public static void printBoard(ChessPiece[][] pieces) {
		
		for(int i=0; i < pieces.length; i++) {
			System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK  + (8 - i) + " " + ANSI_RESET);
			for(int j=0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  A B C D E F G H" + ANSI_RESET);
	}
		public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
			
			for(int i=0; i < pieces.length; i++) {
				System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK  + (8 - i) + " " + ANSI_RESET);
				for(int j=0; j < pieces.length; j++) {
					printPiece(pieces[i][j], possibleMoves[i][j]);
				}
				System.out.println();
			}
		
		System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  A B C D E F G H" + ANSI_RESET);
	}
	
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_GREEN_BACKGROUND);
		}
    	if (piece == null) {
            System.out.print("o" + " " + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_PURPLE + piece + " " + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_RED + piece + " " + ANSI_RESET);
            }
            
        }
    	
	}
	

	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> capWhite = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> capBlack = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println(ANSI_GREEN + "Pecas capturadas: \n");
		System.out.print(ANSI_PURPLE + "Roxas: ");
		System.out.println(Arrays.toString(capWhite.toArray()) + "\n");
		System.out.print(ANSI_RED + "Vermelhas: ");
		System.out.println(Arrays.toString(capBlack.toArray()));
		System.out.println(ANSI_RESET);
	}
	
}
