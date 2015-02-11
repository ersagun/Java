/**
 * Cette classe represente les livres 
 * @author Ersagun YALCINTEPE
 * */
public abstract class Livre{
	
	//Attributs
	/**
	 * Le titre du livre
	 * */
	private String titre;
	/**
	 * l'auteur du livre
	 * */
	private String auteur;
	/**
	 * Compteur de l'identifiant 
	 * */
	public static int num=0;
	/**
	 * l'identifiant stable du livre et unique du livre
	 * */
	 private int numero;
	 	
	//Constructeur
	/**
	 * Constructeur qui cree un livre et le donne un identifiant unique
	 * @param title titre du livre
	 * @param author auteur du livre
	 *
	 * */
	public Livre(String title, String author){
		this.titre=title;
		this.auteur=author;
		this.num=this.num+1;
		this.numero=num;
	}
	
	//Methodes
	/**
	 * type de livre
	 * */
	public abstract String categorie();
	
	/**
	 * Methode retourne la categorie, le numero, le titre, l'auteur du livre
	 * @return retourne la categorie, le numero, le titre, l'auteur du livre
	 * */
	public String toString(){
		return(this.categorie()+" ; "+this.getNumero()+" ; "+this.getTitre()+" ; "+this.getAuteur());
	}	
	
	//Methodes Accés
	/**
	 * Methode retourne le titre du livre
	 * @return le titre du livre
	 * */
	public String getTitre(){
		return this.titre;
	}
	/**
	 * Methode retourne l'auteur du livre
	 * @return l'auteur du livre
	 * */
	public String getAuteur(){
		return this.auteur;
	}
	/**
	 * Methode retourne l'identifiant unique du livre
	 * @return l'identifiant du livre
	 * */
	public int getNumero(){
		return this.numero;
	}
	
	
}

