package modele;

/**
 * La classe qui est chargée du gérer l'état du jeu
 * @author Edgar Liang, Li Huanghuang
 *
 */
public class GameManager extends AbstractModel{
	/**
	 * Construteur
	 * 
	 * @param nameW le nom du joueur qui joue les pions blancs
	 * @param nameB le nom du joueur qui joue les pions noirs
	 */
	public GameManager(String nameW, String nameB){
		super(nameW, nameB);
	}
	
	/**
	 * Constructeur avec les noms par défauts ("Blanc" pour white et "Noir" pour black)
	 */
	public GameManager(){
		super();
	}
	
	@Override
	public void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn){
		if(whiteToPlay)
			white.movePawnAtTo(pawnLine, pawnColumn, destLine, destColumn);
		else
			black.movePawnAtTo(pawnLine, pawnColumn, destLine, destColumn);
		
		notifyObserver();
	}
	
	@Override
	public void switchTurn(){
		whiteToPlay = !whiteToPlay;
		notifyObserver();
	}
	
	/**
	 * Renvoie le String décrivant l'état du GameManager
	 */
	public String toString(){
		return white.toString() + black.toString() +
				"C'est au tour de " + (whiteToPlay? "blanc":"noir") + " de jouer\n" + 
				"Phase initialisation : " + initPhase + "\n" + 
				"Fin de jeu : " + gameEnded();
	}
	
	
	
	@Override
	public boolean thereIsPawnAt(int line, int column) {
		if(whiteToPlay)
			return white.thereIsPawnAt(line, column);
		else
			return black.thereIsPawnAt(line, column);
	}

	@Override
	public boolean thereIsOpponentPawnAt(int line, int column) {
		if(whiteToPlay)
			return black.thereIsPawnAt(line, column);
		else
			return white.thereIsPawnAt(line, column);
	}

	@Override
	public void removeOpponentPawnAt(int line, int column) {
		if(whiteToPlay)
			black.removePawnAt(line, column);
		else
			white.removePawnAt(line, column);
		
		notifyObserver();
	}

	@Override
	public boolean whitePlayerWon() {
		return getBlack().allGoodBeenCaptured() || 
				getWhite().allBadBeenCaptured() || 
				getWhite().aGoodHasExited();
	}
	
	@Override
	public boolean blackPlayerWon() {
		return getWhite().allGoodBeenCaptured() ||
				getBlack().allBadBeenCaptured() ||
				getBlack().aGoodHasExited();
	}

	@Override
	public void addCurrentPlayerGoodPwnAt(int line, int column) {
		getCurrentPlayer().addGoodPawnAt(line, column, isWhiteToPlay());
		notifyObserver();
	}

	@Override
	public void addCurrentPlayerBadPwnAt(int line, int column) {
		getCurrentPlayer().addBadPawnAt(line, column);
		notifyObserver();
	}

	@Override
	public boolean allCurrentPlayerGoodPwnSet() {
		return getCurrentPlayer().allGoodPawnsSet();
	}

	@Override
	public boolean allCurrentPlayerBadPwnSet() {
		return getCurrentPlayer().allBadPawnsSet();
	}
}
