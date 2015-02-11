package controle;

import java.util.Observable;

import carte.Arc;
import carte.Noeud;
import carte.Reseau;

/**
 * Un réseau dont on peut sélectionner un Noeud.
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
	 * Crée une nouvelle instance de ReseauSelection.
	 * 
	 * @param r
	 *            Le réseau sur lequel on va sélectionner un Noeud.
	 */
	public ReseauSelection(Reseau r) {
		super();
		this.reseau = r;
		this.index = -1;
		indexx = -1;
	}

	// Methodes

	/**
	 * Methode selectionne un noeud a un endroit donnée avec les abcisse x et
	 * ordonnee y.
	 * 
	 * @param x
	 *            abcisse.
	 * @param y
	 *            ordonnée.
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
	 * Methode deselectionne le noeud selectionné.
	 */
	@SuppressWarnings("static-access")
	public void deselectionne() {
		this.index = -1;
		this.indexx = -1;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Methode deplace un noeud sur les abcisse x et ordonnée y
	 * 
	 * @param x
	 *            L'abscisse de l'endroit.
	 * @param y
	 *            L'ordonnée de l'endroit.
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
	 * selectionné.
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
