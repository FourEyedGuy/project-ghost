package observer;

import java.util.ArrayList;

import modele.Pawn;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver();
}
