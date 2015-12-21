package controller;

import constantes.Parameters;
import modele.AbstractModel;

public class Controller extends AbstractController{

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
		
		pawnLine = -1;
		pawnColumn = -1;
		destLine = -1;
		destColumn = -1;
		
		System.out.println(gameMngr.toString());
	}

	@Override
	public void initControl() {
		if(gameMngr.isWhiteToPlay()){
			if(!gameMngr.allWhiteGoodPawnsSet()){
				if(isAValidInitSquare())
					gameMngr.addGoodPawnForWhitePlayerAt(pawnLine, pawnColumn, true);
			}else if(!gameMngr.allWhiteBadPawnsSet()){
				if(isAValidInitSquare())
					gameMngr.addBadPawnForWhitePlayerAt(pawnLine, pawnColumn);
			}
			
			if(gameMngr.allWhiteGoodPawnsSet() && gameMngr.allWhiteBadPawnsSet())
				gameMngr.switchTurn();
		}else{
			if(!gameMngr.allBlackGoodPawnsSet()){
				if(isAValidInitSquare())
					gameMngr.addGoodPawnForBlackPlayerAt(pawnLine, pawnColumn, true);
			}else if(!gameMngr.allBlackBadPawnsSet()){
				if(isAValidInitSquare())
					gameMngr.addBadPawnForBlackPlayerAt(pawnLine, pawnColumn);
			}
			
			if(gameMngr.allBlackGoodPawnsSet() && gameMngr.allBlackBadPawnsSet()){
				gameMngr.switchTurn();
				gameMngr.setInitPhase(false);
			}
		}
	}
	
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
		// TODO Auto-generated method stub
		
	}
}
