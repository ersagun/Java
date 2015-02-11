

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Alcool extends Boisson {
	
		// ATTRIBUTS
	
	/**
	 * Le degré de la boisson alcoliséee.
	 */
	private double degre;
	
	/**
	 * Le montant d'accise.
	 */
	private double accise;

		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de créer une boisson alcolisée.
	 * @param nomAlcool Le nom de la boisson alcolisée.
	 * @param prixHt Le prix hors taxe de la boisson alcolisée.
	 * @param volumeAlcool Le volume de la boisson alcolisée.
	 * @param degreAlcool Le degré de la boisson alcolisée.
	 * @param alcoolAccise Le montant d'accise de la boisson alcolisée.
	 */
	public Alcool(String nomAlcool, double prixHt, double volumeAlcool, double degreAlcool, double alcoolAccise){
		super(nomAlcool,prixHt, volumeAlcool);
		this.degre=degreAlcool;
		this.accise=alcoolAccise;
	}
	
		//METHODES
	
	
	/**
	 * Cette méthode retourne le prix TTC de la boisson alcolisée.
	 * @return prixHT Prix tout compris de la boisson alcolisée.
	 */
	public double getPrixTTC(){
		return (this.getPrixHT()+this.accise*this.getVolume()*this.degre)*((Produit.TVA_NORMALE/100)+1);
	}
	
	/**
	 * Cette méthode retourne le TVA de la boisson alcolisée.
	 * @return TVA_NORMALE TVA de la boisson alcolisée.
	 */
	public double getTVA(){
		return Produit.TVA_NORMALE;
	}
	
	/**
	 * Cette méthode retourne une chaîne decrivant le nom, le prix TTC,le volume, le degré et le montant d'accise d'une boisson alcolisée.
	 * @return ("nom : "+this.getNom()+", prix : "+this.getPrixTTC()+", volume : "+this.getVolume()+", degré : "+this.degre+", accises :"+this.accise)
	 */
	public String toString(){
		return ("nom : "+this.getNom()+", prix : "+this.getPrixTTC()+", volume : "+this.getVolume()+", degré : "+this.degre+", accises :"+this.accise);
	}
}
