package modele;

import utils.Parameters;

/**
 * Pion fantôme gentil
 * @author Edgar Liang, Li Huanghuang
 *
 */
public class Good extends Pawn {
	private boolean winUp;
	
	/**
	 * créer un pion gentil et le placer aux coordonnées demandées
	 * @param line la ligne sur laquelle est situé la case cible
	 * @param column la colonne sur laquelle est situé la case cible
	 */
	public Good(int line, int column, boolean winUp) {
		super(line, column);
		this.winUp = winUp;
	}

	@Override
	public boolean isGood() {
		return true;
	}
	/**
	 * Dire si le gentil fantôme a réussi à sortir en atteignant l'une des deux cases de sortie
	 * @return true si le gentil fantôme à atteindre son objectif
	 */
	public boolean exit(){
		if(getColumn() == 0|| getColumn() == Parameters.BOARD_WIDTH - 1){
			if((getLine() == 0 && winUp)|| (getLine() == Parameters.BOARD_HEIGHT - 1 && !winUp))
				return true;
		}
		
		return false;
	}
}
