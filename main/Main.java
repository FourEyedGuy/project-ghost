package main;

import vue.Fenetre;

import java.util.Scanner;

import controller.AbstractController;
import controller.Controller;
import modele.*;

public class Main {
	public static void main(String[] args) {
		AbstractModel ghostGame = new GameManager();
		
		AbstractController controller = new Controller(ghostGame);
		
		Fenetre fenetre = new Fenetre(controller);
		
		ghostGame.addObserver(fenetre);
	}
}
