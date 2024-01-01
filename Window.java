package chess;
import chess.pieces.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.*;

public class Window extends JFrame { // JFrame is used to create a graphical ui
	
	private int width;
	private int height;
	
	private BoardGraphics board;
	private ChessCalculator chess;
	
	private static PromotionWindow pw;

	
	public Window(int w, int h, ChessCalculator chess) { // 640, 640, c
		
		this.chess = chess;
		chess.setWindow(this); // passing this window to chess calculator
		
		width = w + 10;
		height = h + 40;
		
		setTitle("ChessGame");
		setSize(width, height);
		setLocationRelativeTo(null); // window position as centre.
		setDefaultCloseOperation(EXIT_ON_CLOSE); // to exit when pressed close
		setBackground(Color.BLACK); // BG color of the window
				
		BoardGraphics board = new BoardGraphics(w, h, chess); // 640, 640, c
		chess.setBoard(board);
		
		Container contentPane = getContentPane();
		contentPane.add(board, BorderLayout.CENTER);
		contentPane.addMouseListener(new ClickListener(board));
				
		setVisible(true); // makes the window visible
	}
	
	public void createPromotionWindow() {
		pw = new PromotionWindow();
	}
	
	public void showGameEnd(int winner,int stale) {
		String s = new String("white");
		if(winner == 1) {
			s = "black";
		}
		if(stale==0)
		{
			JOptionPane.showMessageDialog(null, "Checkmate, " + s + " wins");
			chess.reset();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Stalemate, Draw");
			chess.reset();
		}
	}
	
	private class ButtonListener implements ActionListener {
		public ButtonListener() {}
		
		public void actionPerformed (ActionEvent e) {
			
			String event = e.getActionCommand();
			if(event.equals("Knight")) {
				pw.destroy();
				pw = null;
				chess.piecePromote(3);
			}
			if(event.equals("Bishop")) {
				pw.destroy();
				pw = null;
				chess.piecePromote(4);
			}
			if(event.equals("Rook")) {
				pw.destroy();
				pw = null;
				chess.piecePromote(2);
			}
			if(event.equals("Queen")) {
				pw.destroy();
				pw = null;
				chess.piecePromote(5);
			}
		
		}
	}
	
	private class ClickListener extends MouseAdapter {
		
		private BoardGraphics board;
		
		public ClickListener(BoardGraphics b) {
			this.board = b;
		}
		
		public void mouseClicked(MouseEvent e)
		{
			if(pw == null) {
				int x = e.getX();
				int y = e.getY();
			
				board.drawChose(x, y);//x-(0,640),y-(0,640)	
			}		
		}
	}
	
	private class PromotionWindow extends JFrame {
		
		private JPanel window;
		private JPanel buttons;
		private JLabel message;
		private JButton knight;
		private JButton bishop;
		private JButton rook;
		private JButton queen;
		
		public PromotionWindow() {
			
			setTitle("Promote pawn to a new piece");
			setSize(400, 300);
			setLocation(300, 300);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			
			message = new JLabel("Choose a new piece");
			
			knight = new JButton("Knight");
			bishop = new JButton("Bishop");
			rook = new JButton("Rook");
			queen = new JButton("Queen");
			
			knight.setPreferredSize(new Dimension(40, 15));
			bishop.setPreferredSize(new Dimension(40, 15));
			rook.setPreferredSize(new Dimension(40, 15));
			queen.setPreferredSize(new Dimension(40, 15));
			
			knight.addActionListener( new ButtonListener());
			bishop.addActionListener( new ButtonListener());
			rook.addActionListener( new ButtonListener());
			queen.addActionListener( new ButtonListener());		
			
			buttons = new JPanel();
		
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
			buttons.add(knight);
			buttons.add(bishop);
			buttons.add(rook);
			buttons.add(queen);
			
			window = new JPanel(new BorderLayout(0, 10));			
			window.add(message, BorderLayout.PAGE_START);			
			window.add(buttons, BorderLayout.CENTER);
			
			add(window);
			
			setVisible(true);
		}
		
		public void destroy() {
			setVisible(false);
			dispose();
		}
	}
	
}
