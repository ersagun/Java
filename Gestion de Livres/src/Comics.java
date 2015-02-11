/**
 * Cette classe represente les Comics
 * @author Ersagun YALCINTEPE
 * */
public class Comics extends BD {
	

	//Constructeur
	/**
	 * Constructeur cree un Manga
	 * @param title
	 * @param scenarist
	 * @param drawer
	 * */
	public Comics(String title,String scenarist,String drawer){
		super(title,scenarist,drawer);
	}
	
	//Methode
	/**
	 * Methode retourne type du livre : Comics
	 * @return type du livre : Comics
	 * */
	public String categorie(){
		return("Comics");
	}
}

