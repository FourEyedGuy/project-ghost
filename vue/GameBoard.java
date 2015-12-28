package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import utils.Parameters;

/**
 * la classe construisant le plateau du jeu dans la fen�tre
 * @author Edgar Liang
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel{
	
	/**
	 * liste des cases du plateau de jeu
	 */
	private Square[] squares = new Square[Parameters.BOARD_WIDTH * Parameters.BOARD_HEIGHT];
	
	/**
	 * constructeur 
	 */
	public GameBoard(){
		setLayout(new GridLayout(Parameters.BOARD_HEIGHT,Parameters.BOARD_WIDTH));
		
		for(int i=0; i<(Parameters.BOARD_HEIGHT * Parameters.BOARD_WIDTH); i++){
			squares[i] = new Square("");
			squares[i].setIndex(i);
			add(squares[i]);
		}
	}
	
	/**
	 * change le texte de la case indiqu�e
	 * @param text texte à mettre
	 * @param line ligne sur laquelle est situ�e la case indiqu�e
	 * @param column colonne sur laquelle est situé la case indiqu�e
	 */
	public void setSquareAt(String text, int line, int column){
		int index = line * Parameters.BOARD_WIDTH + column;
		
		squares[index].setText(text);
	}
	
	/**
	 * Renvoie la case aux coordonn�es indiqu�es
	 * @param line la ligne sur laquelle est situ�e la case
	 * @param column la colonne sur laquelle est situ�e la case
	 * @return la case aux coordonn�es (ligne, colonne)
	 */
	public Square getSquareAt(int line, int column){
		return squares[line * Parameters.BOARD_WIDTH + column];
	}
	
	/**
	 * Renvoyer la case � l'index demand�e
	 * @param index l'index de la case demand�e
	 * @return la case � l'index index
	 */
	public Square getSquareAt(int index){
		return squares[index];
	}

	/**
	 * colorie les cases de sortie
	 */
	public void setExits(){
		Color exitSquareColor = Color.gray;
		
		getSquareAt(0, 0).setBackground(exitSquareColor);
		getSquareAt(0, Parameters.BOARD_WIDTH-1).setBackground(exitSquareColor);
		getSquareAt(Parameters.BOARD_HEIGHT - 1, 0).setBackground(exitSquareColor);
		getSquareAt(Parameters.BOARD_HEIGHT-1, Parameters.BOARD_WIDTH-1).setBackground(exitSquareColor);
	}
	
	/**
	 * active ou d�sactive l'ensemble des boutons (cases) du plateau de jeu
	 * @param on true pour activer, false pour d�sactiver
	 */
	public void setEnabled(boolean on){
		for (Square square : squares)
			square.setEnabled(on);
	}
	
	/**
	 * efface le contenue de tous les cases
	 */
	public void clear(){
		for (Square square : squares)
			square.setText("");
		
		setExits();
	}
	
	/**
	 * Ajoute un �couteur � chacun des cases du plateau
	 * @param actionListener �couteur � ajouter
	 */
	public void addListeners(ActionListener actionListener){
		for (Square square:squares){
			square.addActionListener(actionListener);
		}
	}
	
	/**
	 * enl�ve un �couteur � l'ensemble des cases du plateau
	 * @param actionListener �couteur � enlever
	 */
	public void removeListeners(ActionListener actionListener){
		for (Square square:squares){
			square.addActionListener(actionListener);
		}
	}
}
