

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Boisson extends Article {
	
		//ATTRIBUTS
	
	/**
	 * Le volume d'une boisson en litre.
	 */
	private double volume; /* en litre */
	
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de créer une boisson.
	 * @param nomBoisson  Le nom de la boisson.
	 * @param prixHt Le prix hors taxe de la boisson.
	 * @param volumeBoisson Le volume de la boisson. Il est en litre.
	 */
	public Boisson(String nomBoisson, double prixHt, double volumeBoisson){
		super(nomBoisson,prixHt);
		this.volume=volumeBoisson;
	}
	
		//METHODES
	
	/**
	 * Cette méthode retourne le prix TTC de la boisson.
	 * @return prixHT Prix tout compris de la boisson.
	 */
	
	public double getPrixTTC(){
		return (this.getPrixHT())*((Produit.TVA_REDUITE/100)+1);
	}
	
	/**
	 * Cette méthode retourne une chaîne decrivant les caracteristiques d'une boisson, le nom, le prix et le volume.
	 */
	public String toString(){
		return ("nom : "+this.getNom()+", prix : "+this.getPrixTTC()+", volume : "+this.volume);
	}
	
	/**
	 * Cette méthode retourne le volume en litre.
	 * @return volume Le volume en litre de la boisson.
	 */
	public double getVolume(){
		return this.volume;
	}
}
