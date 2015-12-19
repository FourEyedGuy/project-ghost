package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import constantes.Direction;
import constantes.Parameters;
import modele.GameManager;

public class Fenetre extends JFrame{
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	
	private GameManager gameManager;
	private GameBoard gameBoard;
	private JLabel upperLabel;
	private JLabel downLabel;
	private JLabel errorMsg;
	
	private boolean update;
	private boolean squareSelected = false;
	private Square currentSelectedSquare = new Square("");
	
	public Fenetre(GameManager gameManager){
		
		setTitle("Project ghost");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.gameManager = gameManager; 
		gameBoard = new GameBoard();
		//gameBoard.init();
		for(int i=0; i<(Parameters.BOARD_HEIGHT * Parameters.BOARD_WIDTH);i++)
			gameBoard.getSquareAt(i).addActionListener(new SquareListener());
		
		//gameBoard.getSquareAt(3, 3).setText("cool");
		
		Font font = new Font("Comic sans MS", Font.BOLD, 20);
		upperLabel = new JLabel();
		upperLabel.setFont(font);
		upperLabel.setText(whoseTurnToPlay());
		upperLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		downLabel = new JLabel();
		downLabel.setFont(font);
		downLabel.setText("pièce adverse prise : 2 gentils, 3 méchants");
		setLayout(new BorderLayout());
		
		errorMsg = new JLabel("");
		errorMsg.setFont(font);
		
		
		getContentPane().add(upperLabel, BorderLayout.NORTH);
		getContentPane().add(gameBoard, BorderLayout.CENTER);
		getContentPane().add(downLabel, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	
	private String whoseTurnToPlay(){
		return "C'est au tour de " + (gameManager.isWhiteToPlay()? gameManager.getWhite().getName() : gameManager.getBlack().getName()) + " de jouer";
	}
	
	private void updateUpperLabel(){
		upperLabel.setText(whoseTurnToPlay());
	}
	
	class SquareListener implements ActionListener{
		Square selectedSquare = new Square("");
		int currentLine = 0;
		int currentColumn = 0;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedSquare =  (Square)e.getSource();
			currentLine = selectedSquare.getLine();
			currentColumn = selectedSquare.getColumn();
			
			if(gameManager.isInitPhase()){
				init();
			}
			
			/*
			if(!squareSelected){
				if(selectedSquare.getText().equals("cool")){
					selectedSquare.setBackground(Color.green);
					currentSelectedSquare = selectedSquare;
					squareSelected = true;
				}
			}
			else{
				if(currentSelectedSquare.equals(selectedSquare)){
					selectedSquare.setBackground(Color.white);
					squareSelected = false;
				}
			}
			*/
			
			/*
			if(!currentSquare.getText().equals("cool") && count < 4){
				currentSquare.setText("cool");
				count++;
				
				if(count >= 4){
					count = 0;
					gameManager.switchTurn();
					updateUpperLabel();
				}
			}
			*/
			
			
			
			//System.out.println("Case courante : " + currentLine + "," + currentColumn);
			System.out.println(gameManager.toString());
		}
		
		private void init(){
			int whiteGoodToBeSet = Parameters.NB_GOOD;
			int whiteBadToBeSet = Parameters.NB_BAD;
			
			int blackGoodToBeSet = Parameters.NB_GOOD;
			int blackBadToBeSet = Parameters.NB_BAD;
			
			placePawnAt(currentLine, currentColumn);
		}
		
		private void placePawnAt(int line, int column){
			if(gameManager.isWhiteToPlay()){
				if(isAValidInitSquare(selectedSquare)){
					if(gameManager.getWhite().addGoodPawnAt(line, column, true)){
						gameBoard.getSquareAt(line, column).setText("Gentil");
					}
				}
				else{
					
				}
			}
		}
		
		private boolean isAValidInitSquare(Square square){
			if(gameManager.isWhiteToPlay()){
				if(square.getLine()>=Parameters.BOARD_HEIGHT-2 && square.getLine()<Parameters.BOARD_HEIGHT){
					if(square.getColumn()>0 && square.getColumn()<Parameters.BOARD_WIDTH-1)
						return true;
				}
			}
			else{
				if(square.getLine()>=0 && square.getLine()<2){
					if(square.getColumn()>0 && square.getColumn()<Parameters.BOARD_WIDTH-1)
						return true;
				}
			}
			return false;
		}
		
		private void moveSquare(Square square){
			Square nextSquare = gameBoard.getSquareAt(square.getLine()-1, square.getColumn());
			String str = nextSquare.getText();
			
			nextSquare.setText(square.getText());
			square.setText(str);
		}
	}
	
}
