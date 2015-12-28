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

import controller.AbstractController;
import modele.Pawn;
import modele.Player;
import observer.Observer;
import utils.Parameters;

/**
 * la classe charg� de l'interface graphique (affichage de la fen�tre)
 * @author Edgar Liang, Li Huanghuang
 *
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Observer{
	
	/**
	 * largeur de la fen�tre 
	 */
	private static final int WIDTH = 700;
	
	/**
	 * longueur de la fen�tre
	 */
	private static final int HEIGHT = 700;
	
	/**
	 * contr�leur
	 */
	private AbstractController controller;
	
	/**
	 * plateau de jeu 
	 */
	private GameBoard gameBoard;
	
	/**
	 * panneau d'affichage du haut
	 */
	private JPanel upperPane;
	
	/**
	 * le texte dans le panneau du haut 
	 */
	private JLabel upperText;
	
	/**
	 * le bouton de validation ("OK")
	 */
	private JButton validate;
	
	/**
	 * panneau d'affichage du bas(il affiche combien de pions ont �t� pris � l'adversaire et leurs types)
	 */
	private JLabel downLabel;
	
	/**
	 * si c'est au blanc de jouer
	 */
	private boolean whiteToPlay = true;
	
	/**
	 * si on est en attente de passage de tour � l'adversaire
	 */
	private boolean onStandBy = false;
	
	/**
	 * si on est � la phase de placements initiaux
	 */
	private boolean initPhase = true;
	
	/**
	 * si un joueur a s�lectionn� une case
	 */
	private boolean aSquareIsSelected = false;
	
	/**
	 * la case courante selectionnee
	 */
	private Square currentSelectedSquare = new Square("");
	
	/**
	 * si le jeu est en mode triche
	 */
	private boolean cheat = false;
	
	/**
	 * constructeur : instancie la fenetre
	 * @param controller controleur, contr�le les entr�es (les cliques) sur les JButton
	 * @param cheat true pour afficher tous les pions en standBy, false pour cacher les pions adverses pendant le standBy
	 */
	public Fenetre(AbstractController controller, boolean cheat){
		this.cheat = cheat;
		
		setTitle("Ghost !");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.controller = controller; 
		gameBoard = new GameBoard();
		gameBoard.setExits();
		gameBoard.addListeners(new SquareListener());
		
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
		
		setLayout(new BorderLayout());
		getContentPane().add(upperPane, BorderLayout.NORTH);
		getContentPane().add(gameBoard, BorderLayout.CENTER);
		getContentPane().add(downLabel, BorderLayout.SOUTH);
		
		setVisible(true);
		validate.setVisible(false);
	}
	
	/**
	 * �couteur pour les cases du plateau
	 */
	class SquareListener implements ActionListener{
		Square selectedSquare = new Square("");
		int currentLine = 0;
		int currentColumn = 0;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedSquare =  (Square)e.getSource();
			currentLine = selectedSquare.getLine();
			currentColumn = selectedSquare.getColumn();
			
			//phase de placements initiaaux
			if(initPhase){
				controller.setSquareAt(currentLine, currentColumn);
				
			//phase de jeu
			}else{
				//selection d'un pion joueur
				if(!aSquareIsSelected && controller.thereIsPawnAt(currentLine, currentColumn)){
					controller.setSquareAt(currentLine, currentColumn);
					aSquareIsSelected = true;
					currentSelectedSquare = gameBoard.getSquareAt(currentLine, currentColumn);
					currentSelectedSquare.setBackground(Color.green);
				}
				//d�placement du pion si mouvement valide
				else{
					controller.setDestSquateAt(currentLine, currentColumn);
					aSquareIsSelected = false;
					currentSelectedSquare.setBackground(Color.white);
					currentSelectedSquare = new Square("");
				}
			}
		}
	}
	
	/**
	 * �couteur pour le bouton "OK"
	 * @author Li Huanghuang Liang Edgar
	 *
	 */
	class validateButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gameBoard.setEnabled(true);
			onStandBy = false;
			controller.standByUpdate();
			validate.setEnabled(false);
			validate.setVisible(false);
		}
	}

	@Override
	public void update(Player white, Player black, boolean whiteToPlay, boolean initPhase) {
		gameBoard.clear();
		if(justSwitchedTurn(whiteToPlay)) onStandBy = true;
		
		if(!initPhase) {
			updateDownLabel(white, black, whiteToPlay);
			
			if(controller.gameEnded()){
				if(controller.currentPlayerWin()){
					upperText.setText("Felicitation " + (whiteToPlay? white.getName():black.getName()) + " !");
					downLabel.setText("Vous avez gagne ! :)");
				}
				else{
					upperText.setText("Desole " + (whiteToPlay? white.getName():black.getName()) + " ...");
					downLabel.setText("Vous avez perdu ! :(");
				}
				gameBoard.setEnabled(false);
				updatePlayer(white, true);
				updatePlayer(black, true);
				
				return ;
			}
		}
		
		if(onStandBy) {
			putOnStandBy();
			updatePlayer(white, cheat);
			updatePlayer(black, cheat);
			this.whiteToPlay = whiteToPlay;
		}else{
			updateUpperLabel(whiteToPlay, initPhase);
			updatePlayer(white, whiteToPlay || cheat);
			updatePlayer(black, !whiteToPlay || cheat);
		}
		
		if(this.initPhase != initPhase) this.initPhase = initPhase;
	}
	
	private void updatePlayer(Player player, boolean reveal){
		String sideName = player.getName();
		
		for(Pawn pawn:player.getAllPawns()){
			if(reveal){
				gameBoard.setSquareAt((pawn.isGood()? "gentil" : "mechant") + " " + sideName.charAt(0), pawn.getLine(), pawn.getColumn());
			}
			else{
				gameBoard.setSquareAt(sideName, pawn.getLine(), pawn.getColumn());
			}
		}
	}
	
	/**
	 * change le texte du haut (qui affiche le tour de jeu)
	 * @param whiteToPlay si c'est au joueur blanc de jouer
	 * @param initPhase si on est dans la phase de placements initiaux ou pas
	 */
	private void updateUpperLabel(boolean whiteToPlay, boolean initPhase){
		upperText.setText((initPhase? "(placement init.) ":"") + "C'est aux " + (whiteToPlay? "blanc":"noir") + "s de jouer");
	}
	
	/**
	 * change le texte du bas (qui affiche les d�comptes des pions adverses pris)
	 * @param white joueur blanc
	 * @param black joueur noir
	 * @param whiteToPlay si c'est au joueur blanc de jouer
	 */
	private void updateDownLabel(Player white, Player black, boolean whiteToPlay){
			downLabel.setText("Pions adverses pris : gentils " + (Parameters.NB_GOOD - (whiteToPlay? black:white).getGoodRemaning()) +
			", mechant " + (Parameters.NB_BAD - (whiteToPlay? black:white).getBadRemaning()));
	}
	
	/**
	 * Dire si on vient de passer � l'autre joueur (et mettre ainsi en pause)
	 * @param whiteToPlay la variable � comparer avec whiteToPlay courant
	 * @return vrai si on a chang� de tour
	 */
	private boolean justSwitchedTurn(boolean whiteToPlay){
		return this.whiteToPlay != whiteToPlay;
	}
	
	/**
	 * met en pause le jeu (pour laisser le temps � un joueur de se retourner et � l'autre joueur de s'installer)
	 */
	private void putOnStandBy(){
		gameBoard.setEnabled(false);
		upperText.setText("Passez au joueur suivant");
		validate.setVisible(true);
		validate.setEnabled(true);
	}
}
