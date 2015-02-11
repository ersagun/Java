
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
	 * Cette m�thode retourne le prix TTC du produit.
	 * @return prixTTC Prix tout compris du produit.
	 */
	public abstract double getPrixTTC();
	
	/**
	 * Cette m�thode retourne la TVA du produit.
	 * @return TVA La TVA du produit.
	 */
	public abstract double getTVA();
	
	/**
	 * Cette m�thode retourne une cha�ne decrivant le prix TTC du produit.
	 */
	public abstract String toString();

}
