

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Livraison extends Service {
	
		//ATTRIBUTS
	/**
	 * La commande � faire livrer.
	 */
	private Commande commande;
	
	/**
	 * La distance de la commande.
	 */
	private double distance;
	
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de cr�er une livraison avec une commande donn�e � une distance donn�e.
	 * @param commandeLivraison Commande � faire livrer.
	 * @param distanceCommande Distance � laquelle rammener la commande.
	 */
	public Livraison(Commande commandeLivraison,double distanceCommande){
		this.commande=commandeLivraison;
		this.distance=distanceCommande;
	}
	
		//METHODES
	
	/**
	 * Cette m�thode retourne le prix total que le client doit payer (livraison+commande).
	 * @return prix_commande Le prix total que le client doit payer (livraison+commande).
	 */
	public double getPrixTTC(){
			double prix_commande=this.commande.getPrixTTC();
			double sup=0;
			if(commande.getPrixTTC()<100){
				sup+=15;
			}
			if(this.distance<=20){
				sup+=0;
			}
				else if(this.distance>20 && this.distance<=50){
					sup+=this.commande.getPrixTTC()*0.05;
				}
					else if(this.distance>50 && this.distance<=100){
						sup+=this.commande.getPrixTTC()*0.10;
					}
						else if(this.distance>100){
							sup+=this.commande.getPrixTTC()*0.15;
						}
							sup=sup*((Produit.TVA_NORMALE/100)+1);
							prix_commande+=sup;
					return prix_commande;
	}
	
	
	/**;
	 * Cette m�thode retourne une cha�ne de caract�res qui decrit les informations sur la livraison (total,livraison, les articles a livrer);
	 * @return cha�ne.toString() Chaine qui d�crit l'etat de livraison 
	 */
	public String toString(){
		StringBuffer cha�ne=new StringBuffer();
		cha�ne.append("\n"+"Livraison no "+this.getIdentifiant()+" de la commande suivante :");
		cha�ne.append("\n"+"------------------------------------");
		cha�ne.append("\n"+commande);
		cha�ne.append("\n"+"");
		cha�ne.append("\n"+"Total : "+this.getPrixTTC());
		cha�ne.append("\n"+"Livraison � "+this.distance+" kms");
		return cha�ne.toString();
	}
	

	
			
		
	

}
