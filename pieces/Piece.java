package chess.pieces;
import chess.*;

public interface Piece {
	
	/*
	x: 0=left, 7=right
	y: 0=top, 7=down
	color: 0=white, 1=black
	
	getPieceCode: 1=Pawn, 2=Rook, 3=Knight, 4=Bishop, 5=Queen, 6=King
	*/
		
	public void possibleMoves();	
	
	public void getControl();
	
	public int getPieceCode();
	
	public int getColor();
	
	public int getX();
	
	public int getY();
	
	public void setX(int x);
	
	public void setY(int y);
}