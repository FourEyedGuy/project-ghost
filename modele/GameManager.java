package modele;

/**
 * La classe qui est chargé du déroulement du jeu
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
	
	public void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn){
		if(whiteToPlay)
			white.movePawnAtTo(pawnLine, pawnColumn, destLine, destColumn);
		else
			black.movePawnAtTo(pawnLine, pawnColumn, destLine, destColumn);
		
		notifyObserver();
	}
	
	public void switchTurn(){
		whiteToPlay = !whiteToPlay;
		notifyObserver();
	}
	
	public String toString(){
		return white.toString() + black.toString() +
				"C'est au tour de " + (whiteToPlay? "blanc":"noir") + " de jouer\n" + 
				"Phase initialisation : " + initPhase;
	}

	public void addGoodPawnForWhitePlayerAt(int line, int column, boolean winUp) {
		white.addGoodPawnAt(line, column, winUp);
		notifyObserver();
	}

	public void addBadPawnForWhitePlayerAt(int line, int column) {
		white.addBadPawnAt(line, column);
		notifyObserver();
	}

	public void addGoodPawnForBlackPlayerAt(int line, int column, boolean winUp) {
		black.addGoodPawnAt(line, column, winUp);
		notifyObserver();
	}

	public void addBadPawnForBlackPlayerAt(int line, int column) {
		black.addBadPawnAt(line, column);
		notifyObserver();
	}
	
	public boolean allWhiteGoodPawnsSet(){
		return white.allGoodPawnsSet();	
	}
	
	public boolean allWhiteBadPawnsSet(){
		return white.allBadPawnsSet();
	}
	
	public boolean allBlackGoodPawnsSet(){
		return black.allGoodPawnsSet();	
	}
	
	public boolean allBlackBadPawnsSet(){
		return black.allBadPawnsSet();
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
}
