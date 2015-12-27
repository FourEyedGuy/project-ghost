package observer;

import modele.Player;

/**
 * Interface observateur
 * @author Liang Edgar, Li Huanghuang
 *
 */
public interface Observer {
	/**
	 * mettre l'observateur à jour, avec les objets passé en arguments
	 * @param white le joueur blanc (en bas au début)
	 * @param black le joueur noir (en haut au début)
	 * @param whiteToPlay si c'est au joueur blanc de jouer
	 * @param initPhase si on est à la phase de placements initiaux des pions
	 */
	public void update(Player white, Player black, boolean whiteToPlay, boolean initPhase);
}
