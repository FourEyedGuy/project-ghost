package modele;

import utils.Parameters;

/**
 * Pion : il peut être soit gentil (Good), soit méchant (Bad)
 * 
 * @author Edgar Liang, Li Huanghuang
 */
public abstract class Pawn{
	private int line;
	private int column;
	
	/**
	 * Créer un pion et le placer aux coordonnées demandées
	 * @param line la ligne sur laquelle placer le pion
	 * @param column la colonne sur laquelle placer le pion
	 */
	public Pawn(int line, int column){
		this.line = line;
		this.column = column;
	}
	
	/**
	 * convertit les coordonnées du pion courant (this) en String
	 * @return les coordonnees du pion en String
	 */
	public String getPosition() {
		return Pawn.getPosition(line, column);
	}
	
	/**
	 * convertit les coordonnées données en paramètre en String
	 * @param line ligne de la coordonnée à convertir
	 * @param column colonne de la coordonées à convertir
	 * @return coordonnées en String
	 */
	public static String getPosition(int line, int column){
		return Integer.toString(Parameters.BOARD_HEIGHT-line) + Character.toString((char) ('a'+ column));
	}
	
	/**
	 * Positionne le pion aux coordonnées demandées
	 * @param line la ligne sur laquelle est situé la case cible
	 * @param column la colonne sur laquelle est situé la case cible
	 */
	public void setPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**
	 * Positionne le pion aux coordonnées indiquées
	 * @param coord les coordonnées cibles au format String
	 */
	public void setPosition (String coord){
		 this.line = Pawn.getLine(coord); 
		 this.column = Pawn.getColumn(coord);
	}
	
	/**
	 * renvoie la ligne sur laquelle est située le pion
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * A partir de coordonnées en String, renvoyer la ligne correspondante
	 * @param coord coordonées en String
	 * @return la ligne correspondant aux coordonnées en String
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
	 * A partir de coordonnées en String, renvoyer la colonne correspondante
	 * @param coord coordonnées en String
	 * @return la colonne correspondant aux coordonées en String
	 */
	public static int getColumn(String coord){
		return Character.getNumericValue(coord.charAt(1)) - 10;
	}

	/**
	 * déplacer le pion à la position indiquée (en String)
	 * @param square la case vers lequel déplacer le pion
	 */
	public void move(String square){
		setPosition(square);
	}
	
	/**
	 * déplace le pion à la position indiquée
	 * 
	 * @param line la ligne vers laquelle déplacer le pion
	 * @paraml column la colonne vers laquelle déplacer le pion
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
