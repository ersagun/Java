package test;

import junit.framework.TestCase;
import algo.Chemin;
import carte.Affiche;
import carte.NoeudAbsentException;
import carte.Reseau;

/**
 * La classe a pour l'objectif de tester les methodes de la classe chemin.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 */

public class TestChemin extends TestCase {

	// Attributs

	private Reseau r;
	private Chemin ch;

	// Méthodes

	/**
	 * Prepare le TestChemin.
	 */

	public void setUp() {
		try {
			this.r = Affiche.getReseau();
			ch = new Chemin();
		} catch (NoeudAbsentException ex) {
		}

	}

	/**
	 * Teste la methode distance.
	 */

	public final void testDistance1() {
		this.setUp();
		ch.ajoutePoint(this.r.getListeNoeuds().get(0));
		ch.ajoutePoint(this.r.getListeNoeuds().get(1));

		if (ch.distance() == 150.0)
			fail("Probleme avec la méthode distance");
	}

	/**
	 * Teste la methode distance.
	 */

	public void testDistance2() {
		this.setUp();
		ch.ajoutePoint(this.r.getListeNoeuds().get(5));
		ch.ajoutePoint(this.r.getListeNoeuds().get(8));

		if (ch.distance() == 200.0)
			fail("Probleme avec la méthode distance");
	}
}
