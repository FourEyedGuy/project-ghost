package modele;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

/**
 * Classe abstraite mod�le : le mod�le est charg� de g�rer l'�tat du jeu 
 * @author Edgar Liang, HuangHuang Li
 *
 */
public abstract class AbstractModel implements Observable {
	
	/**
	 * le joueur blanc (celui qui place ses pions en bas � la phase initiale)
	 */
	protected Player white;
	
	/**
	 * le joueur noir (celui qui place ses pions en haut � la phase initiale)
	 */
	protected Player black;
	
	/**
	 * la liste des observateurs
	 */
	private ArrayList<Observer> observersList = new ArrayList<Observer>();
	
	/**
	 * true si c'est au joueur blanc de jouer (premier joueur par d�faut)
	 */
	protected boolean whiteToPlay = true;
	
	/**
	 * true si on est � la phase d'initialisation (placement initial des pions)
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
	 * revoie le joueur blanc (le joueur du bas du plateau et premier joueur)
	 * @return le joueur blanc
	 */
	public Player getWhite() {
		return white;
	}

	/**
	 * renvoie le joueur noir (le joueur du haut du plateau et second joueur)
	 * @return le joueur noir
	 */
	public Player getBlack() {
		return black;
	}
	
	/**
	 * renvoie l'ardversaire du joueur dont c'est le tour
	 * @return le joueur adversaire du joueur courant
	 */
	public Player getOpposingPlayer(){
		if(isWhiteToPlay())
			return black;
		else
			return white;
	}
	
	/**
	 * renvoie le joueur dont c'est le tour
	 * @return le joueur courant
	 */
	public Player getCurrentPlayer(){
		if(isWhiteToPlay())
			return white;
		else
			return black;
	}
	
	/**
	 * Dire si c'est au joueur blanc de jouer
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
	 * Dire si on est à la phase de placements initiaux des pions
	 * @return vrai si on est à la phase de placement des pions
	 */
	public boolean isInitPhase() {
		return initPhase;
	}

	/**
	 * mettre l'attribut initPhase � la valeur demand�e
	 * @param initPhase valeur bool�enne � mettre
	 */
	public void setInitPhase(boolean initPhase) {
		this.initPhase = initPhase;
		notifyObserver();
	}
	/**
	 * d�placer le pion (pawn) aux coordon�es indiqu�es vers les coordonn�es de destination
	 * @param pawnLine ligne sur laquelle est le pion
	 * @param pawnColumn colonne sur laquelle est le pion
	 * @param destLine ligne de la case destination
	 * @param destColumn colonne de la case de destination
	 */
	public abstract void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn);
	
	/**
	 * Ajoute un pion fant�me gentil pour le joueur dont c'est le tour de jouer
	 * @param line la ligne sur laquelle doit �tre ajout� le pion gentil
	 * @param column la colonne sur laquelle doit �tre ajout� le pion gentil
	 */
	public abstract void addCurrentPlayerGoodPwnAt(int line, int column);
	
	/**
	 * Ajoute un pion fant�me m�chant spour le joueur dont c'est le tour
	 * @param line la ligne sur laquelle doit �tre ajout� le pion m�chant
	 * @param column la colonne sur laquelle doit �tre ajout� le pion m�chant
	 */
	public abstract void addCurrentPlayerBadPwnAt(int line, int column);
	public abstract boolean allCurrentPlayerGoodPwnSet();
	public abstract boolean allCurrentPlayerBadPwnSet();
	
	/**
	 * Dire s'il y a un pion � la case indiqu�e
	 * @param line la ligne de la case � analyser
	 * @param column la colonne de la case � analyser
	 * @return vrai s'il y a un pion (adverse ou pas)
	 */
	public abstract boolean thereIsPawnAt(int line, int column);
	
	/**
	 * Dire s'il y a un pion de l'adversaire � la case indiqu�e
	 * @param line la ligne de la case �analyser
	 * @param column la colonne de la case � analyser
	 * @return vrai s'il y a un pion adverse
	 */
	public abstract boolean thereIsOpponentPawnAt(int line, int column);
	
	/**
	 * enl�ve de la case indiqu�e le pion adverse
	 * @param line la ligne sur laquelle est situ�e le pion adverse
	 * @param column la colonne sur laquelle est situ�e le pion adverse
	 */
	public abstract void removeOpponentPawnAt(int line, int column);
	
	/**
	 * Dire si le joueur blanc a gagn�
	 * @return true si Blanc a gagn�
	 */
	public abstract boolean whitePlayerWon();
	
	/**
	 * Dire si le joueur noir a gagn�
	 * @return true si Noir a gagn�
	 */
	public abstract boolean blackPlayerWon();

	/**
	 * Dire si le jeu est fini, si l'une des conditions de fin de jeu est r�alis�e :
	 * - Un joueur a captur� tout les bon fant�mes de son adversaire
	 * - Un joueur a captur� tout les mauvais fant�me de son adversaire
	 * - Un joueur a r�ussi � faire sortir tout ses bon fant�mes
	 * 
	 * @return true si le jeu est termin�
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
