
/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public interface Produit {
	
		// ATTRIBUTS CONSTANT
	
	/**
	 * Tva reduite pour un produit.
	 */
	public final static double TVA_REDUITE=5.5;
	
	/**
	 * Tva normale pour un produit.
	 */
	public final static double TVA_NORMALE=19.6;
	
	
		// METHODES
	
	/**
	 * Cette méthode retourne le prix TTC du produit.
	 * @return prixTTC Prix tout compris du produit.
	 */
	public abstract double getPrixTTC();
	
	/**
	 * Cette méthode retourne la TVA du produit.
	 * @return TVA La TVA du produit.
	 */
	public abstract double getTVA();
	
	/**
	 * Cette méthode retourne une chaîne decrivant le prix TTC du produit.
	 */
	public abstract String toString();

}
