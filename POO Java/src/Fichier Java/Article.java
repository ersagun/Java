

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Article implements Produit {
	
		//ATTRIBUTS
	
	/**
	 * Le nom de l'article.
	 */
	private String nom;
	
	/**
	 * Le prix hors taxe de l'article.
	 */
	private double prixHT;
		
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de créer un article.
	 * @param nomArticle Le nom de l'article.
	 * @param prixHt Le prix hors taxe de l'article.
	 */
	public Article(String nomArticle,double prixHt){
		this.nom=nomArticle;
		this.prixHT=prixHt;
	}
	
		//METHODES
	
	/**
	 * Cette méthode retourne le prix TTC de l'article.
	 * @return prix Prix tout compris de l'article.
	 */
	public double getPrixTTC(){
		double prix=(this.prixHT*((this.getTVA()/100)+1));
		return prix;
	}
	
	/**
	 * Cette méthode retourne une chaîne decrivant le prix TTC et le nom de l'article.
	 * @return ("nom : "+this.nom+", prix : "+this.getPrixTTC()).
	 */
	public String toString(){
		return ("nom : "+this.nom+", prix : "+this.getPrixTTC());
	}
	
	/**
	 * Cette méthode retourne la TVA de l'article.
	 * @return TVA_REDUITE TVA de l'article.
	 */
	
	public  double getTVA(){
		return Produit.TVA_REDUITE;
	}
	
	/**
	 * Cette méthode retourne le prix hors taxe de l'article.
	 * @return prixHT Le prix hors taxe de l'article.
	 */
	public double getPrixHT(){
		return this.prixHT;
	}
	
	/**
	 * Cette méthode retourne le nom de l'article.
	 * @return nom Le nom de l'article.
	 */
	public String getNom(){
		return this.nom;
	}
}
