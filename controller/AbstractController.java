package controller;

import modele.AbstractModel;

/**
 * Classe abstraite contrôleur : contrôle les cliques de souris (en particulier pour contrôler le bon positionnement des pions lors de la phase initiale, 
 * ainsi du bon déplacement des pions lors de la phase de jeu)
 * @author Edgar Liang, Huanghuang Li
 *
 */
public abstract class AbstractController {
	/**
	 * Modèle à mettre à jour selon les actions du joueur
	 */
	protected AbstractModel gameMngr;
	
	/**
	 * vrai si on est à la phase de placements initiaux
	 */
	protected boolean isInitPhase;
	
	/**
	 * vrai si c'est au joueur WHITE de jouer
	 */
	protected boolean whiteToPlay;

	/**
	 * la ligne sur laquelle est située la case à contrôler
	 */
	protected int pawnLine = -1;
	
	/**
	 * la colonne sur laquelle est située la case à contrôler
	 */
	protected int pawnColumn = -1;
	
	/**
	 * la ligne sur laquelle est située la case de destination à contrôler
	 */
	protected int destLine = -1;
	
	/**
	 * la colonne sur laquelle est située la case de destination à contrôler
	 */
	protected int destColumn = -1;
	
	/**
	 * constructeur 
	 * @param gameMngr modèle sur lequel faire les contrôles et faire les mises à jour
	 */
	public AbstractController(AbstractModel gameMngr){
		this.gameMngr = gameMngr;
		isInitPhase = gameMngr.isInitPhase();
		whiteToPlay = gameMngr.isWhiteToPlay();
	}
	
	/**
	 * met la valeur de la ligne de la case sélectionnée
	 * @param line la valeur à mettre
	 */
	public void setLine(int line) {
		this.pawnLine = line;
	}

	/**
	 * met la valeur de la colonne de la case sélectionnée
	 * @param column la valeur à mettre
	 */
	public void setColumn(int column) {
		this.pawnColumn = column;
	}
	
	/**
	 * instancie et contrôle les coordonnées concernant la case sélectionnée
	 * @param line la ligne sur laquelle est situé la case sélectionnée
	 * @param column la colonne sur laquelle est situé la case sélectionnée
	 */
	public void setSquareAt(int line, int column){
		setLine(line);
		setColumn(column);
		control();
	}
	
	/**
	 * met la valeur de la ligne de la case de destination
	 * @param destLine la valeur à  mettre
	 */
	public void setDestLine(int destLine) {
		this.destLine = destLine;
	}

	/**
	 * met la valeur de la colonne de la case de destination
	 * @param destColumn la valeur Ã  mettre
	 */
	public void setDestColumn(int destColumn) {
		this.destColumn = destColumn;
	}
	
	/**
	 * instancie et contrÃ´le les coordonnÃ©es concernant la case de destination
	 * @param line la ligne sur laquelle est situÃ© la case de destination
	 * @param column la colonne sur laquelle est situÃ© la case de destination
	 */
	public void setDestSquateAt(int destLine, int destColumn){
		setDestLine(destLine);
		setDestColumn(destColumn);
		control();
	}
	
	/**
	 * rafraÃ®chir l'affichage aprÃ¨s le standby
	 */
	public void standByUpdate(){
		gameMngr.notifyObserver();
	}
	
	/**
	 * Dire s'il y a un pion sur une case donnÃ©e
	 * @param line la ligne sur laquelle est situÃ©e la case Ã  analyser
	 * @param column la colonne sur laquelle est situÃ©e la case Ã  analyser
	 * @return vrai si il y a un pion sur la case indiquÃ©e
	 */
	public boolean thereIsPawnAt(int line, int column){
		return gameMngr.thereIsPawnAt(line, column);
	}
	
	/**
	 * Dire si le joueur courant a gagné
	 * @return vrai si le joueur dont c'est le tour  a gagné
	 */
	public boolean currentPlayerWin(){
		if(gameMngr.isWhiteToPlay()){
			if(gameMngr.whitePlayerWon()) return true;
			else if(gameMngr.blackPlayerWon()) return false;
			return false;
		}else{
			if(gameMngr.blackPlayerWon()) return true;
			else if(gameMngr.whitePlayerWon()) return false;
			return false;
		}
	}
	
	/**
	 * controle si la case selectionnée est conforme aux règles du jeu et met à jour le modèle en conséquence
	 */
	public abstract void control();
	
	/**
	 * controle les placements initiaux des pions sur le plateau
	 */
	public abstract void initControl();
	
	/**
	 * controle le dÃ©placement des pions
	 */
	public abstract void playControl();
	
	/**
	 * Dire si le jeu est fini
	 * @return vrai si le jeu est terminÃ©
	 */
	public abstract boolean gameEnded();
}
