package modele;

/**
 * Pion Méchant fantôme
 * @author Edgar Liang, Li Huanghuang
 *
 */
public class Bad extends Pawn {

	/**
	 * créer un pion méchant et le placer aux coordonnées demandées
	 * @param line la ligne sur laquelle est situé la case cible
	 * @param column la colonne sur laquelle est situé la case cible
	 */
	public Bad(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean isGood() {
		return false;
	}
	

}
