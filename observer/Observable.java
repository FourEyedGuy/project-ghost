package observer;

/**
 * interface observable : est observé par un(des) observateur(s), l' (les) avertir de tout changement en lui
 * @author Liang Edgar, Li Huanghuang
 */
public interface Observable {
	/**
	 * ajoute un observateur dans la liste
	 * @param obs obsservateur à ajouter
	 */
	public void addObserver(Observer obs);
	
	/**
	 * enlève un observateur de la liste
	 */
	public void removeObserver();
	
	/**
	 * prévient les observateurs de liste d'un changement chez l'observé (observable)
	 */
	public void notifyObserver();
}
