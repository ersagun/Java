
public class Principale {
	public static void main (String[]Args){
				
		// Tableau de produits
		
		Produit [] produits = new Produit[7];
		int i;

		Article couche = new Article("Couches", 15.12);
		Article yaourts = new Article("Yaourts natures", 1.67);
		Article steak = new Article("Steak 200g", 3.43);
		Boisson source = new Boisson("Eau de source", 1.12, 1.50);
		Boisson soda = new Boisson("Cola 2 litres", 2.31, 2.00);
		Alcool biere = new Alcool("Bière 8x25 cl", 4.31, 2.00, 4.5, .108);
		Alcool vodka = new Alcool("Vodka 70 cl", 8.13, .7, 40, .155);

		// On recopie les différents produits dans le tableau
		
		produits[0] = couche;
		produits[1] = yaourts;
		produits[2] = steak;
		produits[3] = source;
		produits[4] = soda;
		produits[5] = biere;
		produits[6] = vodka;

		// On affiche les caractéristiques des différents produits
		
		System.out.println("Caractéristiques des articles\n------------------------------\n");

		for (i = 0; i < 7; i++)
			System.out.println(produits[i]);

		// On affiche les prix des différents articles, en utilisant
		// les méthodes getPrixTTC()
		
		for (i = 0; i < 7; i++)
			System.out.println(produits[i].getPrixTTC());
		
			
		// On fait une commande

		Commande numero1=new Commande();
		numero1.ajoute((Article)produits[0],2);
		numero1.ajoute((Article)produits[1],3);
		numero1.ajoute((Article)produits[2],1);
		numero1.ajoute((Article)produits[5],2);
		
		
		//On fait une autre commande
		
		Commande numero2=new Commande();
		numero2.ajoute((Article)produits[1],1);
		numero2.ajoute((Article)produits[2],3);
		numero2.ajoute((Article)produits[3],12);
		numero2.ajoute((Article)produits[4],8);
		numero2.ajoute((Article)produits[5],5);
		numero2.ajoute((Article)produits[6],2);

		
		// On affiche l'état des commandes

		System.out.println(numero1);
		System.out.println("");
		System.out.println(numero2);
		
		
		// On prépare des livraisons possibles
		
		Livraison L1=new Livraison(numero1,2.0);
		Livraison L2=new Livraison(numero1,35.0);
		Livraison L3=new Livraison(numero2,52.0);
		Livraison L4=new Livraison(numero2,120.0);
		Livraison L5=new Livraison(numero2,12.0);
		

		// On affiche les caractéristiques des livraisons

		System.out.println(L1);
		System.out.println(L2);
		System.out.println(L3);
		System.out.println(L4);
		System.out.println(L5);
	
	}
}