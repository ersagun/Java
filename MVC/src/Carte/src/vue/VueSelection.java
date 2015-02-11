package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import carte.Arc;
import controle.ReseauSelection;

/**
 * Classe selectionne un reseau pour afficher.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
@SuppressWarnings("serial")
public class VueSelection extends JPanel implements Observer {

	// Attributs

	private ReseauSelection reseauSelectionne;

	// Constructeur

	/**
	 * Cree une nouvelle instance de VueSelection.
	 * 
	 * @param rs
	 *            Le ReseauSelection que l'on veut voir.
	 */

	public VueSelection(ReseauSelection rs) {
		this.reseauSelectionne = rs;
	}

	// Methodes

	/**
	 * Methode permettre d'afficher les noeuds dessiner dans un panel.
	 * 
	 * @param g
	 *            graphics.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		if (this.reseauSelectionne != null) {
			for (int i = 0; i < this.reseauSelectionne.getReseau()
					.getListeNoeuds().size(); i++) {
				g.setColor(Color.BLUE);
				g.fillOval(this.reseauSelectionne.getReseau().getListeNoeuds()
						.get(i).getX() - 10, this.reseauSelectionne.getReseau()
						.getListeNoeuds().get(i).getY() - 10, 20, 20);
				g.setColor(Color.BLACK);

				if (reseauSelectionne.getIndex() == i) {
					g.setColor(Color.YELLOW);
					g.fillOval(this.reseauSelectionne.getReseau()
							.getListeNoeuds().get(reseauSelectionne.getIndex())
							.getX() - 10, this.reseauSelectionne.getReseau()
							.getListeNoeuds().get(reseauSelectionne.getIndex())
							.getY() - 10, 20, 20);
				}

				for (Arc a : this.reseauSelectionne.getReseau()
						.getListeNoeuds().get(i).getListeArcs()) {
					g.drawLine(a.getOrigine().getX(), a.getOrigine().getY(), a
							.getDestination().getX(), a.getDestination().getY());
				}
			}
		}
	}

	/**
	 * Donne acces au ReseauSelection.
	 * 
	 * @return reseauSelectionne le reseauSelection.
	 */

	public ReseauSelection getReseauSelectionne() {
		return this.reseauSelectionne;
	}

	/**
	 * Methode qui fait la mise a jour de page a chaque apelle a une methode de
	 * la classe modele ReseauSelection.
	 * 
	 * @param b
	 *            observable.
	 * @param arg1
	 *            objectif.
	 */
	public void update(Observable b, Object arg1) {
		this.reseauSelectionne = (ReseauSelection) b;
		this.repaint();
	}

}
