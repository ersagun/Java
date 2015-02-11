

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Commande extends Service {

		//ATTRIBUTS
	
	/**
	 * Le nombre d'articles command�s.
	 */
	private Article []Article;
	
	/**
	 * La quantit� d'articles command�s.
	 */
	private int []quantiteArticle;
	
	/**
	 * Le nombre effectif d'articles.
	 */
	private int nbrEffectifArticle;
	
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de cr�er une table de quantit� et une table d'article � une taille max.
	 */
	public Commande(){
		int max=1000;
		this.quantiteArticle=new int[max];
		this.Article=new Article[max];
		this.nbrEffectifArticle=0;
	}
	
		//METHODES
	
	/**
	 * Cette m�thode ajoute un article dans le tableau d'articles et ajoute sa quantit� dans le tableau de quantit�.
	 * @param art Article � ajouter.
	 * @param quantite La quantit� d'articles � ajouter.
	 */
	public void ajoute(Article art, int quantite){
		this.quantiteArticle[this.nbrEffectifArticle]=quantite;
		this.Article[this.nbrEffectifArticle]=art;
		this.nbrEffectifArticle++;	
	}
	
	/**
	 * Cette m�thode retourne le montant total TTC de la commande.
	 */
	public double getPrixTTC(){
		double montant=0;
		for (int i=0;i<=nbrEffectifArticle-1;i++){
			montant=(montant+(this.Article[i].getPrixTTC()*quantiteArticle[i]));
		}
			return montant;
	}
	
	
	/**
	 * Cette m�thode retourne une cha�ne qui decrit les articles command�s et ses quantit�s.
	 * 
	 */
	public String toString(){
		StringBuffer cha�ne=new StringBuffer();
		cha�ne.append("\n"+"-- Article de la commande no "+getIdentifiant()+" --");
		for (int i=0;i<nbrEffectifArticle;i++){
			cha�ne.append("\n"+quantiteArticle[i]+" x ("+Article[i]+")");
		}
			cha�ne.append("\n"+"-- Fin de la commande no "+getIdentifiant()+", montant : "+this.getPrixTTC()+" --");
	
			return cha�ne.toString();
	}
}

	
