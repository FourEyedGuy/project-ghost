package vue;

import java.awt.Color;
import javax.swing.JButton;

import constantes.Parameters;

/**
 * 
 * @author Edgar Liang, Li Huanghuang
 *
 */
@SuppressWarnings("serial")
public class Square extends JButton{
	/**
	 * l'index de la case, c'est pour mieux le reperer par rapport au plateau (GameBoard)
	 */
	private int index;
	
	/**
	 * constructeur
	 * @param name le texte contenu dans la case
	 */
	public Square (String name){
		super(name);
		setBackground(Color.WHITE);
	}
	
	/**
	 * retourne l'index de la case courante
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * change la valeur de la case courante
	 * @param index valeur à mettre
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * retourne la ligne correspond à l'index
	 */
	public int getLine(){
		return index/Parameters.BOARD_WIDTH;
	}
	
	/**
	 * retourne la colone correspond à l'index
	 */
	public int getColumn(){
		return index%Parameters.BOARD_WIDTH;
	}
}
