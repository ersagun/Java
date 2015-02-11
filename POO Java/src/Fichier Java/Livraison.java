

/**
 * 
 * @author Ersagun YALCINTEPE
 *
 */
public class Livraison extends Service {
	
		//ATTRIBUTS
	/**
	 * La commande à faire livrer.
	 */
	private Commande commande;
	
	/**
	 * La distance de la commande.
	 */
	private double distance;
	
		//CONSTRUCTEUR
	
	/**
	 * Ce constructeur permet de créer une livraison avec une commande donnée à une distance donnée.
	 * @param commandeLivraison Commande à faire livrer.
	 * @param distanceCommande Distance à laquelle rammener la commande.
	 */
	public Livraison(Commande commandeLivraison,double distanceCommande){
		this.commande=commandeLivraison;
		this.distance=distanceCommande;
	}
	
		//METHODES
	
	/**
	 * Cette méthode retourne le prix total que le client doit payer (livraison+commande).
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
	 * Cette méthode retourne une chaîne de caractères qui decrit les informations sur la livraison (total,livraison, les articles a livrer);
	 * @return chaîne.toString() Chaine qui décrit l'etat de livraison 
	 */
	public String toString(){
		StringBuffer chaîne=new StringBuffer();
		chaîne.append("\n"+"Livraison no "+this.getIdentifiant()+" de la commande suivante :");
		chaîne.append("\n"+"------------------------------------");
		chaîne.append("\n"+commande);
		chaîne.append("\n"+"");
		chaîne.append("\n"+"Total : "+this.getPrixTTC());
		chaîne.append("\n"+"Livraison à "+this.distance+" kms");
		return chaîne.toString();
	}
	

	
			
		
	

}
