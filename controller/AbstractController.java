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
	 * change la valeur de la ligne de la case selectionnée
	 * @param line la nouvelle valeur de ligne à mettre
	 */
	protected void setLine(int line) {
		this.pawnLine = line;
	}

	/**
	 * change la valeur de la colonne de la case selectionnée
	 * @param column la nouvelle valeur de colonne à mettre
	 */
	protected void setColumn(int column) {
		this.pawnColumn = column;
	}
	
	/**
	 * instancie dans controller et contrôle les coordonnées concernant la case selectionnée
	 * @param line la ligne sur laquelle est située la case selectionnée
	 * @param column la colonne sur laquelle est située la case selectionnée
	 */
	public void setSquareAt(int line, int column){
		setLine(line);
		setColumn(column);
		control();
	}
	
	/**
	 * change la valeur de la ligne de la case de destination
	 * @param destLine la valeur a mettre
	 */
	protected void setDestLine(int destLine) {
		this.destLine = destLine;
	}

	/**
	 * met la valeur de la colonne de la case de destination
	 * @param destColumn la valeur a mettre
	 */
	protected void setDestColumn(int destColumn) {
		this.destColumn = destColumn;
	}
	
	/**
	 * instancie et contrôle les coordonnées concernant la case de destination
	 * @param destLine la ligne sur laquelle est située la case de destination
	 * @param destColumn la colonne sur laquelle est située la case de destination
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
	 * @return vrai si le joueur dont c'est le tour a gagné
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
	 * contrôle si la manipulation du joueur est conforme aux règles du jeu et met à jour le modèle en conséquence
	 */
	public abstract void control();
	
	/**
	 * contrôle les placements initiaux des pions sur le plateau
	 */
	public abstract void initControl();
	
	/**
	 * contrôle le déplacement des pions
	 */
	public abstract void playControl();
	
	/**
	 * Dire si le jeu est fini
	 * @return vrai si le jeu est terminé
	 */
	public abstract boolean gameEnded();
}
