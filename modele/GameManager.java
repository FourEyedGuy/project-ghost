package modele;

/**
 * La classe qui est chargé du déroulement du jeu
 * @author Edgar Liang, Li Huanghuang
 *
 */
public class GameManager{
	private Player white;
	private Player black;
	
	/**
	 * true si c'est au joueur blanc de jouer (premier joueur par défaut)
	 */
	private boolean whiteToPlay = true;
	
	/**
	 * true si on est à la phase d'initialisation (placement initial des pions)
	 */
	private boolean initPhase = true;
	
	/**
	 * Construteur
	 * 
	 * @param nameW le nom du joueur qui joue les pions blancs
	 * @param nameB le nom du joueur qui joue les pions noirs
	 */
	public GameManager(String nameW, String nameB){
		this.white = new Player(nameW);
		this.black = new Player(nameB); 
	}
	
	/**
	 * Constructeur avec les noms par défauts ("Blanc" pour white et "Noir" pour black)
	 */
	public GameManager(){
		white = new Player("Blanc");
		black = new Player("Noir");
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
	
	
	public boolean isInitPhase() {
		return initPhase;
	}

	public void setInitPhase(boolean initPhase) {
		this.initPhase = initPhase;
	}

	/**
	 * A FAIRE
	 * initialise le jeu en début de partie :
	 * créer pour chaque joueur les 4 pions gentils et les 4 pions méchants
	 */
	public void init(){
		
	}


	/**
	 * l'ensemble des actions qui sont réalise pendant un tour de jeu
	 */
	public void doAction(){
		
	}
	
	public boolean move(int line, int column){
		return false;
	}
	
	/**
	 * Dire si le jeu est fini, si les conditions de fin de jeu est realise :
	 * - Un joueur a capture tout les bon fantomes de son adversaire
	 * - Un joueur a capture tout les mauvais fantôme de son adversaire
	 * - Un joueur a reussi à faire sortir tout ses bon fantomes
	 * 
	 * @return true si le jeu est terminé
	 */
	public boolean gameEnded(){
		return false;
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
}
