package algo;

import carte.Noeud;

/**
 * Contient un Noeud et d'autres element pour aider a effectuer l'algorithme.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */

public class NoeudAlgo {

	// Attributs

	public Noeud n; // Le noeud considere
	public double parcouru; // La distance parcourue pour arriver jusqu'à ce
							// Noeud depuis le depart
	public Noeud parent; // Le noeud parent a ce Noeud.

	// Constructeur

	/**
	 * Cree une nouvelle instance de la classe NoeudAlgo.
	 * 
	 * @param n
	 *            Le noeud considere.
	 * @param parcouru
	 *            La distance parcourue pour arriver jusqu'à ce Noeud depuis le
	 *            depart.
	 * @param parent
	 *            Le noeud parent a ce Noeud.
	 */

	public NoeudAlgo(Noeud n, double parcouru, Noeud parent) {
		this.n = n;
		this.parcouru = parcouru;
		this.parent = parent;
	}

	// Methodes

	/**
	 * donne acces au noeud considere.
	 * 
	 * @return n
	 */

	public Noeud getN() {
		return this.n;
	}

	/**
	 * Donne acces au noeud parcouru.
	 * 
	 * @return parcouru
	 */

	public double getParcouru() {
		return this.parcouru;
	}

	/**
	 * Donne acces au noeud parent.
	 * 
	 * @return parent
	 */

	public Noeud getParent() {
		return this.parent;
	}
}
