/**
 * Cette classe represente la maillon
 * @author Ersagun YALCINTEPE
 * */


public class Maillon {
		
	//Attributs
	/**
	* la tête de la liste
	* */
	private Livre valeur;
	
	/**
	 * suivant dans la liste
	 * */
	private Maillon suc;
	
	//Constructeur
	/**
	 * Constructeur qui creer un maillon pour le livre ajouté dans la liste.
	 * @param suivant le maillon suivant
	 * @param stocker le livre a stocker
	 * */
	 public Maillon(Maillon suivant, Livre stocker){
		 this.valeur=stocker;
		 this.suc=suivant;
	 }
	 
	 //Methodes
	 /**
	  * Methode retourne le livre que la maillon contient
	  * @return le livre que la maillon contient
	  * */
	 public Livre getValeur(){
		 return this.valeur;
	 }
	 
	 /**
	  * Methode retourne le prochaine maillon;
	  * @return prochaine maillon
	  * */ 
	 public Maillon getMaillon(){
		 return this.suc;
	 }
	 
	 
		 
}

