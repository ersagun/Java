

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Alcool extends Boisson {
	
		// ATTRIBUTS
	
	/**
	 * Le degr� de la boisson alcolis�ee.
	 */
	private double degre;
	
	/**
	 * Le montant d'accise.
	 */
	private double accise;

		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de cr�er une boisson alcolis�e.
	 * @param nomAlcool Le nom de la boisson alcolis�e.
	 * @param prixHt Le prix hors taxe de la boisson alcolis�e.
	 * @param volumeAlcool Le volume de la boisson alcolis�e.
	 * @param degreAlcool Le degr� de la boisson alcolis�e.
	 * @param alcoolAccise Le montant d'accise de la boisson alcolis�e.
	 */
	public Alcool(String nomAlcool, double prixHt, double volumeAlcool, double degreAlcool, double alcoolAccise){
		super(nomAlcool,prixHt, volumeAlcool);
		this.degre=degreAlcool;
		this.accise=alcoolAccise;
	}
	
		//METHODES
	
	
	/**
	 * Cette m�thode retourne le prix TTC de la boisson alcolis�e.
	 * @return prixHT Prix tout compris de la boisson alcolis�e.
	 */
	public double getPrixTTC(){
		return (this.getPrixHT()+this.accise*this.getVolume()*this.degre)*((Produit.TVA_NORMALE/100)+1);
	}
	
	/**
	 * Cette m�thode retourne le TVA de la boisson alcolis�e.
	 * @return TVA_NORMALE TVA de la boisson alcolis�e.
	 */
	public double getTVA(){
		return Produit.TVA_NORMALE;
	}
	
	/**
	 * Cette m�thode retourne une cha�ne decrivant le nom, le prix TTC,le volume, le degr� et le montant d'accise d'une boisson alcolis�e.
	 * @return ("nom : "+this.getNom()+", prix : "+this.getPrixTTC()+", volume : "+this.getVolume()+", degr� : "+this.degre+", accises :"+this.accise)
	 */
	public String toString(){
		return ("nom : "+this.getNom()+", prix : "+this.getPrixTTC()+", volume : "+this.getVolume()+", degr� : "+this.degre+", accises :"+this.accise);
	}
}
