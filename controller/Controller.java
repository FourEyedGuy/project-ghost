package controller;

import constantes.Direction;
import constantes.Parameters;
import modele.AbstractModel;

public class Controller extends AbstractController{

	/**
	 * constructeur 
	 * @param gameMngr modèle gérant l'état du jeu
	 */
	public Controller(AbstractModel gameMngr) {
		super(gameMngr);
	}

	@Override
	public void control() {
		if(gameMngr.isInitPhase()){
			initControl();
		}
		else
			playControl();
	}

	@Override
	public void initControl() {
		if(isAValidInitSquare()){
			if(!gameMngr.allCurrentPlayerGoodPwnSet())
				gameMngr.addCurrentPlayerGoodPwnAt(pawnLine, pawnColumn);
			else if(!gameMngr.allCurrentPlayerBadPwnSet())
				gameMngr.addCurrentPlayerBadPwnAt(pawnLine, pawnColumn);
			
			if(gameMngr.allCurrentPlayerGoodPwnSet() && gameMngr.allCurrentPlayerBadPwnSet()){
				if(!gameMngr.isWhiteToPlay()) gameMngr.setInitPhase(false);
				gameMngr.switchTurn();
			}
		}
		
		pawnLine = -1;
		pawnColumn = -1;
	}
	
	/**
	 * dire si la case sélectionnée correspond à une case valide du pion
	 * @return true si la case sélectionnée correspond à une case valide du pion
	 */
	private boolean isAValidInitSquare(){
		if(gameMngr.isWhiteToPlay()){
			if(pawnLine >= Parameters.BOARD_HEIGHT-2 && pawnLine < Parameters.BOARD_HEIGHT){
				if(pawnColumn > 0 && pawnColumn < Parameters.BOARD_WIDTH-1)
					return true;
			}
		}
		else{
			if(pawnLine >= 0 && pawnLine < 2){
				if(pawnColumn > 0 && pawnColumn < Parameters.BOARD_WIDTH-1)
					return true;
			}
		}
		return false;
	}

	@Override
	public void playControl() {
		if(isAValidMove()){
			gameMngr.movePawnAt(pawnLine, pawnColumn, destLine, destColumn);
			if(gameMngr.thereIsOpponentPawnAt(destLine, destColumn)) gameMngr.removeOpponentPawnAt(destLine, destColumn);
			if(!gameEnded()) gameMngr.switchTurn();
		}
		
		
		if(destLine >= 0 && destColumn >= 0){
			pawnLine = -1;
			pawnColumn = -1;
			destLine = -1;
			destColumn = -1;
		}
		
		
	}
	
	/**
	 * dire si le déplacement est valide
	 * @return vrai si le déplacement est valide
	 */
	private boolean isAValidMove(){
			for(Direction dir:Direction.values()){
				if(pawnLine + dir.getLine() == destLine && pawnColumn + dir.getColumn() == destColumn && !gameMngr.thereIsPawnAt(destLine, destColumn))
					return true;
			}
		return false;
	}

	@Override
	public boolean gameEnded() {
		return gameMngr.gameEnded();
	}
}
