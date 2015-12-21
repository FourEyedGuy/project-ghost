package controller;

import constantes.Parameters;
import modele.AbstractModel;
import modele.GameManager;
import modele.Player;
import vue.Square;

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
			}else{
				gameMngr.setWhiteToPlay(false);
			}
		}else{
			if(!gameMngr.allBlackGoodPawnsSet()){
				if(isAValidInitSquare())
					gameMngr.addGoodPawnForBlackPlayerAt(pawnLine, pawnColumn, true);
			}else if(!gameMngr.allBlackBadPawnsSet()){
				if(isAValidInitSquare())
					gameMngr.addBadPawnForBlackPlayerAt(pawnLine, pawnColumn);
			}else{
				gameMngr.setWhiteToPlay(true);
				gameMngr.setInitPhase(false);
			}
		}
	}
	
	/**
	 * place tous les pions pour un joueur donne
	 * @param player le joueur pour qui on doit placer les pions
	 * @param winUp si le joueur doit amener ses pions gentils vers les deux cases de sortie du haut
	 * @return true si on a fini de placer tous les pions pour le joueurs
	 */
	private boolean placePawnsForAPlayer(Player player, boolean winUp){
		//placer tous les pions bons
		if(!player.allGoodPawnsSet()){
			if(isAValidInitSquare()){
				player.addGoodPawnAt(pawnLine, pawnColumn, winUp);
			}
		}
		//placer les pions méchants
		else if (!player.allBadPawnsSet()){
			if(isAValidInitSquare()){
				player.addBadPawnAt(pawnLine, pawnColumn);
				return true;
			}
		}
		return false;
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
