package modele;
import java.util.ArrayList;

import constantes.*;

/**
 * La classe joueur
 * @author Edgar
 *
 */
public class Player {
	private String name;
	private ArrayList<Good> goodPawns;
	private ArrayList<Bad> badPawns;

	/**
	 * Constructeur
	 * @param name le nom du joueur
	 */
	public Player (String name){
		this.name = name;
		this.goodPawns = new ArrayList<Good>();
		this.badPawns = new ArrayList<Bad>();	
	}
	
	/**
	 * @return le nom du joueur
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * instancie les pions fant�mes gentils du joueur
	 * @param goodPawns un tableau de taille 4 de l'ensemble des Good
	 */
	public void setGoodPawns(ArrayList<Good> goodPawns) {
		this.goodPawns = goodPawns;
	}
	
	/**
	 * instancie les pions fant�mes m�chants du joueur
	 * @param badPawns un tableau de taille 4 de l'ensemble des Bad
	 */
	public void setBadPawns(ArrayList<Bad> badPawns) {
		this.badPawns = badPawns;
	}
	
	/**
	 * Ajoute aux coordonn�es indiqu�es un pion fant�me gentils
	 * @param line
	 * @param column
	 * 
	 * @return true si un pion a effectivement �t� ajout�
	 */
	public boolean addGoodPawnAt(int line, int column, boolean winUp){
		for (int i = 0; i < goodPawns.size(); i++){
			if (line == goodPawns.get(i).getLine() && column == goodPawns.get(i).getColumn())
				return false;
		}
		
		for (int i = 0; i < badPawns.size(); i++){
			if (line == badPawns.get(i).getLine() && column == badPawns.get(i).getColumn())
				return false;
		}
		
		goodPawns.add(new Good(line, column, winUp));
		return true;
	}
	
	/**
	 * Ajoute aux coordonn�es indiqu�es un pion fant�me m�chants
	 * @param line
	 * @param column
	 * 
	 * @return true si un pion a effectivement �t� ajout�
	 */
	public boolean addBadPawnAt(int line, int column){
		for (int i = 0; i < goodPawns.size(); i++){
			if (line == goodPawns.get(i).getLine() && column == goodPawns.get(i).getColumn())
				return false;
		}
		
		for (int i = 0; i < badPawns.size(); i++){
			if (line == badPawns.get(i).getLine() && column == badPawns.get(i).getColumn())
				return false;
		}
	
		badPawns.add(new Bad(line, column));
		return true;
	}
	
	/**
	 * A FAIRE
	 * D�place un pion qui est plac� au coordon�e indiqu�e
	 * @param dir la direction vers lequel d�placer le pion
	 * @param line la ligne sur laquelle est sit� le pion
	 * @param column la colonne sur lequel est situ� le pion
	 * 
	 * @return true s'il y a eu d�placement de pion
	 */
	public boolean movePawn(Direction dir, int line, int column){
		if (!thereIsPawnAt(line, column)) return false;
		
		//for()
		
		return true;
	}
	
	/**
	 * Dire s'il y a un pion aux coordonn�es indiqu�es
	 * @param line la ligne correspondant � la case demand�ee
	 * @param column la colonne corespondant � la case demand�e
	 * @return true s'il y a un pion sur la case demand�e
	 */
	public boolean thereIsPawnAt(int line, int column){
		for (int i = 0; i < goodPawns.size(); i++){
			if (goodPawns.get(i).getLine() == line && goodPawns.get(i).getColumn() == column)
				return true;
		}
		
		for (int i = 0; i < badPawns.size(); i++){
			if (badPawns.get(i).getLine() == line && badPawns.get(i).getColumn() == column)
				return true;
		}
		
		return false;
	}
	
	/**
	 * A FAIRE
	 * Dire si un pion gentil vient de sortir
	 * @return true si un pion gentil vient de sortir
	 */
	public boolean aGoodHasExited(){
		//pas prioitaire : je ne suis m�me pas s�r qu'on aurait besoin de cette m�thode
		return false;
	}
	
	/**
	 * Dire si tous les pions gentils ont �t� captur�s
	 * @return true si tous les pions gentils ont �t� captur�s
	 */
	public boolean allGoodBeenCaptured(){
		if (goodPawns.size() == 0)
			return true;
		else
			return false;
	}

	/**
	 * Dire si tous les pions m�chants ont �t� captur�s
	 * @return true si tous les pions m�chants ont �t� captur�s
	 */
	public boolean allBadBeenCaptured(){
		if (badPawns.size()==0)
			return true;
		else
			return false;
	}
	
	/**
	 * A FAIRE
	 * retire un pion situ� aux coordonn�es indiqu�s
	 * @param ligne la ligne sur laquelle est situ�e le pion � retirer
	 * @param colone la colonne sur lequel est situ� le pion � retirer
	 * 
	 * @return true si un pion a �t� retir�;
	 */
	public boolean removePawnAt(int ligne, int colone){
		for (int i = 0; i < goodPawns.size(); i++){
			if(ligne == goodPawns.get(i).getLine() && colone == goodPawns.get(i).getColumn()){
				goodPawns.remove(i);
				return true;
			}	
		}
		
		for (int i = 0; i < badPawns.size(); i++){
			if(ligne == badPawns.get(i).getLine() && colone == badPawns.get(i).getColumn()){
				badPawns.remove(i);
				return true;
			}
				
		}
		
		return false;
	}
	
	/**
	 * Dire combien de pions fant�me gentils il reste au joueur
	 * @return le nombre de pions fant�mes gentils restant
	 */
	public int getGoodRemaning(){
		return goodPawns.size();	
	}
	
	/**
	 * Dire combien de pions fant�mes m�chants ils restent au joueur
	 * @return le nombre de 
	 */
	public int getBadRemaning(){
		return badPawns.size();	
	}
	
	public boolean allGoodPawnsSet(){
		return getGoodRemaning() == Parameters.NB_GOOD;
	}
	
	public boolean allBadPawnsSet(){
		return getBadRemaning() == Parameters.NB_BAD;
	}
	
	public String toString(){
		String goodPawnsStr = "";
		String badPawnsStr = "";
		
		for(Good good:goodPawns)
			goodPawnsStr += good.toString() + "\n";
		
		for(Bad bad:badPawns)
			badPawnsStr += bad.toString() + "\n";
			
		return "Nom Joueur : " + this.name + "\n" + 
				"Pions gentils : \n" + 
				goodPawnsStr +
				"Pions m�chant : \n" + 
				badPawnsStr;
	}
}
