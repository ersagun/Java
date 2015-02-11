/**
 * Cette classe represente les Mangas
 * @author Ersagun YALCINTEPE
 * */
public class Manga extends BD {
	
	//Constructeur
	/**
	 * Constructeur cree un Manga
	 * @param title
	 * @param scenarist
	 * @param drawer
	 * */
	public Manga(String title,String scenarist,String drawer){
		super(title,scenarist,drawer);
	}
	
	//Methode
	/**
	 * Methode retourne type du livre : Manga
	 * @return type du livre : Manga
	 * */
	public String categorie(){
		return("Manga");
	}
}

