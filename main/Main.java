package main;

import vue.Fenetre;

import java.util.Scanner;

import modele.*;

public class Main {
	public static void main(String[] args) {
		new Fenetre(new GameManager());
	}
}
