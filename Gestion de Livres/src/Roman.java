/**
 * Cette classe represente les romans
 * @author Ersagun YALCINTEPE
 * */

public class Roman extends Livre {
	
	//Constructeur
	/**
	 * Constructeur qui cree un roman
	 * @param title titre du roman
	 * @param author auteur du roman
	 * */
	public Roman(String title, String author){
		super(title,author);		
	}
	
	//Methode
	/**
	 * methode affiche le type du livre : Roman
	 * @return affiche le type du livre : Roman
	 * */
	public String categorie(){
		return("Roman");
	}
}

