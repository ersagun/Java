package algo;

import carte.*;

/**
 * Methode abstraite qui implementera des Algorithmes.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public abstract class Algorithme {

	// Attributs

	public Reseau reseau; // Le reseau sur lequel on utilisera l'Algorithme.
	public Noeud depart; // Le Noeud d'ou l'on veut commencer.
	public Noeud arrivee; // Le Noeud que l'on souhaite atteindre.

	// Methodes

	/**
	 * Permet d'avoir le plus court chemin entre le Noeud depart. et le Noeud
	 * arrivee.
	 * 
	 * @return Le plus court chemin entre le Noeud depart. et le Noeud arrivee.
	 */
	public abstract Chemin plusCourt();

}
