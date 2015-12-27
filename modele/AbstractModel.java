package modele;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

/**
 * Classe abstraite gÃ©rant le modÃ¨le 
 * @author Edgar Liang, HuangHuang Li
 *
 */
public abstract class AbstractModel implements Observable {
	
	/**
	 * le joueur blanc (celui qui place ses pions en bas Ã  la phase initiale)
	 */
	protected Player white;
	
	/**
	 * le joueur noir (celui qui place ses pions en haut Ã  la phase initiale)
	 */
	protected Player black;
	
	/**
	 * la liste des observateurs
	 */
	private ArrayList<Observer> observersList = new ArrayList<Observer>();
	
	/**
	 * true si c'est au joueur blanc de jouer (premier joueur par dï¿½faut)
	 */
	protected boolean whiteToPlay = true;
	
	/**
	 * true si on est ï¿½ la phase d'initialisation (placement initial des pions)
	 */
	protected boolean initPhase = true;
	
	/**
	 * constructeur
	 */
	public AbstractModel() {
		white = new Player("Blanc");
		black = new Player("Noir");
	}
	
	/**
	 * constructeur
	 * @param nameW le nom du joueur blanc
	 * @param nameB le nom du joueur noir
	 */
	public AbstractModel(String nameW, String nameB){
		white = new Player(nameW);
		black = new Player(nameB);
	}
	
	/**
	 * 
	 * @return le joueur blanc
	 */
	public Player getWhite() {
		return white;
	}

	/**
	 * 
	 * @return le joueur noir
	 */
	public Player getBlack() {
		return black;
	}
	
	public Player getOpposingPlayer(){
		if(isWhiteToPlay())
			return black;
		else
			return white;
	}
	
	public Player getCurrentPlayer(){
		if(isWhiteToPlay())
			return white;
		else
			return black;
	}
	
	/**
	 * 
	 * @return true si c'est au joueur blanc de joueur
	 */
	public boolean isWhiteToPlay(){
		return whiteToPlay;
	}
	
	/**
	 * changer de tour (passer la main au joueur adverse)
	 */
	public abstract void switchTurn();
	
	/**
	 * Dire si on est Ã  la phase de placements initiaux des pions
	 * @return vrai si on est Ã  la phase de placement des pions
	 */
	public boolean isInitPhase() {
		return initPhase;
	}

	/**
	 * mettre l'attribut initPhase à  la valeur demandée
	 * @param initPhase valeur booléenne à  mettre
	 */
	public void setInitPhase(boolean initPhase) {
		this.initPhase = initPhase;
		notifyObserver();
	}
	/**
	 * dÃ©placer le pion (pawn) aux coordonÃ©es indiquÃ©es vers les coordonnÃ©es de destination
	 * @param pawnLine ligne sur laquelle est le pion
	 * @param pawnColumn colonne sur laquelle est le pion
	 * @param destLine ligne de la case destination
	 * @param destColumn colonne de la case de destination
	 */
	public abstract void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn);
	
	/**
	 * Ajoute un pion fantôme gentil pour le joueur dont c'est le tour de jouer
	 * @param line la ligne sur laquelle doit être ajouté le pion gentil
	 * @param column la colonne sur laquelle doit être ajouté le pion gentil
	 */
	public abstract void addCurrentPlayerGoodPwnAt(int line, int column);
	
	/**
	 * Ajoute un pion fantôme méchant spour le joueur dont c'est le tour
	 * @param line la ligne sur laquelle doit être ajouté le pion méchant
	 * @param column la colonne sur laquelle doit être ajouté le pion méchant
	 */
	public abstract void addCurrentPlayerBadPwnAt(int line, int column);
	public abstract boolean allCurrentPlayerGoodPwnSet();
	public abstract boolean allCurrentPlayerBadPwnSet();
	
	/**
	 * Dire s'il y a un pion à  la case indiquée
	 * @param line la ligne de la case à  analyser
	 * @param column la colonne de la case à  analyser
	 * @return vrai s'il y a un pion (adverse ou pas)
	 */
	public abstract boolean thereIsPawnAt(int line, int column);
	
	/**
	 * Dire s'il y a un pion de l'adversaire à  la case indiquée
	 * @param line la ligne de la case à analyser
	 * @param column la colonne de la case à analyser
	 * @return vrai s'il y a un pion adverse
	 */
	public abstract boolean thereIsOpponentPawnAt(int line, int column);
	
	/**
	 * enlève de la case indiquée le pion adverse
	 * @param line la ligne sur laquelle est située le pion adverse
	 * @param column la colonne sur laquelle est située le pion adverse
	 */
	public abstract void removeOpponentPawnAt(int line, int column);
	
	public abstract boolean whitePlayerWon();
	public abstract boolean blackPlayerWon();

	/**
	 * Dire si le jeu est fini, si les conditions de fin de jeu est réalisé :
	 * - Un joueur a capturé tout les bon fantômes de son adversaire
	 * - Un joueur a capturé tout les mauvais fantôme de son adversaire
	 * - Un joueur a reussi à faire sortir tout ses bon fantomes
	 * 
	 * @return true si le jeu est terminé
	 */
	public boolean gameEnded(){
		return whitePlayerWon() || blackPlayerWon();
	}
	
	@Override
	public void addObserver(Observer obs) {
		observersList.add(obs);
	}

	@Override
	public void removeObserver() {
		observersList = new ArrayList<Observer>();
	}
	
    @Override
	public void notifyObserver(){
    	for(Observer obs : observersList){
    		obs.update(white, black, whiteToPlay, initPhase);
    	}
    }

}
