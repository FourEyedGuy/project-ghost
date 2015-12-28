package vue;

import java.awt.Color;
import javax.swing.JButton;

import utils.Parameters;

/**
 * La classe repr�sentant une case du plateau de jeu
 * @author Edgar Liang, Li Huanghuang
 *
 */
@SuppressWarnings("serial")
public class Square extends JButton{
	/**
	 * l'index de la case
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
	 * @param index valeur � mettre
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * retourne la ligne sur laquelle est situ� la case
	 */
	public int getLine(){
		return index/Parameters.BOARD_WIDTH;
	}
	
	/**
	 * retourne la colonne sur laquelle est situ� la case
	 */
	public int getColumn(){
		return index%Parameters.BOARD_WIDTH;
	}
}
