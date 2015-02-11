package test;

import java.util.ArrayList;

import algo.*;
import carte.*;
import junit.framework.TestCase;

/**
 * La classe a pour l'objectif de tester les methodes de la classe dijkstra.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 */

public class TestDijkstra extends TestCase {
	// Attributs

	private Dijkstra d1;
	private Dijkstra d2;

	// Méthodes

	/**
	 * Prepare les tests.
	 */

	@Override
	protected void setUp() throws Exception {
		d1 = new Dijkstra();
		Reseau r1 = Affiche.getReseau();
		Noeud n1 = r1.getListeNoeuds().get(0);
		Noeud nf = r1.getListeNoeuds().get(4);
		d1.depart = n1;
		d1.arrivee = nf;
		d1.ouvert = new ArrayList<NoeudAlgo>();
		d1.ouvert.add(new NoeudAlgo(n1, 0, null));
		d1.ferme = new ArrayList<NoeudAlgo>();

		d2 = new Dijkstra();
		Reseau r2 = Affiche.getReseau();
		Noeud n2 = r2.getListeNoeuds().get(0);
		Noeud n3 = r2.getListeNoeuds().get(0);
		Noeud n4 = r2.getListeNoeuds().get(0);
		d2.depart = n1;
		d2.arrivee = nf;
		d2.ouvert = new ArrayList<NoeudAlgo>();
		d2.ouvert.add(new NoeudAlgo(n2, 3, n1));
		d2.ouvert.add(new NoeudAlgo(n4, 2, n3));
		d2.ouvert.add(new NoeudAlgo(nf, 3, n3));
		d2.ferme = new ArrayList<NoeudAlgo>();
		d2.ferme.add(new NoeudAlgo(n1, 0, null));
		d2.ferme.add(new NoeudAlgo(n3, 1, null));
	}

	/**
	 * 
	 */

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Teste la methode choisir de Dijkstra.
	 */

	public void testchoisir1() {
		assert d1.choisir() == null;
		assert d2.choisir() == d2.ouvert.get(1);
	}

	/**
	 * Teste la methode indiceOuvert de Dijkstra.
	 */

	public void testindiceOuvert1() {
		assert d1.indiceOuvert(d1.reseau.getListeNoeuds().get(7)) == -1;
		assert d2.indiceOuvert(d2.ouvert.get(1).n) == 1;
	}

	/**
	 * Teste la methode indiceFerme de Dijkstra.
	 */

	public void testindiceFerme1() {
		assert d1.indiceFerme(d1.reseau.getListeNoeuds().get(7)) == -1;
		assert d2.indiceFerme(d2.ferme.get(1).n) == 1;
	}

	/**
	 * 
	 */

	public void testtraiterNoeud1() {
	}

	/**
	 * 
	 */

	public void testpropagation1() {

	}

	/**
	 * 
	 */

	public void testpluscourt1() {

	}

	/**
	 * Teste la methode plus court lorsque le point d'arrivee. n est pas
	 * atteignable.
	 */

	public void testpluscourt2() {
		try {
			d1.arrivee = Affiche.getReseau().getListeNoeuds().get(10);
		} catch (NoeudAbsentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert d1.plusCourt() == null;
	}
}
