package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import carte.Arc;
import carte.Reseau;

/**
 * Classe a pour l'objectif d'afficher un reseau.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
@SuppressWarnings("serial")
public class VueReseau extends JPanel implements Observer {

	// Attribut

	public Reseau r;

	// Constructeur

	/**
	 * Constructeur a pour l'objectif de creer un VueReseau qui a etre le vue de
	 * la modele mvc, qui va etre communiquer avec modele.
	 * 
	 * @param r
	 */
	public VueReseau(Reseau r) {
		super();
		this.r = r;
	}

	// Methodes

	/**
	 * Methode permettre d'afficher les noeuds dessiner dans un panel.
	 * 
	 * @param g
	 *            Graphics.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		if (this.r != null) {
			for (int i = 0; i < this.r.getListeNoeuds().size(); i++) {
				g.setColor(Color.BLUE);
				g.fillOval(this.r.getListeNoeuds().get(i).getX() - 10, this.r
						.getListeNoeuds().get(i).getY() - 10, 20, 20);
				g.setColor(Color.BLACK);

				for (Arc a : this.r.getListeNoeuds().get(i).getListeArcs()) {
					g.drawLine(a.getOrigine().getX(), a.getOrigine().getY(), a
							.getDestination().getX(), a.getDestination().getY());
				}
			}
		}
	}

	/**
	 * Methode qui fait la mise a jour de page a chaque apelle a une methode de
	 * la classe modele ReseauSelection.
	 * 
	 * @param o
	 *            observable.
	 * @param arg
	 *            object.
	 */
	public void update(Observable o, Object arg) {
		r = (Reseau) o;
		repaint();

	}

	/**
	 * Methode retourne le reseau.
	 * 
	 * @return r Reseau.
	 */
	public Reseau getReseau() {
		return this.r;
	}

}
