package chess;
import chess.pieces.*;

import java.awt.*;

import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BoardGraphics extends JPanel {
	
	//Width and height must be divisible by 16
	private int width;
	private int height;
	private int square;
	private int mouseX = 0;
	private int mouseY = 0;
	private int chosenX = 0;
	private int chosenY = 0;
	
	private int drawMode = 0;
	
	private ChessCalculator chess;
	
	public BoardGraphics(int w, int h, ChessCalculator chess) //640,640,chess
	{
		width = w;
		height = h;
		square = width/8;//80
		this.chess = chess;//Chess Calculator object
	}
	
	@Override
	protected void paintComponent(Graphics g) { 
	
		if(drawMode == 0) {
			
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
				
			g.setColor(Color.LIGHT_GRAY);
			int i, j;
			int color = 0;
			
			for(i = 0; i < width; i += square) {
				for(j = 0; j < height; j += square) {
					
					g.fillRect(i, j, square, square);
					
					if(color == 1) {
						g.setColor(Color.LIGHT_GRAY);
						color = 0;
					} else {
						g.setColor(Color.DARK_GRAY);
						color = 1;
					}
				}
				
				if(color == 1) {
					g.setColor(Color.LIGHT_GRAY);
					color = 0;
				} else {
					g.setColor(Color.DARK_GRAY);
					color = 1;
				}
			}
					
			ArrayList<Piece> pieces = chess.getPieces();
			if(pieces != null) {
				for(Piece p : pieces) {
					int pc = p.getPieceCode();
					int c = p.getColor();
					int x = p.getX();
					int y = p.getY();
					
					switch(pc) {
						case 1:
						drawPawn(g, x*square, y*square, c);
						break;
						
						case 2:
						drawRook(g, x*square, y*square, c);
						break;
						
						case 3:
						drawKnight(g, x*square, y*square, c);
						break;
						
						case 4:
						drawBishop(g, x*square, y*square, c);
						break;
						
						case 5:
						drawQueen(g, x*square, y*square, c);
						break;
						
						case 6:
						drawKing(g, x*square, y*square, c);
						break;
					}
				}
			}
			
		} else {
			
			//Remove previous rectangle
			if((chosenX/square)%2 == 0) {
				if((chosenY/square)%2 == 0) {
					g.setColor(Color.LIGHT_GRAY);
				} else {
					g.setColor(Color.DARK_GRAY);
				}
			} else {
				if((chosenY/square)%2 == 0) {
					g.setColor(Color.DARK_GRAY);
				} else {
					g.setColor(Color.LIGHT_GRAY);
				}
			}
			g.drawRect((chosenX/square)*square, (chosenY/square)*square, square, square);
			
			//New Rectangle
			g.setColor(Color.ORANGE);	
			g.drawRect((mouseX/square)*square, (mouseY/square)*square, square, square);
			chosenX = mouseX;
			chosenY = mouseY;
			drawMode = 0;
		}
	}

	public void drawChose(int x, int y) {
		
		drawMode = 1;
		mouseX = x;
		mouseY = y;
		
		int a, b;
		a = x/square;
		b = y/square;
		if(a >= 0 && a < 8 && b >= 0 && b < 8) {
			chess.userInput(a, b);
		}
		
		repaint();
	}
	
	public void drawPieces() {
		drawMode = 0;
		repaint();
	}
	
	private void drawPawn(Graphics g, int x, int y, int c) {
		Toolkit t=Toolkit.getDefaultToolkit();  
		try{
			File f1= new File("chess/imgs/WhitePawn.png");
			File f2= new File("chess/imgs/BlackPawn.png");
			if(!f1.exists() || !f2.exists()){
				throw new FileNotFoundException("Piece not found.");
			}
			if(c == 0)
			{
				Image i=t.getImage("chess/imgs/WhitePawn.png");  
				g.drawImage(i,x,y,80,80,this);  
			}
			else
			{
				Image i=t.getImage("chess/imgs/BlackPawn.png");  
				g.drawImage(i, x,y,80,80,this); 
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}
		
	}
	
	private void drawRook(Graphics g, int x, int y, int c) {
		Toolkit t=Toolkit.getDefaultToolkit();  
		try{
			File f1= new File("chess/imgs/WhiteRook.png");
			File f2= new File("chess/imgs/BlackRook.png");
			if(!f1.exists() || !f2.exists()){
				throw new FileNotFoundException("Piece not found.");
			}
			if(c == 0)
			{
				Image i=t.getImage("chess/imgs/WhiteRook.png");  
				g.drawImage(i,x,y,80,80,this);  
			}
			else
			{
				Image i=t.getImage("chess/imgs/BlackRook.png");  
				g.drawImage(i, x,y,80,80,this); 
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}
		
	}
	
	private void drawKnight(Graphics g, int x, int y, int c) {
		Toolkit t=Toolkit.getDefaultToolkit();  
		try{
			File f1= new File("chess/imgs/WhiteKnight.png");
			File f2= new File("chess/imgs/BlackKnight.png");
			if(!f1.exists() || !f2.exists()){
				throw new FileNotFoundException("Piece not found.");
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}
		if(c == 0)
		{
        	Image i=t.getImage("chess/imgs/WhiteKnight.png");  
        	g.drawImage(i,x,y,80,80,this);  
		}
		else
		{
			Image i=t.getImage("chess/imgs/BlackKnight.png");  
			g.drawImage(i, x,y,80,80,this); 
		}
	}
	
	private void drawBishop(Graphics g, int x, int y, int c) {
		Toolkit t=Toolkit.getDefaultToolkit(); 
		try{
			File f1= new File("chess/imgs/WhiteBishop.png");
			File f2= new File("chess/imgs/BlackBishop.png");
			if(!f1.exists() || !f2.exists()){
				throw new FileNotFoundException("Piece not found.");
			}
			if(c == 0)
			{
				Image i=t.getImage("chess/imgs/WhiteBishop.png");  
				g.drawImage(i,x,y,80,80,this);  
			}
			else
			{
				Image i=t.getImage("chess/imgs/BlackBishop.png");  
				g.drawImage(i, x,y,80,80,this); 
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		} 
	}
	
	private void drawQueen(Graphics g, int x, int y, int c) {
		Toolkit t=Toolkit.getDefaultToolkit();  
		try{
			File f1= new File("chess/imgs/WhiteQueen.png");
			File f2= new File("chess/imgs/BlackQueen.png");
			if(!f1.exists() || !f2.exists()){
				throw new FileNotFoundException("Piece not found.");
			}
			if(c == 0)
			{
				Image i=t.getImage("chess/imgs/WhiteQueen.png");  
				g.drawImage(i,x,y,80,80,this);  
			}
			else{
				Image i=t.getImage("chess/imgs/BlackQueen.png");  
				g.drawImage(i, x,y,80,80,this); 
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}
	}
	
	private void drawKing(Graphics g, int x, int y, int c) {
		Toolkit t=Toolkit.getDefaultToolkit();  
		try{
			File f1= new File("chess/imgs/WhiteKing.png");
			File f2= new File("chess/imgs/BlackKing.png");
			if(!f1.exists() || !f2.exists()){
				throw new FileNotFoundException("Piece not found.");
			}	
			if(c == 0)
			{
				Image i=t.getImage("chess/imgs/WhiteKing.png");  
				g.drawImage(i,x,y,80,80,this);  
			}
			else
			{
				Image i=t.getImage("chess/imgs/BlackKing.png");  
				g.drawImage(i, x,y,80,80,this); 
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File not found: " + e.getMessage());
		}
		
	}
}
