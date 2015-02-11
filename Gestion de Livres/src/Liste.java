/**
 * Cette classe permet de gerer une liste de livres
 * @author Ersagun YALCINTEPE
 * */
import java.io.*;

public class Liste {
	//Attributs
	/**
	 * la tête de la liste
	 * */
	private Maillon tete;
	/**
	 * Nombre de livre dans la liste
	 * */
	private static int nb_element;
	
	//Constructeur
	/**
	 * Constructeur qui cree une liste vide
	 * */
	 public Liste(){
		this.tete=null;
		this.nb_element=0;
	}
	   
	 //Methodes
	 /**
	  * Methode qui ajoute un livre en tete de la liste
	  * 
	  * */
	public void ajouter(Livre l) {
		this.tete=new Maillon(tete,l);
		this.nb_element=this.nb_element+1;
	}
	
/*
	public boolean supprime(String titre){
		While(this.tete.suc!=null){
			nb_element=nb_element-1;
			this.tete.getValeur()=this.tete.suc;
		while(this.tete.valeur.getTitre()!=titre){
			Maillon precedent;
			if(this.tete.suc.valeur.getTitre()==titre){
				nb_element=nb_element-1;
	*/	
	
	public String toString(){
		Maillon m;
		StringBuffer chaine="";
		while(m=tete;m=!null;m=m.suc){
			if(m.valeur instanceof Roman)
				chaine=chaine.append(\n+m.valeur.Roman.toString());
			if(m.valeur instanceof Manga)
				chaine=chaine.append(\n+m.valeur.Manga.toString());
			if(m.valeur instanceof BD)
				chaine=chaine.append(\n+m.valeur.BD.toString());
			if(m.valeur instanceof Comics)
				chaine=chaine.append(\n+m.valeur.Comics.toString());
		}
		String line;
		line=line.toString(chaine);
		return line;
	}
	
	public int getNbElement(){
		return this.nb_element;
	}
	

}

