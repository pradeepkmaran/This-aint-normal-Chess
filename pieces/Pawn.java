package chess.pieces;
import chess.*;

public class Pawn implements Piece {
	
	private int x;
	private int y;	
	private int color;
	private int pieceCode;
	
	private ChessCalculator chess;
	
	public Pawn(int x, int y, int color, ChessCalculator chess) 
	{
		this.x = x;
		this.y = y;//1(black) or 6(white) initially
		this.color = color; // 1->black, 0->white
		this.chess = chess; //object of chess calculator
		this.pieceCode = 1;
	}		
	
	public void possibleMoves() {
		int xx = x;//5
		int yy = y;//6
		int add = -1;
		int firstRow = 7;
		if(color == 1) {
			add = 1;
			firstRow = 0;
		}
		
		if(yy == firstRow + add) {
			if(chess.getSquare(xx, yy+add*2) == -1 && chess.getSquare(xx, yy+add) == -1) {
				chess.submitMove(this, xx, yy+add*2);
			}
		}
		
		if(chess.getSquare(xx, yy+add) == -1) {
			chess.submitMove(this, xx, yy+add);
		}
		
		if(xx > 0) {
			int s = chess.getSquare(xx-1, yy+add);//if there is piece in top left
			if(s > -1 && s != color) {
				chess.submitMove(this, xx-1, yy+add);
			} 
		}
		
		if(xx < 7) {
			int s = chess.getSquare(xx+1, yy+add);//if there is piece in top right
			if(s > -1 && s != color) {
				chess.submitMove(this, xx+1, yy+add);
			} 
		}
	}
	
	
	public void getControl() {
		int xx = x;//0
		int yy = y;//1	
		int add = -1;
		if(color == 1) {
			add = 1;
		}
		
		if(xx > 0) {
			submitControl(xx-1, yy+add);
		}
		
		if(xx < 7) {
			submitControl(xx+1, yy+add);//1,2
		}
	}
	
	private void submitControl(int toX, int toY) //1,2
	{
		if(color == 0) {
			chess.setWControl(toX, toY);
		} else {
			chess.setBControl(toX, toY);
		}
	}
	
	public int getPieceCode() {
		return this.pieceCode;
	}
	
	public int getColor() {
		return this.color;
	}
		
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}