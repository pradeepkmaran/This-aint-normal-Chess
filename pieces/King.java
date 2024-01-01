package chess.pieces;
import chess.*;

public class King implements Piece {
	
	private int x;
	private int y;	
	private int color;
	private int pieceCode;
	
	private ChessCalculator chess;
	
	public King(int x, int y, int color, ChessCalculator chess) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.chess = chess;
		this.pieceCode = 6;
		
	}
		
	public void possibleMoves() {
		
		int xx = x;
		int yy = y;
		int i, k;
		for(i=-1; i<2; i++) {
			for(k=-1; k<2; k++) {
				if(xx+i <= 7 && xx+i >= 0 && yy+k <= 7 && yy+k >= 0) {
					if(i != 0 || k != 0) {
						
						if(color == 0) {
							if(chess.getSquare(xx+i, yy+k) != color) {
								chess.submitMove(this, xx+i, yy+k);
							}
						} else {
							if(chess.getSquare(xx+i, yy+k) != color) {
								chess.submitMove(this, xx+i, yy+k);
							}
						}
					}
				}
			}
		}
		
		if(color == 0) {
			chess.refreshBlackControl(null);
		} else {
			chess.refreshWhiteControl(null);
		}
//---------------------------------------\
		if(color == 0)
		{
			if(chess.getBControl(x, y)>0)
			{
				i = 1;
				while(xx+i <= 7 && yy+i <= 7) {
					
					if(chess.getSquare(xx+i, yy+i) == color) {
						if(chess.getPiece(xx, yy-i) != null)
						{	
							if(chess.getPiece(xx,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx, yy-i);
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
				while(xx-i >= 0 && yy+i <= 7) {
					
					if(chess.getSquare(xx-i, yy+i) == color) {
						if(chess.getPiece(xx-i, yy+i) != null)
						{	
							if(chess.getPiece(xx-i,yy+i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx-i, yy+i);
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
				while(xx+i <= 7 && yy-i >= 0) {
					
					if(chess.getSquare(xx+i, yy-i) == color) {
						if(chess.getPiece(xx+i, yy-i) != null)
						{	
							if(chess.getPiece(xx+i,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx+i, yy-i);
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
				while(xx-i >= 0 && yy-i >= 0) {
					
					if(chess.getSquare(xx-i, yy-i) == color) {
						if(chess.getPiece(xx-i, yy-i) != null)
						{	
							if(chess.getPiece(xx-i,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx-i, yy-i);
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
				while(xx + i <= 7) {
					
					if(chess.getSquare(xx+i, yy) == color) {
						if(chess.getPiece(xx+i, yy) != null)
						{	
							if(chess.getPiece(xx+i,yy).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx+i, yy);
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
					
					if(chess.getSquare(xx-i, yy) == color) {
						if(chess.getPiece(xx-i, yy) != null)
						{	
							if(chess.getPiece(xx-i,yy).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx-i, yy);
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
				while(yy + i <= 7) {
					
					if(chess.getSquare(xx, yy+i) == color) {
						if(chess.getPiece(xx, yy+i) != null)
						{	
							if(chess.getPiece(xx,yy+i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx, yy+i);
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
				while(yy - i >= 0) {
					
					if(chess.getSquare(xx, yy-i) == color) {
						if(chess.getPiece(xx, yy-i) != null)
						{	
							if(chess.getPiece(xx,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx, yy-i);
							}
							else
							{
								break;
							}
						}
					}
					i++;
				}
			}	
		}
		else
		{
			if(chess.getWControl(x, y)>0)
			{
				i = 1;
				while(xx+i <= 7 && yy+i <= 7) {
					
					if(chess.getSquare(xx+i, yy+i) == color) {
						if(chess.getPiece(xx, yy-i) != null)
						{	
							if(chess.getPiece(xx,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx, yy-i);
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
				while(xx-i >= 0 && yy+i <= 7) {
					
					if(chess.getSquare(xx-i, yy+i) == color) {
						if(chess.getPiece(xx-i, yy+i) != null)
						{	
							if(chess.getPiece(xx-i,yy+i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx-i, yy+i);
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
				while(xx+i <= 7 && yy-i >= 0) {
					
					if(chess.getSquare(xx+i, yy-i) == color) {
						if(chess.getPiece(xx+i, yy-i) != null)
						{	
							if(chess.getPiece(xx+i,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx+i, yy-i);
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
				while(xx-i >= 0 && yy-i >= 0) {
					
					if(chess.getSquare(xx-i, yy-i) == color) {
						if(chess.getPiece(xx-i, yy-i) != null)
						{	
							if(chess.getPiece(xx-i,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx-i, yy-i);
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
				while(xx + i <= 7) {
					
					if(chess.getSquare(xx+i, yy) == color) {
						if(chess.getPiece(xx+i, yy) != null)
						{	
							if(chess.getPiece(xx+i,yy).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx+i, yy);
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
					
					if(chess.getSquare(xx-i, yy) == color) {
						if(chess.getPiece(xx-i, yy) != null)
						{	
							if(chess.getPiece(xx-i,yy).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx-i, yy);
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
				while(yy + i <= 7) {
					
					if(chess.getSquare(xx, yy+i) == color) {
						if(chess.getPiece(xx, yy+i) != null)
						{	
							if(chess.getPiece(xx,yy+i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx, yy+i);
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
				while(yy - i >= 0) {
					
					if(chess.getSquare(xx, yy-i) == color) {
						if(chess.getPiece(xx, yy-i) != null)
						{	
							if(chess.getPiece(xx,yy-i).getPieceCode()==5 )
							{
								chess.submitEmergencyMove(this, xx, yy-i);
							}
							else
							{
								break;
							}
						}
					}
					i++;
				}
			}	
		}
//---------------------------------------
	} 
	
	public void getControl() {
		int xx = x;
		int yy = y;
		int i, k;
		for(i=-1; i<2; i++) {
			for(k=-1; k<2; k++) {
				if(xx+i <= 7 && xx+i >= 0 && yy+k <= 7 && yy+k >= 0) {
					if(i != 0 || k != 0) {
						submitControl(xx+i, yy+k);
					}
				}
			}
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