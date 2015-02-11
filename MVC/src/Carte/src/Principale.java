import carte.Affiche;
import carte.NoeudAbsentException;
import carte.Reseau;
import controle.Controleur;
import vue.VueReseau;

public class Principale {

	/**
	 * Classe a pour l'objectif de tester MVC mais seulement avec un VueReseau
	 * et Controleur.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Reseau r = null;

		try {
			r = Affiche.getReseau();
		} catch (NoeudAbsentException naex) {
			System.out.println(naex.getMessage());
		}

		VueReseau vr = new VueReseau(r);
		r.addObserver(vr);
		Controleur ctr = new Controleur(vr);
		vr.addMouseListener(ctr);
		vr.addMouseMotionListener(ctr);
		ctr.chargervue();

		// Pour modifier les points.
		/**
		 * ctr.deplacerNoeud(r.getListeNoeuds().get(0), 40, 60);
		 * ctr.deplacerNoeud(r.getListeNoeuds().get(1), 35, 80);
		 * ctr.deplacerNoeud(r.getListeNoeuds().get(5), 25, 30);
		 **/

		// Affiche.Afficher(r);
	}
}
