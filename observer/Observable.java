package observer;

/**
 * interface observable : est observ� par un(des) observateur(s), l' (les) avertir de tout changement en lui
 * @author Liang Edgar, Li Huanghuang
 */
public interface Observable {
	/**
	 * ajoute un observateur dans la liste
	 * @param obs obsservateur � ajouter
	 */
	public void addObserver(Observer obs);
	
	/**
	 * enl�ve un observateur de la liste
	 */
	public void removeObserver();
	
	/**
	 * pr�vient les observateurs de liste d'un changement chez l'observ� (observable)
	 */
	public void notifyObserver();
}
