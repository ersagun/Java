

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public abstract class Service implements Produit {
	
		//ATTRIBUT STATIQUE
	
	/**
	 * Identifiant du service.
	 */
	private int identifiant;
	
	/**
	 * Numero de service.
	 */
	private static int nbrService=0;
	
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet d'incr�menter l'identifiant et le nombre de service.
	 */
	public Service(){
			Service.nbrService++;
			this.identifiant=Service.nbrService;
	}
	
		//METHODES
	
	/**
	 * Cette m�thode retourne la TVA du service.
	 * @return Produit.TVA_NORMALE La TVA du service.
	 */
	public double getTVA(){
		return Produit.TVA_NORMALE;
	}
	
	/**
	 * Cette m�thode retourne le nombre de service.
	 * @return Service.nbrService Num�ro de service
	 */
	public int getNbrService(){
		return Service.nbrService;
	}
	
	/**
	 * Cette m�thode retourne le nombre de service.
	 * @return this.identifiant L'identifiant du service.
	 */
	public int getIdentifiant(){
		return this.identifiant;
	}
}
