package carte;

import java.util.ArrayList;
import java.util.Observable;

import controle.ReseauSelection;

/**
 * Représente un réseau composé de noeuds.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class Reseau extends Observable implements Cloneable {

	// Attributs

	private ArrayList<Noeud> listeNoeuds; // La liste des noeuds du réseau

	// Constructeurs

	/**
	 * Crée une nouvelle instance de la classe Réseau.
	 */
	public Reseau() {
		super();
		this.listeNoeuds = new ArrayList<Noeud>();
	}

	/**
	 * Crée un copie profonde d'un réseau déjà existant.
	 * 
	 * @param r
	 *            Le réseau dont on crée une copie.
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
	 * Donne accès à la liste des noeuds du réseau.
	 * 
	 * @return liste des noeuds du réseau.
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
	 * @return Un clone de ce réseau.
	 * @throws CloneNotSupportedException
	 *             si problème au moment du clonage.
	 */
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Methode a pour l'objectif de deplacer les noeuds sur les abcisse et
	 * ordonnées donné en parametre.
	 * 
	 * @param x
	 *            abcisse.
	 * @param y
	 *            ordonnée.
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
