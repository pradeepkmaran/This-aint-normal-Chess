package chess;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ChessCalculator {
	
	private ArrayList<Piece> pieces;
	private int[][] bControl = new int[8][8];
	private int[][] wControl = new int[8][8];
	private int[] moves = new int[200];
	private int moveIndex = 0;
	private int [][] squares = new int[8][8];
	private int userChose;
	private BoardGraphics board;
	private Window window;
	private boolean whiteTurn = true;
	private King whiteKing;
	private King blackKing;
	private boolean promote = false;
	private int promoteX;
	private int promoteY;
	private boolean playerTurn;
	private Random rand;
	
	
	public ChessCalculator() {
		rand = new Random();
		reset();
	}
	
	public void reset() {
		int i, j;
		for(i=0; i<8; i++) {
			for(j=0; j<8; j++) {
				bControl[i][j] = 0;
				wControl[i][j] = 0;	
				squares[i][j] = -1; // not assigned to any colors
			}
		}
		
		userChose = 0;
		whiteTurn = true;
		
		pieces = new ArrayList<Piece>(32);
		
		for(i=0; i<8; i++) {
			// 1->black, 0->white
			pieces.add(new Pawn(i, 1, 1, this)); // x, y (box position), color, chessCalculator
			pieces.add(new Pawn(i, 6, 0, this));
			squares[i][0] = 1;
			squares[i][1] = 1;
			squares[i][6] = 0;
			squares[i][7] = 0;
			
		}
		
		pieces.add(new Rook(0, 0, 1, this));
		pieces.add(new Rook(7, 0, 1, this));
		pieces.add(new Rook(0, 7, 0, this));
		pieces.add(new Rook(7, 7, 0, this));
		
		pieces.add(new Knight(1, 0, 1, this));
		pieces.add(new Knight(6, 0, 1, this));
		pieces.add(new Knight(1, 7, 0, this));
		pieces.add(new Knight(6, 7, 0, this));
		
		pieces.add(new Bishop(2, 0, 1, this));
		pieces.add(new Bishop(5, 0, 1, this));
		pieces.add(new Bishop(2, 7, 0, this));
		pieces.add(new Bishop(5, 7, 0, this));
		
		pieces.add(new Queen(3, 0, 1, this));
		pieces.add(new Queen(3, 7, 0, this));
		
		blackKing = new King(4, 0, 1, this);
		whiteKing = new King(4, 7, 0, this);
		pieces.add(blackKing);
		pieces.add(whiteKing);
		
		calculateMoves();			
	}
	
	public void setBoard(BoardGraphics board) {
		this.board = board;
	}
	
	public void piecePromote(int pieceCode) {
		int col = 1;
		if(whiteTurn) {
			col = 0;
		}
		
		switch(pieceCode) {
			case 2:
			pieces.add(new Rook(promoteX, promoteY, col, this));
			break;
			case 3:
			pieces.add(new Knight(promoteX, promoteY, col, this));
			break;
			case 4:
			pieces.add(new Bishop(promoteX, promoteY, col, this));
			break;
			case 5:
			pieces.add(new Queen(promoteX, promoteY, col, this));
			break;
		}
		
		promote = false;
		
		if(whiteTurn) {
			whiteTurn = false;
		} else {
			whiteTurn = true;
		}
		
		board.drawPieces();
		calculateMoves();			
	}
	
	public void setWindow(Window window) {
		this.window = window;
	}
	
	public void calculateMoves() 
	{
		moveIndex = 0;
		int i, j;
		
		for(i=0; i<200; i++) 
		{
			moves[i] = 0;
		}
		
		int color;
		playerTurn = true;
		if(whiteTurn) { // player is default white
			color = 0;
			
		} else {
			color = 1;
			playerTurn = false;
		}
		
		for(Piece p : pieces) {
			if(p.getColor() == color) {
				p.possibleMoves();
			}
		}

		//If checkmate, game ends	
		if(moveIndex == 0) 
		{
			//no moves
			int stale=0;
			if(whiteTurn) 
			{
				if(bControl[whiteKing.getX()][whiteKing.getY()] == 0) 
				{
					stale=1;
				}
			} 
			else 
			{
				if(wControl[blackKing.getX()][blackKing.getY()] == 0) 
				{
					stale=1;
				}
			}

			int w = 0;
			if(whiteTurn) {
				w = 1;
			}
			window.showGameEnd(w,stale);
			return;
		}
		
		//Computer move
		if(!playerTurn) {
			if(whiteTurn) {
				doComputerMove(wControl, bControl);
			} else {
				doComputerMove(bControl, wControl);
			}			
		}
	}
	
	
	private void doComputerMove(int [][] ownControl, int [][] enemyControl) {
		int i;
		int move;
		int x, y, toX, toY;
		double [] evaluation = new double[moveIndex];
		int pieceCode;
		int enemyPieceCode;
		
		Piece p, p2;
		
		for(i=0; i<moveIndex; i++) {
			refreshBlackControl(null);
			refreshWhiteControl(null);
			
			evaluation[i] = 0;
			move = moves[i];
				
			x = move/1000;
			y = (move - x*1000)/100;
			toX = (move - x*1000 - y*100)/10;
			toY = move%10;	
			p = getPiece(x, y);
			pieceCode = 0;
			if(p != null) {
				pieceCode = p.getPieceCode();
			}

			//Check if piece is in danger
			if(enemyControl[x][y] > 0) {
				switch(pieceCode) {
					case 5:
					evaluation[i] += 5;
					break;
					
					case 4:
					if(enemyControl[x][y] > ownControl[x][y]) {
						evaluation[i] += 1.5;
					}
					break;
					
					case 3:
					if(enemyControl[x][y] > ownControl[x][y]) {
						evaluation[i] += 1.5;
					}
					break;
					
					case 2:
					evaluation[i] += 3;
					break;
				}
				
			}
			
			
			//Test move
			p2 = getPiece(toX, toY);
			enemyPieceCode = 0;
			p.setX(toX);
			p.setY(toY);
			squares[x][y] = -1;
			squares[toX][toY] = p.getColor();
			
			if(pieceCode != 1 || (toY > 0 && toY < 7)) { 
				refreshBlackControl(p2);
				refreshWhiteControl(p2);
			} else {
				//Pawn promote is good move
				evaluation[i] += 9;
			}

			
			if(p2 != null) {
				enemyPieceCode = p2.getPieceCode();
				
				switch (enemyPieceCode) {
					case 5:
					evaluation[i] += 9;
					break;
					
					case 4:
					evaluation[i] += 3;
					break;
					
					case 3:
					evaluation[i] += 3;
					break;
					
					case 2:
					evaluation[i] += 5;
					break;
					
					case 1:
					evaluation[i] += 1;
					break;
				}
			}
			
			if(enemyControl[toX][toY] > ownControl[toX][toY]) {
				switch (pieceCode) {
					case 5:
					evaluation[i] -= 8;
					break;
					
					case 4:
					evaluation[i] -= 2;
					break;
					
					case 3:
					evaluation[i] -= 2;
					break;
					
					case 2:
					evaluation[i] -= 4;
					break;				
				}
			}
		
			if(pieceCode == 3 || pieceCode == 4) {
				//if knight or bishop undeveloped
				if(y == 0 || y == 7) {
					evaluation[i] += 1;
				}
			}
			
			//Don't lose your queen
			if(pieceCode == 5 && enemyPieceCode != 5) {
				if(enemyControl[toX][toY] > ownControl[toX][toY]) {
					evaluation[i] -= 5;
				}
			}
			
			if(ownControl[toX][toY] >= enemyControl[toX][toY]) {
				//Checking is good
				if(whiteTurn) {
					if(ownControl[blackKing.getX()][blackKing.getY()] > 0) {
						evaluation[i] += 2;
					}
				} else {
					if(ownControl[whiteKing.getX()][whiteKing.getY()] > 0) {
						evaluation[i] += 2;
					}					
				}
				
				if(pieceCode == 1) {
					evaluation[i] += 0.5;
					if((x == 3 || x == 4) && (y == 1 || y == 6)) {
						evaluation[i] += 0.5;
					}
				}
			}
			
			
			//set pieces again
			p.setX(x);
			p.setY(y);
			squares[x][y] = p.getColor();
			
			if(p2 != null) {
				squares[toX][toY] = p2.getColor();
			} else {
				squares[toX][toY] = -1;
			}			
			
		}
		
		
		
		double maxEvaluation = evaluation[0];
		int amount = 1;
		
		for(i = 0; i < moveIndex; i++) {
			if(evaluation[i] > maxEvaluation) {
				maxEvaluation = evaluation[i];
				amount = 1;
			} else if(evaluation[i] == maxEvaluation) {
				amount++;
			}
		}
		
		int [] bestMoves = new int[amount];
		amount = 0;
		for(i = 0; i < moveIndex; i++) {
			
			if(evaluation[i] == maxEvaluation) {
				bestMoves[amount] = moves[i];
				amount++;
			}
		}
		
		int a = rand.nextInt(amount);
		move = bestMoves[a];		
		
		x = move/1000;
		y = (move - x*1000)/100;
		toX = (move - x*1000 - y*100)/10;
		toY = move%10;	
		doMove(x, y, toX, toY);		
	}
	
	public void submitMove(Piece p, int toX, int toY) //p,5,4
	{
		
		int xx = p.getX();//5
		int yy = p.getY();//6
		int col = p.getColor();//0
		boolean validMove = false;
		
		//Piece in target location
		Piece p2 = getPiece(toX, toY);//initially it returns null

		//Try if the move is valid (king is not in check)
		p.setX(toX);
		p.setY(toY);
		squares[xx][yy] = -1;
		squares[toX][toY] = col;
		
		if(col == 0) {
			refreshBlackControl(p2);//p2=null
			if(bControl[whiteKing.getX()][whiteKing.getY()] == 0) {
				validMove = true;
			}
		} else {
			refreshWhiteControl(p2);
			if(wControl[blackKing.getX()][blackKing.getY()] == 0) {
				validMove = true;
			}
		}
		
		p.setX(xx);
		p.setY(yy);
		squares[xx][yy] = col;
		if(p2 != null) {
			squares[toX][toY] = p2.getColor();
		} else {
			squares[toX][toY] = -1;
		}		
		
		if(validMove) {
			moves[moveIndex] = xx*1000 + yy*100 + toX*10 + toY*1;
			moveIndex++;
		}
	}
	
	public void submitNinjaMove(Piece p, int toX, int toY)
	{
		int xx = p.getX();
		int yy = p.getY();
		moves[moveIndex] = xx*1000 + yy*100 + toX*10 + toY*1;
		moveIndex++;
	}

	public void submitEmergencyMove(Piece p, int toX, int toY)
	{
		int xx = p.getX();
		int yy = p.getY();
		moves[moveIndex] = xx*1000 + yy*100 + toX*10 + toY*1;
		moveIndex++;
	}
	
	public void refreshBlackControl(Piece excludeThis) //p2
	{
		int i, j;
		
		for(i=0; i<8; i++) {
			for(j=0; j<8; j++) {
				bControl[i][j] = 0;
			}
		}
		for(Piece p : pieces) {
			if(p.getColor() == 1 && p != excludeThis) {
				p.getControl();
			}
		}		
	}
	
	public void refreshWhiteControl(Piece excludeThis) {
		int i, j;
		
		for(i=0; i<8; i++) {
			for(j=0; j<8; j++) {
				wControl[i][j] = 0;
			}
		}
		for(Piece p : pieces) {
			if(p.getColor() == 0 && p != excludeThis) {
				p.getControl();
			}
		}		
	}
	
	public int getSquare(int x, int y) {
		return squares[x][y];
	}
	
	public Piece getPiece(int x, int y) //5,4
	{
		for(Piece p : pieces) {
			if(p.getX() == x && p.getY() == y) {
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Piece> getPieces () {
		return pieces;
	}
	
	public int getWControl(int x, int y) {
		return wControl[x][y];
	}
	
	public int getBControl(int x, int y) {
		return bControl[x][y];
	}
	
	public void setBControl(int x, int y) //1,2
	{
		bControl[x][y] += 1;
	}
	
	public void setWControl(int x, int y) {
		wControl[x][y] += 1;
	}
	
	
	private void doMove(int x, int y, int toX, int toY) {

		Piece p, p2;
		
		p = getPiece(x, y);

		// if(p.getColor()==1)
		// {
		// 	try 
		// 	{
		// 		TimeUnit.SECONDS.sleep(1);
		// 	} 			
		// 	catch (InterruptedException e) 
		// 	{
		// 		e.printStackTrace();
		// 	}
		// }

		if(p != null) {
			p2 = getPiece(toX, toY);
			if(p2 != null && p2.getColor() != p.getColor()) {
				pieces.remove(p2);
			}
			
			//Move the piece
			p.setX(toX);
			p.setY(toY);
			squares[x][y] = -1;
			squares[toX][toY] = p.getColor();
			

			//Ninja Move
			if(p.getPieceCode()==5) //  p is queen
			{
				if(p2!=null)
				{
					if(p2.getPieceCode()==3 && p2.getColor()==p.getColor()) // p2 is knight 
					{
						p2.setX(x);
						p2.setY(y);
						squares[x][y] = p2.getColor();
					}
				}
			}

			//Emergency Move
			if(p.getPieceCode()==6) //  p is king
			{
				if(p2!=null)
				{
					if(p2.getPieceCode()==5 && p2.getColor()==p.getColor()) // p2 is knight 
					{
						p2.setX(x);
						p2.setY(y);
						squares[x][y] = p2.getColor();
					}
				}
			}
			
				//Pawn promote	
			if(p.getPieceCode() == 1) {
				if(toY == 7 || toY == 0) {
					if(playerTurn) {
						window.createPromotionWindow();
						board.drawPieces();
						promote = true;
						promoteX = toX;
						promoteY = toY;
						
						pieces.remove(p);						
					} else {
						pieces.add(new Queen(toX, toY, p.getColor(), this));
						pieces.remove(p);
					}
				}
			}
			
			
			if(promote == false) {

				if(whiteTurn) {
					whiteTurn = false;
				} else {
					whiteTurn = true;
				}
				
				board.drawPieces();
				calculateMoves();							
			}
		}
	}
	
	public void userInput(int x, int y) //box chosen
	{
		if(promote == false && playerTurn == true) {
			int newMove = userChose*100 + x*10 + y;
			int i;
			
			for(i = 0; i < moveIndex; i++) {
				if(newMove == moves[i]) {
					//doMove
					int pieceX = userChose/10;
					int pieceY = userChose%10;
					doMove(pieceX, pieceY, x, y);	
					
					i = moveIndex + 1;
				}
			}
			
			userChose = x*10 + y;
		}
	}
	
	
}
