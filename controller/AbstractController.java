package controller;

import constantes.Direction;
import modele.AbstractModel;

public abstract class AbstractController {
	protected AbstractModel gameMngr;
	protected boolean isInitPhase;
	protected int pawnLine = -1;
	protected int pawnColumn = -1;
	protected int destLine = -1;
	protected int destColumn = -1;
	
	
	public AbstractController(AbstractModel gameMngr){
		this.gameMngr = gameMngr;
		isInitPhase = gameMngr.isInitPhase();
	}

	public void setLine(int line) {
		this.pawnLine = line;
		control();
	}

	public void setColumn(int column) {
		this.pawnColumn = column;
		control();
	}
	
	
	public void setDestLine(int destLine) {
		this.destLine = destLine;
		control();
	}

	public void setDestColumn(int destColumn) {
		this.destColumn = destColumn;
		control();
	}

	public abstract void control();
	public abstract void initControl();
	public abstract void playControl();
}
