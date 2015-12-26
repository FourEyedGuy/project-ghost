package controller;

import modele.AbstractModel;

public abstract class AbstractController {
	protected AbstractModel gameMngr;
	protected boolean isInitPhase;
	protected boolean whiteToPlay;
	protected boolean squareSelected = false;
	protected int pawnLine = -1;
	protected int pawnColumn = -1;
	protected int destLine = -1;
	protected int destColumn = -1;
	
	
	public AbstractController(AbstractModel gameMngr){
		this.gameMngr = gameMngr;
		isInitPhase = gameMngr.isInitPhase();
		whiteToPlay = gameMngr.isWhiteToPlay();
	}

	public void setLine(int line) {
		this.pawnLine = line;
	}

	public void setColumn(int column) {
		this.pawnColumn = column;
	}
	
	public void setSquareAt(int line, int column){
		setLine(line);
		setColumn(column);
		control();
	}
	
	public void setDestLine(int destLine) {
		this.destLine = destLine;
	}

	public void setDestColumn(int destColumn) {
		this.destColumn = destColumn;
	}
	
	public void setDestSquateAt(int destLine, int destColumn){
		setDestLine(destLine);
		setDestColumn(destColumn);
		control();
	}
	
	public void standByUpdate(){
		gameMngr.notifyObserver();
	}
	
	public boolean thereIsPawnAt(int line, int column){
		return gameMngr.thereIsPawnAt(line, column);
	}
	
	public boolean currentPlayerWin(){
		if(gameMngr.isWhiteToPlay()){
			if(gameMngr.whitePlayerWon()) return true;
			else if(gameMngr.blackPlayerWon()) return false;
			return false;
		}else{
			if(gameMngr.blackPlayerWon()) return true;
			else if(gameMngr.whitePlayerWon()) return false;
			return false;
		}
	}

	public abstract void control();
	public abstract void initControl();
	public abstract void playControl();
	public abstract boolean gameEnded();
}
