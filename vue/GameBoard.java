package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import constantes.Parameters;

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
	 * change le texte de la case indiquée
	 * @param text texte à mettre
	 * @param line ligne sur laquelle est située la case indiquée
	 * @param column colonne sur laquelle est situé la case indiquée
	 */
	public void setSquareAt(String text, int line, int column){
		int index = line * Parameters.BOARD_WIDTH + column;
		
		squares[index].setText(text);
	}
	
	/**
	 * Renvoyer la case aux coordonnées indiquées
	 * @param line la ligne sur laquelle est situé la case
	 * @param column la colonne sur laquelle est situé la case
	 * @return la case aux coordonnées (ligne, colonne)
	 */
	public Square getSquareAt(int line, int column){
		return squares[line * Parameters.BOARD_WIDTH + column];
	}
	
	/**
	 * Renvoyer la case à l'index demandée
	 * @param index l'index de la case demandée
	 * @return la case à l'index index
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
	 * active ou désactive l'ensemble des boutons (cases) du plateau de jeu
	 * @param on true pour activer, false pour désactiver
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
	 * Ajoute un écouteur à chacun des cases du plateau
	 * @param actionListener écouteur à ajouter
	 */
	public void addListeners(ActionListener actionListener){
		for (Square square:squares){
			square.addActionListener(actionListener);
		}
	}
	
	/**
	 * enlève un écouteur à l'ensemble des cases du plateau
	 * @param actionListener écouteur à enlever
	 */
	public void removeListeners(ActionListener actionListener){
		for (Square square:squares){
			square.addActionListener(actionListener);
		}
	}
}
