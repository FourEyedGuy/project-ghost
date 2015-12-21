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
	
	public abstract void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn, boolean whiteToPlay);
	
	public abstract void addGoodPawnForWhitePlayerAt(int line, int column, boolean winUp);
	
	public abstract void addBadPawnForWhitePlayerAt(int line, int column);
	
	public abstract void addGoodPawnForBlackPlayerAt(int line, int column, boolean winUp);

	public abstract void addBadPawnForBlackPlayerAt(int line, int column);
	
	public abstract boolean allWhiteGoodPawnsSet();
	
	public abstract boolean allWhiteBadPawnsSet();
	
	public abstract boolean allBlackGoodPawnsSet();
	
	public abstract boolean allBlackBadPawnsSet();

	
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
