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
public class Main {
	public static void main(String[] args) {
		boolean cheat = false;
		if(args.length > 0) cheat = args[0].equals("cheat");
		
		AbstractModel ghostGame = new GameManager();
		
		AbstractController controller = new Controller(ghostGame);
		
		Fenetre fenetre = new Fenetre(controller, cheat);
		
		ghostGame.addObserver(fenetre);
	}
}
