/**
 * Cette classe represente les Bandes Dessinées
 * @author Ersagun YALCINTEPE
 * */

public abstract class BD extends Livre{
	//Attributs
	/**
	 * Le dessinateur de BD
	 * */
	private String dessinateur;
	
	 //Constructeur
	/**
	 * Constructeur cree un bd
	 * @param title
	 * @param scenarist
	 * @param drawer
	 * */
	public BD(String title,String scenarist,String drawer){
		super(title,scenarist);
		this.dessinateur=drawer;
	}
	
	//Methodes
	/**
	 * retourne le nom du dessinateur
	 * @return le nom du dessinateur
	 * */
	public String getDessinateur(){
		return this.dessinateur;
	}
	
	/**
	 * methode affiche le type du livre : BD
	 * @return affiche le type du livre : BD
	 * */
	public String categorie(){
		return("BD");
	}
	/**
	 * retourne la categorie, le numero, le titre, l'auteur du livre, le dessinateur du livre
	 * @return la categorie, le numero, le titre, l'auteur du livre, le dessinateur du livre
	 * */
	public String toString(){
		return(this.categorie()+" ; "+this.getNumero()+" ; "+this.getTitre()+" ; "+this.getAuteur()+" ; "+this.getDessinateur());
	}	
}

