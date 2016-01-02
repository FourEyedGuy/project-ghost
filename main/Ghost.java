package main;

import vue.Fenetre;

import controller.AbstractController;
import controller.Controller;
import modele.*;

/**
 * la classe Main
 * 
 * @author Edgar Liang Li Huanghuang
 *
 */
public class Ghost {
	public static void main(String[] args) {
		//s'il a ete demande de lancer le jeu en mode triche
		boolean cheat = false;
		if(args.length > 0) cheat = args[0].equals("cheat");
		
		//instanciation du modele
		AbstractModel ghostGame = new GameManager();
		
		//instanciation du controleur
		AbstractController controller = new Controller(ghostGame);
		
		//instanciation de la vue 
		Fenetre fenetre = new Fenetre(controller, cheat);
		
		//ajout des observateurs
		ghostGame.addObserver(fenetre);
	}
}
