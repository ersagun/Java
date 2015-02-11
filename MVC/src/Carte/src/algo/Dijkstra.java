package algo;

import java.util.ArrayList;

import carte.Noeud;
import carte.Reseau;

/**
 * Classe permettant de trouver le chemin de depart le plus court dans un Reseau
 * de Noeuds.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */

public class Dijkstra extends Algorithme {

	// Attributs

	public ArrayList<NoeudAlgo> ouvert; // Contient l'ensemble des Noeuds qui
										// restent a developper.
	public ArrayList<NoeudAlgo> ferme; // Contient l'ensemble des Noeuds deja
										// developpe.
	public int nbIt; // Le nombre d'iterations de ???
	public double plusCourt; // La plus petite distance jusqu'au Noeud actuel.

	// Constructeur

	public Dijkstra() {
	}

	/**
	 * Cree une nouvelle instance de la classe Dijkstra.
	 * 
	 * @param depart
	 *            Le depart du Chemin.
	 * @param arrivee
	 *            L'arrivee du Chemin.
	 * @param r
	 *            Le reseau par lequel doit passer le Chemin.
	 */

	public Dijkstra(Noeud depart, Noeud arrivee, Reseau r) {
		this.depart = depart;
		this.arrivee = arrivee;
		this.reseau = r;
		this.ouvert = new ArrayList<NoeudAlgo>();
		this.ferme = new ArrayList<NoeudAlgo>();
	}

	// Methodes

	/**
	 * Permet d'avoir le plus court chemin entre le Noeud depart et le Noeud
	 * arrivee.
	 * 
	 * @return Le plus court chemin entre le Noeud depart et le Noeud arrivee.
	 */

	public Chemin plusCourt() {
		// TODO Realise l'algorithme de Dijkstra.

		return null;
	}

	/**
	 * Se charge de construire les listes ouvertes et fermees jusqu'a l'arrivee.
	 */

	public void propagation() {
		assert (ouvert.isEmpty() && ferme.isEmpty()) : "listes non vides...";

		NoeudAlgo n = this.choisir();
		Chemin ch = new Chemin();

		for (NoeudAlgo na : this.ferme)
			ch.ajoutePoint(na.getN());

		/* Recursif */assert new Dijkstra(this.depart, n.getN(), this.reseau)
				.plusCourt().equals(ch);

		// TODO on choisit un Noeud, on le traite, puis on
		// le vire de la liste ouverte.

		for (NoeudAlgo na : this.ferme)
			assert na.getParcouru() < ch.distance() : "Problème pour les distances...";
	}

	/**
	 * Permet de choisir le Noeud a prendre comme chemin.
	 * 
	 * @return Le Noeud a developper ce tour-ci.
	 */

	@SuppressWarnings("null")
	public NoeudAlgo choisir() {
		// TODO pour chaque Noeud du reseau, si le Noeud est relie a celui qu'on
		// traite, on conserve le couple Noeud
		// arrivee/distance, puis on retourne le Noeud arrivee ou distance =
		// min.

		this.nbIt++;
		assert (this.nbIt == ferme.size()) : "Nombre d'iterations differents de ferme.size() ...";

		NoeudAlgo na = null;
		assert na.getParcouru() == plusCourt : "...";

		return na;
	}

	/**
	 * Developpe le Noeud mis en paramètre et fait les traitements adaptes.
	 * 
	 * @param n
	 *            Un Noeud a traiter.
	 */

	public void traiterNoeud(NoeudAlgo n) {
		// TODO si (fermee) ==> rien, si (ouvert) ==> verifie plus faible
		// distance,
		// si (dans aucune liste) ==> ajouter dans liste ouverte.

		// assert n.getParent().getParcouru() + new Arc(n.getN(),
		// n.getParent().getN()).getDistance() == n.getParcouru() :
		// "Problème avec les distances...";
	}

	/**
	 * Donne acces a l'indice du Noeud mis en paramètre dans la liste ouverte.
	 * 
	 * @param n
	 *            Le Noeud mis en paramètre.
	 * @return L'indice du Noeud mis en parmetre s'il a ete trouve, -1 sinon.
	 */

	public int indiceOuvert(Noeud n) {
		// TODO iteration sur la liste ouvert pour
		// TODO trouver l'indice, return -1 si pas trouve.

		for (int i = 0; i < this.ouvert.size(); i++)
			if (this.ouvert.get(i).n.equals(n))
				return i;

		return -1;
	}

	/**
	 * Donne acces a l'indice du Noeud mis en paramètre dans la liste fermee.
	 * 
	 * @param n
	 *            Le Noeud mis en paramètre.
	 * @return L'indice du Noeud mis en parmetre s'il a ete trouve, -1 sinon.
	 */

	public int indiceFerme(Noeud n) {
		// TODO iteration sur la liste ferme pour
		// TODO trouver l'indice, return -1 si pas trouve.

		for (int i = 0; i < this.ferme.size(); i++)
			if (this.ferme.get(i).n.equals(n))
				return i;

		return -1;
	}

	/**
	 * Permet d'avoir le Chemin le plus court.
	 * 
	 * @return le Chemin le plus court.
	 */

	public Chemin construitChemin() {
		Chemin ch = new Chemin();

		// TODO construit le Chemin a partir de la liste fermee.

		assert ch.getPoints().get(0).equals(this.depart) : "Le chemin commence pas au bon endroit.";
		assert ch.getPoints().get(ch.getPoints().size() - 1)
				.equals(this.arrivee) : "Le chemin finit pas au bon endroit.";
		assert ch.distance(this.reseau) == ch.distance() : "Le chemin ne se trouve pas dans le reseau.";

		return null;
	}

	/**
	 * Methode necessaire pour des assertions
	 * 
	 * @return Vrai si la carte est coherente, faux sinon.
	 */

	public boolean carteCoherente() {
		Chemin ch = new Chemin();

		for (NoeudAlgo n : this.ferme)
			ch.ajoutePoint(n.n);

		// if()

		return true;
	}
}
