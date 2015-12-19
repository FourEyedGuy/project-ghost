package modele;
import org.omg.Dynamic.Parameter;

import constantes.*;

/**
 * Mechant fantome
 * @author Edgar
 *
 */
public class Bad extends Pawn {

	/**
	 * creer un pion mechant et le placer aux coordonnees demandees
	 * @param line
	 * @param column
	 */
	public Bad(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean isGood() {
		return false;
	}
	

}
