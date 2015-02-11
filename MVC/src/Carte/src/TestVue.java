import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;

import carte.Affiche;
import carte.NoeudAbsentException;
import carte.Reseau;

import controle.Controleur;
import controle.ControleurSelection;
import controle.ReseauSelection;

import vue.VueReseau;
import vue.VueSelection;

/**
 * Classe a pour l'objectif de tester MVC.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class TestVue {

	public static void main(String[] Args) throws NoeudAbsentException {

		Reseau r = Affiche.getReseau();
		ReseauSelection rs = new ReseauSelection(r);
		VueSelection vs = new VueSelection(rs);
		ControleurSelection cs = new ControleurSelection(rs);
		vs.addMouseListener(cs);
		vs.addMouseMotionListener(cs);
		JPanel droite = new JPanel();
		droite.setPreferredSize(new Dimension(320, 480));
		vs.setPreferredSize(new Dimension(320, 480));
		droite.setLayout(new BorderLayout());
		droite.add(vs, BorderLayout.CENTER);

		rs.addObserver(vs);
		rs.permierDessin();

		VueReseau vr = new VueReseau(r);
		Controleur c = new Controleur(vr);
		vs.addMouseListener(c);
		vs.addMouseMotionListener(c);
		JPanel gauche = new JPanel();
		gauche.setPreferredSize(new Dimension(320, 480));
		vr.setPreferredSize(new Dimension(320, 480));
		gauche.setLayout(new BorderLayout());
		gauche.add(vr, BorderLayout.CENTER);
		r.addObserver(vr);
		r.permierDessin();

		TextField textselect = new TextField("Controleur Selectionne");
		TextField textvue = new TextField("VUE");
		textselect.setEditable(false);
		textvue.setEditable(false);
		droite.add(textselect, BorderLayout.NORTH);
		gauche.add(textvue, BorderLayout.NORTH);

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(gauche, BorderLayout.WEST);
		p.add(droite, BorderLayout.EAST);
		p.setPreferredSize(new Dimension(640, 410));

		JFrame fenetre = new JFrame("Reseau");
		fenetre.setContentPane(p);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.requestFocus();
	}
}
