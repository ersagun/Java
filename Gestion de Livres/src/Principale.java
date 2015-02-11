/**
 * Cette classe permet de tester les differentes methodes
 * @author Ersagun YALCINTEPE
 * */
 
public class Principale {
	
	public static void main (String args[]) {
		System.out.println("*******************");
		System.out.println("* Tous les Livres *");
		System.out.println("*******************");
		
		//Création + Affichage des Romans
		
		Roman Miserables= new Roman("Les Misérables", "Victor HUGO");
		System.out.println(Miserables);
		Roman Germinal= new Roman("Gérminal", "Emile ZOLA");
		System.out.println(Germinal);
		Roman Vie = new Roman("Une Vie", "Guy de Maupassant");
		System.out.println(Vie);
		Roman Ami = new Roman("Bel-Ami", "Guy de Maupassant");
		System.out.println(Ami);
		Roman Utopie = new Roman("L'Utopie", "Thomas More");
		System.out.println(Utopie);
		System.out.println("-----------------------------------");
		//Création + Affichage des Comics 
		
		Comics Asterix= new Comics("Asterix et Obelix","René Goscinny", "Albert Uderzo");
		System.out.println(Asterix);
		Comics Spiderman= new Comics("Spiderman", "Stan Lee ", "Steve Ditko ");
		System.out.println(Spiderman);
		Comics Batman= new Comics("Batman","Bob Kane","Adam West");
		System.out.println(Batman);
		Comics Tintin= new Comics("Tintin", "Hergé","Jean-Pierre Talbot");
		System.out.println(Tintin);
		Comics Lanfeust= new Comics("Lanfeust", "Christophe Arleston", "Didier Tarquin");
		System.out.println(Lanfeust);
		System.out.println("-----------------------------------");
		//Création + Affichage des Manga 
		
		Manga Naruto= new Manga("Naruto", "Masashi Kishimoto", "Masashi Kishimot");
		System.out.println(Naruto);
		Manga Satan= new Manga("Satan", "Seishi Kishimoto", "Seishi Kishimoto");
		System.out.println(Satan);
		Manga Berserk= new Manga("Berserk", "Kentaro Miura", "Kentaro Miura");
		System.out.println(Berserk);
		Manga Fruit= new Manga("Fruits basket", "Natsuki Takaya", "Natsuki Takaya");
		System.out.println(Fruit);
		Manga Bleach= new Manga("Bleach","Noriyuki Abe", "Noriyuki Abe");
		System.out.println(Bleach);
		System.out.println("-----------------------------------");
	
	//Création d'un liste
	
	Liste archive=new Liste();
	
	//Adjonction d'un livre dans cette liste
	archive.ajouter(Naruto);
	archive.ajouter(Batman);
	archive.ajouter(Germinal);
	archive.ajouter(Miserables);
	archive.ajouter(Vie);
	archive.ajouter(Ami);
	archive.ajouter(Tintin);
	archive.ajouter(Naruto);
	
	System.out.println("****************");
	System.out.println("** "+archive.getNbElement());
	System.out.println("****************");
	
	}	
}

