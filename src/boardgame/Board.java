package boardgame;

public class Board {
	
	//Finished

	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;
	
	
	public Board(Integer rows, Integer columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error: � necessario que possua pelo menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		
		pieces = new Piece[rows][columns];
	}


	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public Piece piece(Integer row, Integer column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Error: Posi��o inexistente");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error: Posi��o inexistente");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Error: Ja existe uma pe�a na posi��o atual");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error: Posi��o inexistente");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error: Posi��o inexistente");		
		}
		return piece(position) != null;
	}
	
}
