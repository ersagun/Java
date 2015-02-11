

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
	 * Ce constructeur permet d'incrémenter l'identifiant et le nombre de service.
	 */
	public Service(){
			Service.nbrService++;
			this.identifiant=Service.nbrService;
	}
	
		//METHODES
	
	/**
	 * Cette méthode retourne la TVA du service.
	 * @return Produit.TVA_NORMALE La TVA du service.
	 */
	public double getTVA(){
		return Produit.TVA_NORMALE;
	}
	
	/**
	 * Cette méthode retourne le nombre de service.
	 * @return Service.nbrService Numéro de service
	 */
	public int getNbrService(){
		return Service.nbrService;
	}
	
	/**
	 * Cette méthode retourne le nombre de service.
	 * @return this.identifiant L'identifiant du service.
	 */
	public int getIdentifiant(){
		return this.identifiant;
	}
}
