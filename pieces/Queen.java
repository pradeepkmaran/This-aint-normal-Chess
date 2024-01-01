package chess.pieces;
import chess.*;

public class Queen implements Piece {
	
	private int x;
	private int y;	
	private int color;
	private int pieceCode;
	
	private ChessCalculator chess;
	
	public Queen(int x, int y, int color, ChessCalculator chess) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.chess = chess;
		this.pieceCode = 5;
	}
		
	public void possibleMoves() {
		int xx = x;
		int yy = y;
		int i;
		
		i = 1;
		while(xx+i <= 7 && yy+i <= 7) {
			if(chess.getSquare(xx+i, yy+i) != color) {
				chess.submitMove(this, xx+i, yy+i);
			}
			
			if(chess.getSquare(xx+i, yy+i) > -1) {
				break;
			}
			i++;
		}
		i = 1;
		while(xx-i >= 0 && yy+i <= 7) {
			if(chess.getSquare(xx-i, yy+i) != color) {
				chess.submitMove(this, xx-i, yy+i);
			}
			
			if(chess.getSquare(xx-i, yy+i) > -1) {
				break;
			}
			i++;
		}
		i = 1;
		while(xx+i <= 7 && yy-i >= 0) {
			if(chess.getSquare(xx+i, yy-i) != color) {
				chess.submitMove(this, xx+i, yy-i);
			}
			
			if(chess.getSquare(xx+i, yy-i) > -1) {
				break;
			}
			i++;
		}
		i = 1;
		while(xx-i >= 0 && yy-i >= 0) {
			if(chess.getSquare(xx-i, yy-i) != color) {
				chess.submitMove(this, xx-i, yy-i);
			}
			
			if(chess.getSquare(xx-i, yy-i) > -1) {
				break;
			}
			i++;
		}	
		i = 1;
		while(xx + i <= 7) {
			if(chess.getSquare(xx+i, yy) != color) 
			{
				chess.submitMove(this, xx+i, yy);
			}
			
			if(chess.getSquare(xx+i, yy) > -1) 
			{
				if(chess.getPiece(xx+i, yy) != null)
				{	
					if(chess.getPiece(xx+i,yy).getPieceCode()==3 && i==1)
					{
						chess.submitNinjaMove(this, xx+i, yy);
					}
					else
					{
						break;
					}
				}
			}
			i++;
		}
				
		i = 1;
		while(xx - i >= 0) {
			if(chess.getSquare(xx-i, yy) != color) {
				chess.submitMove(this, xx-i, yy);
			}
			
			if(chess.getSquare(xx-i, yy) > -1) {
				if(chess.getSquare(xx-i, yy) > -1) 
				{
					if(chess.getPiece(xx-i, yy) != null)
					{	
						if(chess.getPiece(xx-i,yy).getPieceCode()==3 && i==1)
						{
							chess.submitNinjaMove(this, xx-i, yy);
						}
						else
						{
							break;
						}
					}
				}
			}
			i++;
		}
				
		i = 1;
		while(yy + i <= 7) {
			if(chess.getSquare(xx, yy+i) != color) {
				chess.submitMove(this, xx, yy+i);
			}
			
			if(chess.getSquare(xx, yy+i) > -1) {
				if(chess.getSquare(xx, yy+i) > -1) 
				{
					if(chess.getPiece(xx, yy+i) != null)
					{	
						if(chess.getPiece(xx,yy+i).getPieceCode()==3 && i==1)
						{
							chess.submitNinjaMove(this, xx, yy+i);
						}
						else
						{
							break;
						}
					}
				}
			}
			i++;
		}
				
		i = 1;
		while(yy - i >= 0) {
			if(chess.getSquare(xx, yy-i) != color) {
				chess.submitMove(this, xx, yy-i);
			}
			
			if(chess.getSquare(xx, yy-i) > -1) {
				if(chess.getSquare(xx, yy-i) > -1) 
				{
					if(chess.getPiece(xx, yy-i) != null)
					{	
						if(chess.getPiece(xx,yy-i).getPieceCode()==3 && i==1)
						{
							chess.submitNinjaMove(this, xx, yy-i);
						}
						else
						{
							break;
						}
					}
				}
			}
			i++;
		}		
	}
	
	public void getControl() {
		int xx = x;
		int yy = y;
		int i;
		
		i = 1;
		while(xx+i <= 7 && yy+i <= 7) {
			submitControl(xx+i, yy+i);
			if(chess.getSquare(xx+i, yy+i) > -1) {
				break;
			}
			i++;
		}
		i = 1;
		while(xx-i >= 0 && yy+i <= 7) {
			submitControl(xx-i, yy+i);
			if(chess.getSquare(xx-i, yy+i) > -1) {
				break;
			}
			i++;
		}
		i = 1;
		while(xx+i <= 7 && yy-i >= 0) {
			submitControl(xx+i, yy-i);
			if(chess.getSquare(xx+i, yy-i) > -1) {
				break;
			}
			i++;
		}
		i = 1;
		while(xx-i >= 0 && yy-i >= 0) {
			submitControl(xx-i, yy-i);
			if(chess.getSquare(xx-i, yy-i) > -1) {
				break;
			}
			i++;
		}	
		i = 1;
		while(xx + i <= 7) {
			submitControl(xx+i, yy);
			if(chess.getSquare(xx+i, yy) > -1) {
				break;
			}
			i++;
		}
				
		i = 1;
		while(xx - i >= 0) {
			submitControl(xx-i, yy);
			if(chess.getSquare(xx-i, yy) > -1) {
				break;
			}
			i++;
		}
				
		i = 1;
		while(yy + i <= 7) {
			submitControl(xx, yy+i);
			if(chess.getSquare(xx, yy+i) > -1) {
				break;
			}
			i++;
		}
				
		i = 1;
		while(yy - i >= 0) {
			submitControl(xx, yy-i);
			if(chess.getSquare(xx, yy-i) > -1) {
				break;
			}
			i++;
		}
	}
		
	private void submitControl(int toX, int toY) {
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