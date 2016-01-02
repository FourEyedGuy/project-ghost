package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.AbstractController;
import modele.Pawn;
import modele.Player;
import observer.Observer;
import utils.Direction;
import utils.Parameters;

/**
 * la classe chargé de l'interface graphique (affichage de la fenêtre)
 * @author Edgar Liang, Li Huanghuang
 *
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Observer{
	
	/**
	 * largeur de la fenêtre 
	 */
	private static final int WIDTH = 700;
	
	/**
	 * longueur de la fenêtre
	 */
	private static final int HEIGHT = 700;
	
	/**
	 * contrôleur
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
	 * panneau d'affichage du bas(il affiche combien de pions ont été pris à l'adversaire et leurs types)
	 */
	private JLabel downLabel;
	
	/**
	 * si c'est au blanc de jouer
	 */
	private boolean whiteToPlay = true;
	
	/**
	 * si on est en attente de passage de tour à l'adversaire
	 */
	private boolean onStandBy = false;
	
	/**
	 * si on est à la phase de placements initiaux
	 */
	private boolean initPhase = true;
	
	/**
	 * si un joueur a selectionne une case
	 */
	private boolean aSquareIsSelected = false;
	
	/**
	 * la case courante selectionnee
	 */
	private Square currentSelectedSquare = new Square("");
	
	/**
	 * pour le pion fantôme courant sélectionné, contient l'ensemble des cases de déplacements possibles
	 */
	private ArrayList<Square> possibleMovesSquares = new ArrayList<Square>();
	
	/**
	 * si le jeu est en mode triche
	 */
	private boolean cheat = false;
	
	/**
	 * constructeur : instancie la fenetre
	 * @param controller controleur, contrôle les entrées (les cliques) sur les JButton
	 * @param cheat true pour afficher tous les pions en standBy, false pour cacher les pions adverses pendant le standBy
	 */
	public Fenetre(AbstractController controller, boolean cheat){
		this.cheat = cheat;
		
		//initialisation de la fenetre
		setTitle("Ghost !");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//initialisation du controleur interne et du plateau de jeu
		this.controller = controller; 
		gameBoard = new GameBoard();
		//gameBoard.setExits();
		gameBoard.addListeners(new SquareListener());
		
		//initialisation des polices d'affichage + panneau d'affichage du haut + initialisation du bouton ok
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
		
		//initialisation du panneau du bas
		downLabel = new JLabel();
		downLabel.setFont(font);
		
		//créer un BorderLayout
		setLayout(new BorderLayout());
		getContentPane().add(upperPane, BorderLayout.NORTH);
		getContentPane().add(gameBoard, BorderLayout.CENTER);
		getContentPane().add(downLabel, BorderLayout.SOUTH);
		
		setVisible(true);
		validate.setVisible(false);
	}
	
	/**
	 * ecouteur pour les cases du plateau
	 */
	class SquareListener implements ActionListener{
		Square selectedSquare = new Square("");
		int currentLine = 0;
		int currentColumn = 0;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//on recupere les coordonnees de la case cliquee
			selectedSquare =  (Square)e.getSource();
			currentLine = selectedSquare.getLine();
			currentColumn = selectedSquare.getColumn();
			
			//phase de placements initiaux
			if(initPhase){
				controller.setSquareAt(currentLine, currentColumn);
				
			//phase de jeu
			}else{
				//selection d'un pion joueur
				if(!aSquareIsSelected && controller.thereIsPawnAt(currentLine, currentColumn)){
					controller.setSquareAt(currentLine, currentColumn);
					aSquareIsSelected = true;
					currentSelectedSquare = gameBoard.getSquareAt(currentLine, currentColumn);
					highLightCurrentSquare(true);
					highLightPossibleMoves(true);
				}
				//deplacement du pion si mouvement valide
				else{
					controller.setDestSquateAt(currentLine, currentColumn);
					aSquareIsSelected = false;
					highLightCurrentSquare(false);
					highLightPossibleMoves(false);
					currentSelectedSquare = new Square("");
				}
			}
		}
		
		/**
		 * affiche le pion actuellement selectionné
		 * @param on active/desactive l'affichage
		 */
		private void highLightCurrentSquare(boolean on){
			if(on)
				currentSelectedSquare.setBackground(Color.cyan);
			else
				currentSelectedSquare.setBackground(Color.white);
		}
		
		/**
		 * affiche les coups possibles pour le pion selectionné
		 * @param on true pour révéler (affcher) les coups possibles, false pour ne pas révéler
		 */
		private void highLightPossibleMoves(boolean on){
			if(on){
				for (Direction dir : Direction.values()){
					int temp_line = currentLine + dir.getLine();
					int temp_col = currentColumn + dir.getColumn();
					
					if(
							temp_line >= 0 &&
							temp_line < Parameters.BOARD_HEIGHT &&
							temp_col >= 0 &&
							temp_col < Parameters.BOARD_WIDTH &&
							!controller.thereIsPawnAt(temp_line, temp_col)
					){
						
						gameBoard.getSquareAt(temp_line, temp_col).setBackground(Color.GREEN);
						possibleMovesSquares.add(gameBoard.getSquareAt(temp_line, temp_col));
					}
				}
			}
			else{
				for (Square square : possibleMovesSquares)
					square.setBackground(Color.white);
				
				possibleMovesSquares = new ArrayList<Square>();
			}
		}
	}
	
	/**
	 * écouteur pour le bouton "OK". le clique sur celui-ci le sort du standBy
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
		//on rafraichit l'ecran
		gameBoard.clear();
		
		if(justSwitchedTurn(whiteToPlay)) onStandBy = true;
		
		//controle des conditions de fin de partie
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
				//affiche l'état du jeu à la fin de la partie
				gameBoard.setEnabled(false);
				updatePlayer(white, true);
				updatePlayer(black, true);
				
				return ;
			}
		}
		
		if(onStandBy) {
			//mettre en attente et cacher la nature des fantomes
			putOnStandBy();
			updatePlayer(white, cheat);
			updatePlayer(black, cheat);
			this.whiteToPlay = whiteToPlay;
		}else{
			//afficher le plateau de jeu pour le joueur courant
			updateUpperLabel(whiteToPlay, initPhase);
			updatePlayer(white, whiteToPlay || cheat);
			updatePlayer(black, !whiteToPlay || cheat);
			updateExits(whiteToPlay);
		}
		
		if(this.initPhase != initPhase) this.initPhase = initPhase;
	}
	
	/**
	 * afficher l'ensemble des pions pour un joueur
	 * @param player le joueur pour qui on doit afficher les pions
	 * @param reveal true pour révéler la nature des fantomes, false sinon
	 */
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
	 * change le texte du bas (qui affiche les décomptes des pions adverses pris)
	 * @param white joueur blanc
	 * @param black joueur noir
	 * @param whiteToPlay si c'est au joueur blanc de jouer
	 */
	private void updateDownLabel(Player white, Player black, boolean whiteToPlay){
			downLabel.setText("Pions adverses pris : gentils " + (Parameters.NB_GOOD - (whiteToPlay? black:white).getGoodRemaning()) +
			", mechant " + (Parameters.NB_BAD - (whiteToPlay? black:white).getBadRemaning()));
	}
	
	private void updateExits(boolean whiteToPlay){
		Color exitsColor = Color.yellow;
		if(whiteToPlay){
			gameBoard.getSquareAt(0, 0).setBackground(exitsColor);
			gameBoard.getSquareAt(0, Parameters.BOARD_WIDTH-1).setBackground(exitsColor);
		}else{
			gameBoard.getSquareAt(Parameters.BOARD_HEIGHT - 1, 0).setBackground(exitsColor);
			gameBoard.getSquareAt(Parameters.BOARD_HEIGHT-1, Parameters.BOARD_WIDTH-1).setBackground(exitsColor);
		}
	}
	
	/**
	 * Dire si on vient de passer a l'autre joueur (et mettre ainsi en pause)
	 * @param whiteToPlay la variable à comparer avec whiteToPlay courant
	 * @return vrai si on a changé de tour
	 */
	private boolean justSwitchedTurn(boolean whiteToPlay){
		return this.whiteToPlay != whiteToPlay;
	}
	
	/**
	 * met en pause le jeu (pour laisser le temps à un joueur de se retourner et à l'autre joueur de s'installer)
	 */
	private void putOnStandBy(){
		gameBoard.setEnabled(false);
		upperText.setText("Passez au joueur suivant");
		validate.setVisible(true);
		validate.setEnabled(true);
	}
}
