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
	private int index;
	
	public Square (String name){
		super(name);
		setBackground(Color.WHITE);
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getLine(){
		return index/Parameters.BOARD_WIDTH;
	}
	
	public int getColumn(){
		return index%Parameters.BOARD_WIDTH;
	}
}
