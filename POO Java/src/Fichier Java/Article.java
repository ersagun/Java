

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
	 * Ce constructeur permet de cr�er un article.
	 * @param nomArticle Le nom de l'article.
	 * @param prixHt Le prix hors taxe de l'article.
	 */
	public Article(String nomArticle,double prixHt){
		this.nom=nomArticle;
		this.prixHT=prixHt;
	}
	
		//METHODES
	
	/**
	 * Cette m�thode retourne le prix TTC de l'article.
	 * @return prix Prix tout compris de l'article.
	 */
	public double getPrixTTC(){
		double prix=(this.prixHT*((this.getTVA()/100)+1));
		return prix;
	}
	
	/**
	 * Cette m�thode retourne une cha�ne decrivant le prix TTC et le nom de l'article.
	 * @return ("nom : "+this.nom+", prix : "+this.getPrixTTC()).
	 */
	public String toString(){
		return ("nom : "+this.nom+", prix : "+this.getPrixTTC());
	}
	
	/**
	 * Cette m�thode retourne la TVA de l'article.
	 * @return TVA_REDUITE TVA de l'article.
	 */
	
	public  double getTVA(){
		return Produit.TVA_REDUITE;
	}
	
	/**
	 * Cette m�thode retourne le prix hors taxe de l'article.
	 * @return prixHT Le prix hors taxe de l'article.
	 */
	public double getPrixHT(){
		return this.prixHT;
	}
	
	/**
	 * Cette m�thode retourne le nom de l'article.
	 * @return nom Le nom de l'article.
	 */
	public String getNom(){
		return this.nom;
	}
}
