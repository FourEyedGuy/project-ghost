package modele;

import constantes.Parameters;

/**
 * Pion gentil
 * @author Edgar
 *
 */
public class Good extends Pawn {
	private boolean winUp;
	
	/**
	 * creer un pion gentil et le placer aux coordonnees demandees
	 * @param line
	 * @param column
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
	 * Dire si le gentil fantome a reussi à sortir en atteignant l'une des deux cases de sortie
	 * @return true si le gentil fantome a atteint son objectif
	 */
	public boolean exit(){
		
		if(getColumn() == 0|| getColumn() == Parameters.BOARD_WIDTH - 1){
			if((getLine() == 0 && winUp)|| (getLine() == Parameters.BOARD_HEIGHT - 1 && !winUp))
				return true;
		}
		
		return false;
	}
}
