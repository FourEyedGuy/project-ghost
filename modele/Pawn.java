package modele;

import utils.Parameters;

/**
 * Pion : il peut �tre soit gentil (Good), soit m�chant (Bad)
 * 
 * @author Edgar Liang, Li Huanghuang
 */
public abstract class Pawn{
	private int line;
	private int column;
	
	/**
	 * Cr�er un pion et le placer aux coordonn�es demand�es
	 * @param line la ligne sur laquelle placer le pion
	 * @param column la colonne sur laquelle placer le pion
	 */
	public Pawn(int line, int column){
		this.line = line;
		this.column = column;
	}
	
	/**
	 * convertit les coordonn�es du pion courant (this) en String
	 * @return les coordonnees du pion en String
	 */
	public String getPosition() {
		return Pawn.getPosition(line, column);
	}
	
	/**
	 * convertit les coordonn�es donn�es en param�tre en String
	 * @param line ligne de la coordonn�e � convertir
	 * @param column colonne de la coordon�es � convertir
	 * @return coordonn�es en String
	 */
	public static String getPosition(int line, int column){
		return Integer.toString(Parameters.BOARD_HEIGHT-line) + Character.toString((char) ('a'+ column));
	}
	
	/**
	 * Positionne le pion aux coordonn�es demand�es
	 * @param line la ligne sur laquelle est situ� la case cible
	 * @param column la colonne sur laquelle est situ� la case cible
	 */
	public void setPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**
	 * Positionne le pion aux coordonn�es indiqu�es
	 * @param coord les coordonn�es cibles au format String
	 */
	public void setPosition (String coord){
		 this.line = Pawn.getLine(coord); 
		 this.column = Pawn.getColumn(coord);
	}
	
	/**
	 * renvoie la ligne sur laquelle est situ�e le pion
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * A partir de coordonn�es en String, renvoyer la ligne correspondante
	 * @param coord coordon�es en String
	 * @return la ligne correspondant aux coordonn�es en String
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
	 * A partir de coordonn�es en String, renvoyer la colonne correspondante
	 * @param coord coordonn�es en String
	 * @return la colonne correspondant aux coordon�es en String
	 */
	public static int getColumn(String coord){
		return Character.getNumericValue(coord.charAt(1)) - 10;
	}

	/**
	 * d�placer le pion � la position indiqu�e (en String)
	 * @param square la case vers lequel d�placer le pion
	 */
	public void move(String square){
		setPosition(square);
	}
	
	/**
	 * d�place le pion � la position indiqu�e
	 * 
	 * @param line la ligne vers laquelle d�placer le pion
	 * @paraml column la colonne vers laquelle d�placer le pion
	 */
	public void move(int line, int column){
		setPosition(line, column);
	}
	
	/**
	 * si le pion est un gentil fantome
	 */
	public abstract boolean isGood();
	
	/**
	 * Renvoi les infomartions du pion en String
	 */
	public String toString(){
		return "Pion " + getClass().getSimpleName() + " (" + getLine() + "," + getColumn() + ") " + getPosition();
	}
}
