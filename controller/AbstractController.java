package controller;

import modele.AbstractModel;

/**
 * Classe abstraite contr�leur : contr�le les cliques de souris (en particulier pour contr�ler le bon positionnement des pions lors de la phase initiale, 
 * ainsi du bon d�placement des pions lors de la phase de jeu)
 * @author Edgar Liang, Huanghuang Li
 *
 */
public abstract class AbstractController {
	/**
	 * Mod�le � mettre � jour selon les actions du joueur
	 */
	protected AbstractModel gameMngr;
	
	/**
	 * vrai si on est � la phase de placements initiaux
	 */
	protected boolean isInitPhase;
	
	/**
	 * vrai si c'est au joueur WHITE de jouer
	 */
	protected boolean whiteToPlay;

	/**
	 * la ligne sur laquelle est situ�e la case � contr�ler
	 */
	protected int pawnLine = -1;
	
	/**
	 * la colonne sur laquelle est situ�e la case � contr�ler
	 */
	protected int pawnColumn = -1;
	
	/**
	 * la ligne sur laquelle est situ�e la case de destination � contr�ler
	 */
	protected int destLine = -1;
	
	/**
	 * la colonne sur laquelle est situ�e la case de destination � contr�ler
	 */
	protected int destColumn = -1;
	
	/**
	 * constructeur 
	 * @param gameMngr mod�le sur lequel faire les contr�les et faire les mises � jour
	 */
	public AbstractController(AbstractModel gameMngr){
		this.gameMngr = gameMngr;
		isInitPhase = gameMngr.isInitPhase();
		whiteToPlay = gameMngr.isWhiteToPlay();
	}
	
	/**
	 * met la valeur de la ligne de la case s�lectionn�e
	 * @param line la valeur � mettre
	 */
	public void setLine(int line) {
		this.pawnLine = line;
	}

	/**
	 * met la valeur de la colonne de la case s�lectionn�e
	 * @param column la valeur � mettre
	 */
	public void setColumn(int column) {
		this.pawnColumn = column;
	}
	
	/**
	 * instancie et contr�le les coordonn�es concernant la case s�lectionn�e
	 * @param line la ligne sur laquelle est situ� la case s�lectionn�e
	 * @param column la colonne sur laquelle est situ� la case s�lectionn�e
	 */
	public void setSquareAt(int line, int column){
		setLine(line);
		setColumn(column);
		control();
	}
	
	/**
	 * met la valeur de la ligne de la case de destination
	 * @param destLine la valeur � mettre
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
	 * Dire si le joueur courant a gagn�
	 * @return vrai si le joueur dont c'est le tour  a gagn�
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
	 * controle si la case selectionn�e est conforme aux r�gles du jeu et met � jour le mod�le en cons�quence
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
