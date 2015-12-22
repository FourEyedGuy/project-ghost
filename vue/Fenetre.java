package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constantes.Parameters;
import controller.AbstractController;
import modele.Pawn;
import modele.Player;
import observer.Observer;

public class Fenetre extends JFrame implements Observer{
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	
	private AbstractController controller;
	private GameBoard gameBoard;
	private JPanel upperPane;
	private JLabel upperText;
	private JButton validate;
	private JLabel downLabel;
	private boolean whiteToPlay = true;
	private boolean onStandBy = false;
	private boolean initPhase = true;
	
	private boolean squareSelected = false;
	private Square currentSelectedSquare = new Square("");
	
	public Fenetre(AbstractController controller){
		
		setTitle("Project ghost");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.controller = controller; 
		gameBoard = new GameBoard();
		//gameBoard.init();
		for(int i=0; i<(Parameters.BOARD_HEIGHT * Parameters.BOARD_WIDTH);i++)
			gameBoard.getSquareAt(i).addActionListener(new SquareListener());
		
		Font font = new Font("Comic sans MS", Font.BOLD, 20);
		upperText = new JLabel();
		upperText.setFont(font);
		upperText.setText("Placement initial : aux blancs de commencer");
		upperText.setHorizontalAlignment(SwingConstants.CENTER);
		validate = new JButton("OK");
		validate.addActionListener(new validateButtonListener());
		
		upperPane = new JPanel();
		upperPane.add(upperText);
		upperPane.add(validate);
		validate.setEnabled(false);
		
		downLabel = new JLabel();
		downLabel.setFont(font);
		//downLabel.setText("pièce adverse prise : 2 gentils, 3 méchants");
		setLayout(new BorderLayout());
		
		
		
		getContentPane().add(upperPane, BorderLayout.NORTH);
		getContentPane().add(gameBoard, BorderLayout.CENTER);
		getContentPane().add(downLabel, BorderLayout.SOUTH);
		
		setVisible(true);
		validate.setVisible(false);
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
			
			if(initPhase){
				controller.setSquareAt(currentLine, currentColumn);
			}else{
				if(!squareSelected && controller.thereIsPawnAt(currentLine, currentColumn)){
					controller.setSquareAt(currentLine, currentColumn);
					squareSelected = true;
					currentSelectedSquare = gameBoard.getSquareAt(currentLine, currentColumn);
					currentSelectedSquare.setBackground(Color.green);
				}
				else{
					controller.setDestSquateAt(currentLine, currentColumn);
					squareSelected = false;
					currentSelectedSquare.setBackground(Color.white);
					currentSelectedSquare = new Square("");
				}
			}
		}
	}
	
	class validateButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			setEnabledBoard(true);
			onStandBy = false;
			controller.standByUpdate();
			validate.setEnabled(false);
			validate.setVisible(false);
		}
	}

	@Override
	public void update(Player white, Player black, boolean whiteToPlay, boolean initPhase) {
		clearBoard();
		if(justSwitchedTurn(whiteToPlay)) onStandBy = true;
		if(!initPhase) updateDownLabel(white, black, whiteToPlay);
		
		if(onStandBy) {
			onStandBy();
			this.whiteToPlay = whiteToPlay;
			updatePlayer(white, false, "Blanc");
			updatePlayer(black, false, "Noir");
		}else{
			updateUpperLabel(whiteToPlay, initPhase);
			if(whiteToPlay){
				updatePlayer(white, true, "");
				updatePlayer(black, false, "Noir");
				
			}else{
				updatePlayer(black, true, "");
				updatePlayer(white, false, "Blanc");
			}
		}
		
		if(this.initPhase != initPhase) this.initPhase = initPhase;
	}
	
	private void updatePlayer(Player player, boolean reveal, String coverText){
		for(Pawn pawn:player.getAllPawns()){
			if(reveal){
				gameBoard.setSquareAt((pawn.isGood()? "gentil" : "mechant"), pawn.getLine(), pawn.getColumn());
			}
			else{
				gameBoard.setSquareAt(coverText, pawn.getLine(), pawn.getColumn());
			}
		}
	}
	
	private void updateUpperLabel(boolean whiteToPlay, boolean initPhase){
		upperText.setText((initPhase? "(placement init.) ":"") + "C'est aux " + (whiteToPlay? "blanc":"noir") + "s de jouer");
	}
	
	private void updateDownLabel(Player white, Player black, boolean whiteToPlay){
			downLabel.setText("Pions adverses pris : gentils " + (Parameters.NB_GOOD - (whiteToPlay? black:white).getGoodRemaning()) +
			", mechant " + (Parameters.NB_BAD - (whiteToPlay? black:white).getBadRemaning()));
	}
	
	private boolean justSwitchedTurn(boolean whiteToPlay){
		return this.whiteToPlay != whiteToPlay;
	}
	
	private void onStandBy(){
		setEnabledBoard(false);
		upperText.setText("Passez au joueur suivant");
		validate.setVisible(true);
		validate.setEnabled(true);
	}
	
	private void setEnabledBoard(boolean on){
		for(int i=0; i< Parameters.BOARD_HEIGHT * Parameters.BOARD_WIDTH; i++)
			gameBoard.getSquareAt(i).setEnabled(on);
	}
	
	private void clearBoard(){
		for(int i=0; i< Parameters.BOARD_HEIGHT * Parameters.BOARD_WIDTH; i++)
			gameBoard.getSquareAt(i).setText("");;
	}
}
