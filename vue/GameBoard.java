package vue;

import java.awt.GridLayout;
import javax.swing.JPanel;

import constantes.Parameters;

public class GameBoard extends JPanel{
	
	private Square[] squares = new Square[Parameters.BOARD_WIDTH * Parameters.BOARD_HEIGHT];
	
	public GameBoard(){
		setLayout(new GridLayout(Parameters.BOARD_HEIGHT,Parameters.BOARD_WIDTH));
		
		for(int i=0; i<(Parameters.BOARD_HEIGHT * Parameters.BOARD_WIDTH); i++){
			squares[i] = new Square("");
			squares[i].setIndex(i);
			add(squares[i]);
		}
	}
	
	public void setSquareAt(String text, int line, int column){
		int index = line * Parameters.BOARD_WIDTH + column;
		
		squares[index].setText(text);
	}
	
	public Square getSquareAt(int line, int column){
		return squares[line * Parameters.BOARD_WIDTH + column];
	}
	
	public Square getSquareAt(int index){
		return squares[index];
	}
	
	private boolean isOutOfBound(int line, int column){
		return line < 0 || line >= Parameters.BOARD_HEIGHT || column < 0 || column >= Parameters.BOARD_WIDTH;
	}

	public void init(){
		for(int i=1; i<5; i++){
			setSquareAt("Noir" , 0, i);
			setSquareAt("Noir" , 1, i);
			setSquareAt("Blanc" , 4, i);
			setSquareAt("Blanc" , 5, i);
		}
	}
}
