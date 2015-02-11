package carte;

import java.util.ArrayList;
import java.util.Observable;

import controle.ReseauSelection;

/**
 * Repr�sente un r�seau compos� de noeuds.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class Reseau extends Observable implements Cloneable {

	// Attributs

	private ArrayList<Noeud> listeNoeuds; // La liste des noeuds du r�seau

	// Constructeurs

	/**
	 * Cr�e une nouvelle instance de la classe R�seau.
	 */
	public Reseau() {
		super();
		this.listeNoeuds = new ArrayList<Noeud>();
	}

	/**
	 * Cr�e un copie profonde d'un r�seau d�j� existant.
	 * 
	 * @param r
	 *            Le r�seau dont on cr�e une copie.
	 */
	public Reseau(Reseau r) {
		this.listeNoeuds = new ArrayList<Noeud>();
		Noeud n;
		for (int i = 0; i < r.listeNoeuds.size(); i++) {
			n = r.listeNoeuds.get(i);
			this.listeNoeuds.add(new Noeud(n.getX(), n.getY()));
		}
		int j;
		for (int i = 0; i < r.listeNoeuds.size(); i++) {
			n = r.listeNoeuds.get(i);
			for (Arc a : n.getListeArcs()) {
				j = r.listeNoeuds.indexOf(a.getDestination());
				this.listeNoeuds.get(i).addArc(this.listeNoeuds.get(j));
			}
		}
	}

	// Methodes

	/**
	 * Ajoute un noeud au reseau.
	 * 
	 * @param n
	 *            noeud a rajouter.
	 */
	public void addNoeud(Noeud n) {
		this.listeNoeuds.add(n);
		setChanged();
		notifyObservers();
	}

	/**
	 * Donne acc�s � la liste des noeuds du r�seau.
	 * 
	 * @return liste des noeuds du r�seau.
	 */
	public ArrayList<Noeud> getListeNoeuds() {
		return this.listeNoeuds;
	}

	/**
	 * Ajoute un arc au reseau avec des noeuds de dest et noeud d'origine au
	 * parametre.
	 * 
	 * @param origine
	 *            noeud d'origine.
	 * @param destination
	 *            noeud de destination.
	 */
	public void addArc(Noeud origine, Noeud destination)
			throws NoeudAbsentException {

		if (!listeNoeuds.contains(origine)
				&& (!listeNoeuds.contains(destination)))
			throw new NoeudAbsentException(
					"Erreur ! Il n'y a pas de noeud dans cette endroit.");

		origine.addArc(destination);
		destination.addArc(origine);
		setChanged();
		notifyObservers();
	}

	/**
	 * Methode a pour l'objectif de cloner l'objet.
	 * 
	 * @return Un clone de ce r�seau.
	 * @throws CloneNotSupportedException
	 *             si probl�me au moment du clonage.
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Methode a pour l'objectif de deplacer les noeuds sur les abcisse et
	 * ordonn�es donn� en parametre.
	 * 
	 * @param x
	 *            abcisse.
	 * @param y
	 *            ordonn�e.
	 */
	public void deplacer(int x, int y) {
		if (ReseauSelection.indexx != -1) {
			try {
				Noeud n = this.getListeNoeuds().get(ReseauSelection.indexx);
				n.setX(x);
				this.setChanged();
				this.notifyObservers();
				n.setY(y);
				this.setChanged();
				this.notifyObservers();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	/**
	 * Methode a pour l'objectif de faire le premier dessin autrement dit faire
	 * appel au vue 1 ere fois.
	 */
	public void permierDessin() {
		this.setChanged();
		this.notifyObservers();
	}
}
