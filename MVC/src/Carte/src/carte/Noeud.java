package carte;

import java.util.ArrayList;

/**
 * Représente un noeud sur une Carte.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class Noeud {

	// Attributs

	private int x; // l'abscisse du noeud
	private int y; // l'ordonnee du noeud
	private ArrayList<Arc> listeArcs; // Liste des arcs partant de ce noeud

	// Costructeur

	/**
	 * Crée une nouvelle instance de la classe Noeud.
	 * 
	 * @param x
	 *            L'abscisse du noeud.
	 * @param y
	 *            L'ordonnée du noeud.
	 */

	public Noeud(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.listeArcs = new ArrayList<Arc>();
	}

	// Methodes

	/**
	 * Donne accès à l'abscisse de ce noeud.
	 * 
	 * @return L'abscisse de ce noeud.
	 */

	public int getX() {
		return x;
	}

	/**
	 * Modifie l'abscisse de ce noeud.
	 * 
	 * @param x
	 *            La nouvelle abscisse du noeud.
	 */

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Donne accès à l'ordonnée de ce noeud.
	 * 
	 * @return L'ordonnée de ce noeud.
	 */

	public int getY() {
		return y;
	}

	/**
	 * Modifie l'ordonnée de ce noeud.
	 * 
	 * @param y
	 *            La nouvelle ordonnée du noeud.
	 */

	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Donne accès à la liste des arcs partant de ce noeud.
	 * 
	 * @return La liste des arcs partant de ce noeud.
	 */
	public ArrayList<Arc> getListeArcs() {
		return this.listeArcs;
	}

	/**
	 * Permet de créer un arc partant de ce noeud.
	 * 
	 * @param dest
	 *            Le noeud qui a la destination de l'arc crée.
	 */
	public void addArc(Noeud dest) {
		this.listeArcs.add(new Arc(this, dest));
	}

	/**
	 * Observe si deux Noeuds sont identiques (même abscisse et même ordonnée).
	 * 
	 * @param n
	 *            Le noeud à comparer.
	 * @return Vrai si les deux noeuds sont identiques, faux sinon.
	 */
	public boolean equals(Noeud n) {
		return (this.x == n.getX()) && (this.y == n.getY());
	}

}
