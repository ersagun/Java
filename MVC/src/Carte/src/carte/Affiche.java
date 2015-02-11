package carte;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import carte.Arc;
import carte.Noeud;
import carte.NoeudAbsentException;
import carte.Reseau;

/**
 * Classe a ete recuperer sur ent.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
// permet d'afficher un réseau
@SuppressWarnings("serial")
class JPanelReseau extends JPanel {

	// Attributs

	public Reseau r;

	// Methodes

	/**
	 * Methode permettre d'afficher les noeuds dessiner dans un panel.
	 * 
	 * @param g
	 *            graphics.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		ArrayList<Noeud> l = r.getListeNoeuds();

		// affichage par noeud
		for (int i = 0; i < l.size(); i++) {
			// affiche le noeud
			g.drawOval(l.get(i).getX() - 5, l.get(i).getY() - 5, 10, 10);
			// affiche les arcs
			ArrayList<Arc> a = l.get(i).getListeArcs();
			for (int j = 0; j < a.size(); j++) {
				g.drawLine(l.get(i).getX(), l.get(i).getY(), a.get(j)
						.getDestination().getX(), a.get(j).getDestination()
						.getY());
			}

		}
	}

}

// la classe publique
public class Affiche {

	// methode qui creer la JFrame et lie le JPanel au réseau
	/**
	 * Methode qui creer la JFrame et lie le JPanel au réseau
	 * 
	 * @param r
	 *            reseau a afficher
	 */
	static public void Afficher(Reseau r) {
		// creation de la JFrame
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// creation JPanel
		JPanelReseau p = new JPanelReseau();
		p.setPreferredSize(new Dimension(400, 400));
		f.getContentPane().add(p);
		f.pack();

		// on met à jour le reseau du panel
		p.r = r;

		// on affiche tout
		f.setVisible(true);
		p.repaint();

	}

	// methode qui retourne un réseau par défaut

	/**
	 * Methode aui retourne un reseau par defaut
	 * 
	 * @return Reseau r
	 * @throws NoeudAbsentException
	 */
	public static Reseau getReseau() throws NoeudAbsentException {
		Reseau r = new Reseau();
		Noeud[][] t = new Noeud[5][5];

		// creation des noeuds
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				t[i][j] = new Noeud(i * 50 + 50, j * 50 + 50);
			}

		r.addNoeud(t[0][0]);
		r.addNoeud(t[3][0]);
		r.addNoeud(t[1][0]);
		r.addNoeud(t[2][0]);
		r.addNoeud(t[4][0]);
		r.addNoeud(t[2][1]);
		r.addNoeud(t[3][1]);
		r.addNoeud(t[4][1]);
		r.addNoeud(t[1][2]);
		r.addNoeud(t[4][2]);
		r.addNoeud(t[0][3]);
		r.addNoeud(t[2][3]);
		r.addNoeud(t[3][3]);
		r.addNoeud(t[0][4]);
		r.addNoeud(t[2][4]);
		r.addNoeud(t[4][4]);

		r.addArc(t[0][0], t[0][3]);
		r.addArc(t[0][0], t[1][0]);
		r.addArc(t[1][0], t[1][2]);
		r.addArc(t[1][0], t[2][0]);
		r.addArc(t[2][0], t[2][1]);
		r.addArc(t[2][0], t[3][0]);
		r.addArc(t[3][0], t[3][1]);
		r.addArc(t[3][0], t[4][0]);
		r.addArc(t[4][0], t[4][1]);
		r.addArc(t[3][1], t[4][1]);
		r.addArc(t[4][1], t[4][2]);
		r.addArc(t[1][2], t[4][2]);
		r.addArc(t[4][2], t[4][4]);
		r.addArc(t[0][3], t[0][4]);
		r.addArc(t[0][3], t[2][3]);
		r.addArc(t[2][3], t[3][3]);
		r.addArc(t[2][3], t[2][4]);
		r.addArc(t[2][4], t[4][4]);
		return r;
	}
}
