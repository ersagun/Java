

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Commande extends Service {

		//ATTRIBUTS
	
	/**
	 * Le nombre d'articles commandés.
	 */
	private Article []Article;
	
	/**
	 * La quantité d'articles commandés.
	 */
	private int []quantiteArticle;
	
	/**
	 * Le nombre effectif d'articles.
	 */
	private int nbrEffectifArticle;
	
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de créer une table de quantité et une table d'article à une taille max.
	 */
	public Commande(){
		int max=1000;
		this.quantiteArticle=new int[max];
		this.Article=new Article[max];
		this.nbrEffectifArticle=0;
	}
	
		//METHODES
	
	/**
	 * Cette méthode ajoute un article dans le tableau d'articles et ajoute sa quantité dans le tableau de quantité.
	 * @param art Article à ajouter.
	 * @param quantite La quantité d'articles à ajouter.
	 */
	public void ajoute(Article art, int quantite){
		this.quantiteArticle[this.nbrEffectifArticle]=quantite;
		this.Article[this.nbrEffectifArticle]=art;
		this.nbrEffectifArticle++;	
	}
	
	/**
	 * Cette méthode retourne le montant total TTC de la commande.
	 */
	public double getPrixTTC(){
		double montant=0;
		for (int i=0;i<=nbrEffectifArticle-1;i++){
			montant=(montant+(this.Article[i].getPrixTTC()*quantiteArticle[i]));
		}
			return montant;
	}
	
	
	/**
	 * Cette méthode retourne une chaîne qui decrit les articles commandés et ses quantités.
	 * 
	 */
	public String toString(){
		StringBuffer chaîne=new StringBuffer();
		chaîne.append("\n"+"-- Article de la commande no "+getIdentifiant()+" --");
		for (int i=0;i<nbrEffectifArticle;i++){
			chaîne.append("\n"+quantiteArticle[i]+" x ("+Article[i]+")");
		}
			chaîne.append("\n"+"-- Fin de la commande no "+getIdentifiant()+", montant : "+this.getPrixTTC()+" --");
	
			return chaîne.toString();
	}
}

	
