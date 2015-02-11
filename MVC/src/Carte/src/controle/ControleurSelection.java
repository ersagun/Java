package controle;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class ControleurSelection extends MouseInputAdapter {

	// Attributs

	private ReseauSelection reseauSelected; // reseau selectionné
	private boolean pressed = false; // si un noeud est choisi cela retourne
										// vrai.

	// Constructeur

	/**
	 * Constructeur de ControleurSelection a pour objectif de creer un
	 * controleur de reseau qui va gerer les manipulations de souris dans la
	 * fenetre.
	 * 
	 * @param rs
	 *            ReseauSelection.
	 */
	public ControleurSelection(ReseauSelection rs) {
		super();
		this.reseauSelected = rs;
	}

	// Methodes

	/**
	 * Methode choisi un noeud si le souris est cliqué.
	 * 
	 * @param e
	 *            MouseEvent.
	 */
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			reseauSelected.selectionne(e.getX(), e.getY());
			pressed = true;
		}
	}

	/**
	 * Methode deplace un noeud si le souris est appuyé et fait glissé.
	 * 
	 * @param f
	 *            MouseEvent.
	 */
	public void mouseDragged(MouseEvent f) {
		if (SwingUtilities.isLeftMouseButton(f)) {
			reseauSelected.deplacer(f.getX(), f.getY());

		}
	}

	/**
	 * Methode deselectionne un noeud si le souris est relache.
	 * 
	 * @param g
	 *            MouseEvent.
	 */
	public void mouseReleased(MouseEvent g) {
		if (SwingUtilities.isLeftMouseButton(g)) {
			reseauSelected.deselectionne();
		}
	}

	/**
	 * Methode retourne le reseauSelectionné.
	 * 
	 * @return reseauSelected.
	 */
	public ReseauSelection Rs() {
		return this.reseauSelected;
	}

	/**
	 * Methode retourne un boolean qui precise si le noeud est choisie ou pas.
	 * 
	 * @return pressed.
	 */
	public boolean getPressed() {
		return this.pressed;
	}
}
