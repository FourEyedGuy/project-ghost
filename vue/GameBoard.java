package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

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

	public void setExits(){
		Color exitSquareColor = Color.gray;
		
		getSquareAt(0, 0).setBackground(exitSquareColor);
		getSquareAt(0, Parameters.BOARD_WIDTH-1).setBackground(exitSquareColor);
		getSquareAt(Parameters.BOARD_HEIGHT - 1, 0).setBackground(exitSquareColor);
		getSquareAt(Parameters.BOARD_HEIGHT-1, Parameters.BOARD_WIDTH-1).setBackground(exitSquareColor);
	}
	
	public void setEnabled(boolean on){
		for (Square square : squares)
			square.setEnabled(on);
	}
	
	public void clear(){
		for (Square square : squares)
			square.setText("");
		
		setExits();
	}
	
	public void addListeners(ActionListener actionListener){
		for (Square square:squares){
			square.addActionListener(actionListener);
		}
	}
	
	public void removeListeners(ActionListener actionListener){
		for (Square square:squares){
			square.addActionListener(actionListener);
		}
	}
}
