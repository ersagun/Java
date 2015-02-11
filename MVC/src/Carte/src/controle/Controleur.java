package controle;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import vue.VueReseau;
import carte.Arc;
import carte.Noeud;

/**
 * Classe qui contient les methodes de controle.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class Controleur extends MouseInputAdapter {

	// Attributs

	private VueReseau reseau;
	private boolean pressed = false;

	// Constructeur

	/**
	 * Constructeur de Controleur a pour objectif de creer un controleur qui
	 * sera gerer les manipulations souris sur la fenetre.
	 * 
	 * @param r
	 *            VueReseau.
	 */
	public Controleur(VueReseau r) {
		super();
		this.reseau = r;
	}

	// Methodes

	/**
	 * Si mouse est appuyé et fait glissé la methode deplace les noeuds.
	 * 
	 * @param f
	 *            MouseEvent .
	 */
	public void mouseDragged(MouseEvent f) {
		if (SwingUtilities.isLeftMouseButton(f)) {
			this.deplacerNoeud(new Noeud(f.getX(), f.getY()), f.getX(),
					f.getY());

		}
	}

	/**
	 * Prépare le Frame qui contient la VueRéseau pour être affiché.
	 */

	public void chargervue() {
		JFrame toto = new JFrame();
		toto.setTitle("Réseau");
		this.reseau.setPreferredSize(new Dimension(320, 380));
		toto.getContentPane().add(this.reseau);
		toto.pack();
		toto.setVisible(true);
		toto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Methode retourne le reseau de la classe controleur.
	 * 
	 * @return reseau.
	 */
	public VueReseau R() {
		return this.reseau;
	}

	/**
	 * Methode retourne si le noeud choisie ou pas.
	 * 
	 * @return pressed boolean.
	 * 
	 */
	public boolean getPressed() {
		return this.pressed;
	}

	/**
	 * Permet de déplacer un Noeud du réseau.
	 * 
	 * @param n
	 *            Le Noeud que l'on souhaite déplacer.
	 * @param x
	 *            La nouvelle abscisse du Noeud.
	 * @param y
	 *            La nouvelle ordonnée du Noeud.
	 */

	public void deplacerNoeud(Noeud n, int x, int y) {

		try {
			for (int i = 0; i <= reseau.getReseau().getListeNoeuds().size(); i++) {
				Arc arc = new Arc(n, reseau.getReseau().getListeNoeuds().get(i));
				if (arc.getDistance() < 20) {
					reseau.getReseau().deplacer(x, y);
					break;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Vous etes trop loin des noeuds!");
		}
	}
}
