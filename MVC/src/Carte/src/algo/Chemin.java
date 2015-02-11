package algo;

import carte.*;

import java.util.ArrayList;

/**
 * Represente le chemin entre des Noeuds.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class Chemin {

	// Attributs

	public ArrayList<Noeud> points; // La liste des noeuds sur le chemin

	// Constructeur

	/**
	 * Cree une nouvelle instance de la classe Chemin.
	 */

	public Chemin() {
		this.points = new ArrayList<Noeud>();
	}

	// Methodes

	/**
	 * Ajoute un point sur le Chemin.
	 * 
	 * @param n
	 *            Le noeud dont les coordonnees representent le point.
	 */

	public void ajoutePoint(Noeud n) {
		this.points.add(n);
	}

	/**
	 * Permet de calculer la distance du Chemin. (Pour l'instant on suppose que
	 * les arcs entre. les noeuds existent).
	 * 
	 * @return La distance en passant par chacun des points.
	 */

	public double distance() {
		double distance = 0.0;

		for (int i = 0; i < this.points.size() - 1; i++) {
			Arc arc = new Arc(this.points.get(0), this.points.get(i));
			distance += arc.getDistance();
		}

		return distance;
	}

	/**
	 * Calcule la distance du Chemin en verifiant qu'un chemin existe.
	 * 
	 * @param r
	 *            Le reseau sur lequel on veut construire le chemin.
	 * @return La distance en passant par chacun des points si le chemin existe,
	 *         -1 sinon.
	 */

	public double distance(Reseau r) {
		if (this.points.size() > 0)
			if (!r.getListeNoeuds().contains(this.points.get(0)))
				return -1;

		for (int i = 0; i < this.points.size() - 1; i++) {
			if (!r.getListeNoeuds().contains(this.points.get(i + 1)))
				return -1;
			if (!this.points
					.get(i)
					.getListeArcs()
					.contains(
							new Arc(this.points.get(i), this.points.get(i + 1))))
				return -1;
		}

		return this.distance();
	}

	/**
	 * Observe si deux Chemins sont identiques (meme points).
	 * 
	 * @param ch
	 *            Le chemin à comparer.
	 * @return Vrai si les deux chemins sont identiques, faux sinon.
	 */

	public boolean equals(Chemin ch) {
		if (ch.points.size() != this.points.size())
			return false;

		for (int i = 0; i < this.points.size(); i++)
			if (!this.points.get(i).equals(ch.points.get(i)))
				return false;

		return true;
	}

	/**
	 * Donne acces aux points du Chemin.
	 * 
	 * @return Les points du Chemin.
	 */

	public ArrayList<Noeud> getPoints() {
		return this.points;
	}

}
