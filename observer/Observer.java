package observer;

import modele.Player;

public interface Observer {
	public void update(Player white, Player black, boolean whiteToPlay, boolean initPhase);
}
