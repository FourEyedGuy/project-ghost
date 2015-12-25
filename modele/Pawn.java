package modele;

import constantes.Parameters;

/**
 * Pion
 * 
 * @author Edgar Liang, Li Huanghuang
 */
public abstract class Pawn{
	private int line;
	private int column;
	
	/**
	 * Creer un pion et le placer aux coordonnees demandees
	 * @param line
	 * @param column
	 */
	public Pawn(int line, int column){
		this.line = line;
		this.column = column;
	}
	
	/**
	 * convertit les coordonnees du pion courant (this) en String
	 * @return les coordonnees du pion en String
	 */
	public String getPosition() {
		return Pawn.getPosition(line, column);
	}
	
	/**
	 * convertit les coordonnees donnees en parametre en String
	 * @param line ligne
	 * @param column colonne
	 * @return coordonnees en String
	 */
	public static String getPosition(int line, int column){
		return Integer.toString(Parameters.BOARD_HEIGHT-line) + Character.toString((char) ('a'+ column));
	}
	
	/**
	 * Positionner le pion aux coordonnees demandees
	 * @param line la ligne sur laquelle positionner le pion
	 * @param column la colonne sur laquelle posiionner le pion
	 */
	public void setPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**
	 * Positionner le pion aux coordonnees indiquees
	 * @param coordinate les coordonnees sous le format String
	 */
	public void setPosition (String coord){
		 this.line = Pawn.getLine(coord); 
		 this.column = Pawn.getColumn(coord);
	}
	
	/**
	 * renvoie la ligne sur laquelle est situee le pion
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * A partir de coordonnees en String, renvoyer la ligne correspondante
	 * @param coord coordonees en String
	 * @return la ligne
	 */
	public static int getLine(String coord){
		return Parameters.BOARD_HEIGHT - Integer.parseInt(coord,0);
	}

	/**
	 * renvoi la colonne sur laquelle est situe le pion
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * A partir de coordonnees en String, renvoyer la colonne correspondante
	 * @param coord coordonnees en String
	 * @return la colonne
	 */
	public static int getColumn(String coord){
		return Character.getNumericValue(coord.charAt(1)) - 10;
	}

	/**
	 * deplacer le pion a la position indiquee (en String)
	 * @param square la case vers lequel deplacer le pion
	 */
	public void move(String square){
		setPosition(square);
	}
	
	/**
	 * déplace le pion à position indiqué
	 * 
	 * @param la ligne sur laquelle est situe la case vers laquelle se deplacer
	 * @paraml la colonne sur laquelle est situe la case vers laquelle se deplacer
	 */
	public void move(int line, int column){
		setPosition(line, column);
	}
	
	/**
	 * si le pion est un bon fantome
	 */
	public abstract boolean isGood();
	
	/**
	 * Renvoi les infos du pion
	 */
	public String toString(){
		return "Pion " + getClass().getSimpleName() + " (" + getLine() + "," + getColumn() + ") " + getPosition();
	}
}
