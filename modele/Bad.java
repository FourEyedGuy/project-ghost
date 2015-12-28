package modele;

/**
 * Pion M�chant fant�me
 * @author Edgar Liang, Li Huanghuang
 *
 */
public class Bad extends Pawn {

	/**
	 * cr�er un pion m�chant et le placer aux coordonn�es demand�es
	 * @param line la ligne sur laquelle est situ� la case cible
	 * @param column la colonne sur laquelle est situ� la case cible
	 */
	public Bad(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean isGood() {
		return false;
	}
	

}
