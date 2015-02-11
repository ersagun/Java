package controle;

import java.util.Observable;

import carte.Arc;
import carte.Noeud;
import carte.Reseau;

/**
 * Un r�seau dont on peut s�lectionner un Noeud.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class ReseauSelection extends Observable {

	// Attributs

	private Reseau reseau;
	private int index;
	public static int indexx;

	// Constructeur

	/**
	 * Cr�e une nouvelle instance de ReseauSelection.
	 * 
	 * @param r
	 *            Le r�seau sur lequel on va s�lectionner un Noeud.
	 */
	public ReseauSelection(Reseau r) {
		super();
		this.reseau = r;
		this.index = -1;
		indexx = -1;
	}

	// Methodes

	/**
	 * Methode selectionne un noeud a un endroit donn�e avec les abcisse x et
	 * ordonnee y.
	 * 
	 * @param x
	 *            abcisse.
	 * @param y
	 *            ordonn�e.
	 */
	@SuppressWarnings("static-access")
	public void selectionne(int x, int y) {
		Noeud clic = new Noeud(x, y);
		try {
			for (int i = 0; i <= reseau.getListeNoeuds().size(); i++) {
				Arc arc = new Arc(clic, reseau.getListeNoeuds().get(i));
				if (arc.getDistance() < 20.0) {
					this.index = i;
					this.indexx = i;
					this.setChanged();
					this.notifyObservers();

					break;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Vous etes trop loin des noeuds!");
		}
	}

	/**
	 * Methode deselectionne le noeud selectionn�.
	 */
	@SuppressWarnings("static-access")
	public void deselectionne() {
		this.index = -1;
		this.indexx = -1;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Methode deplace un noeud sur les abcisse x et ordonn�e y
	 * 
	 * @param x
	 *            L'abscisse de l'endroit.
	 * @param y
	 *            L'ordonn�e de l'endroit.
	 */
	public void deplacer(int x, int y) {
		if (index != -1) {
			try {
				Noeud n = this.reseau.getListeNoeuds().get(this.index);
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
	 * Methode donne acces au reseau.
	 * 
	 * @return reseau Le reseau.
	 */
	public Reseau getReseau() {
		return this.reseau;
	}

	/**
	 * Methode retourne l'index, c'est a dire l'emplacement de noeud
	 * selectionn�.
	 * 
	 * @return index
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Methode affiche premier dessin, donc fait un appel au vue pour 1ere
	 * affichage.
	 */
	public void permierDessin() {
		this.setChanged();
		this.notifyObservers();
	}

}
