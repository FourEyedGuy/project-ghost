package observer;

import java.util.ArrayList;

import modele.Pawn;
import modele.Player;

public interface Observer {
	public void update(Player white, Player black, boolean whiteToPlay, boolean initPhase);
}
