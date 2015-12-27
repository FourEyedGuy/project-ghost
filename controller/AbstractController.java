package controller;

import modele.AbstractModel;

public abstract class AbstractController {
	/**
	 * Modèle
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
	 * la ligne sur laquelle est située la case selectionnée
	 */
	protected int pawnLine = -1;
	
	/**
	 * la colonne sur laquelle est située la case selectionnée
	 */
	protected int pawnColumn = -1;
	
	/**
	 * la ligne sur laquelle est située la case de destination
	 */
	protected int destLine = -1;
	
	/**
	 * la colonne sur laquelle est située la case de destination
	 */
	protected int destColumn = -1;
	
	/**
	 * constructeur prends en paramètre un modèle
	 * @param gameMngr modèle pour le jeu
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
	 * @param destLine la valeur à mettre
	 */
	public void setDestLine(int destLine) {
		this.destLine = destLine;
	}

	/**
	 * met la valeur de la colonne de la case de destination
	 * @param destColumn la valeur à mettre
	 */
	public void setDestColumn(int destColumn) {
		this.destColumn = destColumn;
	}
	
	/**
	 * instancie et contrôle les coordonnées concernant la case de destination
	 * @param line la ligne sur laquelle est situé la case de destination
	 * @param column la colonne sur laquelle est situé la case de destination
	 */
	public void setDestSquateAt(int destLine, int destColumn){
		setDestLine(destLine);
		setDestColumn(destColumn);
		control();
	}
	
	/**
	 * rafraîchir l'affichage après le standby
	 */
	public void standByUpdate(){
		gameMngr.notifyObserver();
	}
	
	/**
	 * Dire s'il y a un pion sur une case donnée
	 * @param line la ligne sur laquelle est située la case à analyser
	 * @param column la colonne sur laquelle est située la case à analyser
	 * @return vrai si il y a un pion sur la case indiquée
	 */
	public boolean thereIsPawnAt(int line, int column){
		return gameMngr.thereIsPawnAt(line, column);
	}
	
	/**
	 * Dire si le joueur courant a gagné
	 * @return vrai si le joueur courant a gagné
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
	 * controle si la case selectionnée est conforme aux règle du jeu
	 */
	public abstract void control();
	
	/**
	 * controle les placements initiaux des pions sur le plateau
	 */
	public abstract void initControl();
	
	/**
	 * controle le déplacement des pions
	 */
	public abstract void playControl();
	
	/**
	 * Dire si le jeu est fini
	 * @return vrai si le jeu est terminé
	 */
	public abstract boolean gameEnded();
}
