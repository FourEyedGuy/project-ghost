package modele;

/**
 * La classe qui est charg� du d�roulement du jeu
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
	 * Constructeur avec les noms par d�fauts ("Blanc" pour white et "Noir" pour black)
	 */
	public GameManager(){
		super();
	}
	
	public void movePawnAt(int pawnLine, int pawnColumn, int destLine, int destColumn, boolean whiteToPlay){
		if(whiteToPlay)
			white.movePawnAtTo(pawnLine, pawnColumn, destLine, destColumn);
		else
			black.movePawnAtTo(pawnLine, pawnColumn, destLine, destColumn);
	}
	
	/**
	 * Dire si le jeu est fini, si les conditions de fin de jeu est realise :
	 * - Un joueur a capture tout les bon fantomes de son adversaire
	 * - Un joueur a capture tout les mauvais fant�me de son adversaire
	 * - Un joueur a reussi � faire sortir tout ses bon fantomes
	 * 
	 * @return true si le jeu est termin�
	 */
	public boolean gameEnded(){
		return false;
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
		return null;
	}
	
	public void switchTurn(){
		whiteToPlay = !whiteToPlay; 
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
}
