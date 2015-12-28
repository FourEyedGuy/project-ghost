package observer;

import modele.Player;

/**
 * Interface observateur
 * @author Liang Edgar, Li Huanghuang
 *
 */
public interface Observer {
	/**
	 * mettre l'observateur � jour, avec les objets pass� en arguments
	 * @param white le joueur blanc (en bas au d�but)
	 * @param black le joueur noir (en haut au d�but)
	 * @param whiteToPlay si c'est au joueur blanc de jouer
	 * @param initPhase si on est � la phase de placements initiaux des pions
	 */
	public void update(Player white, Player black, boolean whiteToPlay, boolean initPhase);
}
