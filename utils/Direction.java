package utils;

/**
 * Les directions que le joueur peut choisir pour chacun de ses pions
 * @author Edgar Liang, Huanghuang Li
 *
 */
public enum Direction {
	LEFT(0,-1),
	RIGHT(0,1),
	UP(-1,0),
	DOWN(1,0);
	
	
	private int line;
	private int column;
	
	Direction(int line, int column){
		this.line = line;
		this.column = column;
	}

	/**
	 * le facteur de déplacement horizontal de la direction
	 */
	public int getLine() {
		return line;
	}

	/**
	 * le facteur de déplacement vertical de la direction
	 */
	public int getColumn() {
		return column;
	}
}
