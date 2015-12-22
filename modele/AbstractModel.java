package modele;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

public abstract class AbstractModel implements Observable {
	protected Player white;
	protected Player black;
	private ArrayList<Observer> observersList = new ArrayList<Observer>();
	
	/**
	 * true si c'est au joueur blanc de jouer (premier joueur par défaut)
	 */
	protected boolean whiteToPlay = true;
	
	/**
	 * true si on est à la phase d'initialisation (placement initial des pions)
	 */
	protected boolean initPhase = true;
	
	public AbstractModel() {
		white = new Player("Blanc");
		black = new Player("Noir");
	}
	
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
	
	public abstract void switchTurn();
	
	
	public boolean isInitPhase() {
		return initPhase;
	}

	public void setInitPhase(boolean initPhase) {
		this.initPhase = initPhase;
		notifyObserver();
	}
	
	public abstract void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn);
	
	public abstract void addGoodPawnForWhitePlayerAt(int line, int column, boolean winUp);
	
	public abstract void addBadPawnForWhitePlayerAt(int line, int column);
	
	public abstract void addGoodPawnForBlackPlayerAt(int line, int column, boolean winUp);

	public abstract void addBadPawnForBlackPlayerAt(int line, int column);
	
	public abstract boolean allWhiteGoodPawnsSet();
	
	public abstract boolean allWhiteBadPawnsSet();
	
	public abstract boolean allBlackGoodPawnsSet();
	
	public abstract boolean allBlackBadPawnsSet();
	
	public abstract boolean thereIsPawnAt(int line, int column);
	
	public abstract boolean thereIsOpponentPawnAt(int line, int column);
	
	public abstract void removeOpponentPawnAt(int line, int column);
	
	public abstract boolean whitePlayerWon();
	
	public abstract boolean blackPlayerWon();
	
	/**
	 * Dire si le jeu est fini, si les conditions de fin de jeu est realise :
	 * - Un joueur a capture tout les bon fantomes de son adversaire
	 * - Un joueur a capture tout les mauvais fantôme de son adversaire
	 * - Un joueur a reussi à faire sortir tout ses bon fantomes
	 * 
	 * @return true si le jeu est terminé
	 */
	public boolean gameEnded(){
		return whitePlayerWon() || blackPlayerWon();
	}
	
	/**
	 * renvoi quel est le joueur gagnant :
	 * - Un joueur a capturé tout les bon fantômes de son adversaire (il gagne)
	 * - Un joueur a capturé tout les mauvais fantôme de son adversaire (il perd)
	 * - Un joueur a réussi à faire sortir tout ses bon fantômes (il gagne)
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
