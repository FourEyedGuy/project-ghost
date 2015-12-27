package modele;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

/**
 * Classe abstraite gérant le modèle 
 * @author Edgar Liang, HuangHuang Li
 *
 */
public abstract class AbstractModel implements Observable {
	
	/**
	 * le joueur blanc (celui qui place ses pions en bas à la phase initiale)
	 */
	protected Player white;
	
	/**
	 * le joueur noir (celui qui place ses pions en haut à la phase initiale)
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
	 * Dire si on est à la phase de placements initiaux des pions
	 * @return vrai si on est à la phase de placement des pions
	 */
	public boolean isInitPhase() {
		return initPhase;
	}

	/**
	 * mettre initPhase à la valeur demandée
	 * @param initPhase valeur booléenne à mettre
	 */
	public void setInitPhase(boolean initPhase) {
		this.initPhase = initPhase;
		notifyObserver();
	}
	/**
	 * déplacer le pion (pawn) aux coordonées indiquées vers les coordonnées de destination
	 * @param pawnLine ligne sur laquelle est le pion
	 * @param pawnColumn colonne sur laquelle est le pion
	 * @param destLine ligne de la case destination
	 * @param destColumn colonne de la case de destination
	 */
	public abstract void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn);
	
	public abstract void addGoodPawnForWhitePlayerAt(int line, int column, boolean winUp);
	
	public abstract void addBadPawnForWhitePlayerAt(int line, int column);
	
	public abstract void addGoodPawnForBlackPlayerAt(int line, int column, boolean winUp);

	public abstract void addBadPawnForBlackPlayerAt(int line, int column);
	
	public abstract boolean allWhiteGoodPawnsSet();
	
	public abstract boolean allWhiteBadPawnsSet();
	
	public abstract boolean allBlackGoodPawnsSet();
	
	public abstract boolean allBlackBadPawnsSet();
	
	/**
	 * Dire s'il y a un pion à la case indiquée
	 * @param line la ligne de la case à analyser
	 * @param column la colonne de la case à analyser
	 * @return vrai s'il y a un pion (adverse ou pas)
	 */
	public abstract boolean thereIsPawnAt(int line, int column);
	
	/**
	 * Dire s'il y a un pion de l'adversaire à la case indiquée
	 * @param line la ligne de la case à analyser
	 * @param column la colonne de la case à analyser
	 * @return vrai s'il y a un pion adverse
	 */
	public abstract boolean thereIsOpponentPawnAt(int line, int column);
	
	/**
	 * enlève de la case indiquée le pion adverse
	 * @param line la ligne sur laquelle est situé le pion adverse
	 * @param column la colonne sur laquelle est situé le pion adverse
	 */
	public abstract void removeOpponentPawnAt(int line, int column);
	
	public abstract boolean whitePlayerWon();
	
	public abstract boolean blackPlayerWon();

	/**
	 * Dire si le jeu est fini, si les conditions de fin de jeu est realise :
	 * - Un joueur a capture tout les bon fantomes de son adversaire
	 * - Un joueur a capture tout les mauvais fant�me de son adversaire
	 * - Un joueur a reussi � faire sortir tout ses bon fantomes
	 * 
	 * @return true si le jeu est termin�
	 */
	public boolean gameEnded(){
		return whitePlayerWon() || blackPlayerWon();
	}
	
	/**
	 * renvoi quel est le joueur gagnant :
	 * - Un joueur a captur� tout les bon fant�mes de son adversaire (il gagne)
	 * - Un joueur a captur� tout les mauvais fant�me de son adversaire (il perd)
	 * - Un joueur a r�ussi � faire sortir tout ses bon fant�mes (il gagne)
	 * 
	 * @return le nom du joueur gagnant
	 */
	public String winner(){
		if(whitePlayerWon()) return getWhite().getName();
		
		if(blackPlayerWon()) return getBlack().getName();
		
		return null;
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
