package modele;

import utils.Parameters;

/**
 * Pion fant�me gentil
 * @author Edgar Liang, Li Huanghuang
 *
 */
public class Good extends Pawn {
	private boolean winUp;
	
	/**
	 * cr�er un pion gentil et le placer aux coordonn�es demand�es
	 * @param line la ligne sur laquelle est situ� la case cible
	 * @param column la colonne sur laquelle est situ� la case cible
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
	 * Dire si le gentil fant�me a r�ussi � sortir en atteignant l'une des deux cases de sortie
	 * @return true si le gentil fant�me � atteindre son objectif
	 */
	public boolean exit(){
		if(getColumn() == 0|| getColumn() == Parameters.BOARD_WIDTH - 1){
			if((getLine() == 0 && winUp)|| (getLine() == Parameters.BOARD_HEIGHT - 1 && !winUp))
				return true;
		}
		
		return false;
	}
}
