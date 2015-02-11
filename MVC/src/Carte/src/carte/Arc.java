package carte;

/**
 * Represente un Arc entre 2 noeuds.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B
 * 
 */
public class Arc {

	// Attributs

	private Noeud origine; // Noeud origine de l'arc
	private Noeud dest; // Noeud destination de l'arc

	// Constructeur

	/**
	 * Constructeur Arc
	 * 
	 * @param origine
	 *            noeud origine
	 * @param destination
	 *            noeud destination
	 */
	public Arc(Noeud origine, Noeud destination) {
		super();
		this.origine = origine;
		this.dest = destination;
	}

	// Methodes

	/**
	 * Observe si deux Arcs sont identiques (meme dest et meme origine).
	 * 
	 * @param other
	 *            L'Arc a  comparer.
	 * @return Vrai si les deux arcs sont identiques, faux sinon.
	 */

	public boolean equals(Arc other) {
		return ((this.dest.equals(other.dest)) && (this.origine
				.equals(other.origine)));
	}

	/**
	 * Calcule la longueur de l'arc avec la distance euclidienne.
	 * 
	 * @return La longueur de l'arc.
	 */

	public double getDistance() {
		return Math.sqrt(Math.pow(this.origine.getX() - this.dest.getX(), 2)
				+ Math.pow(this.origine.getY() - this.dest.getY(), 2));
	}

	/**
	 * Donne acces au noeud a  l'arrivee de l'arc.
	 * 
	 * @return le noeud a  l'arrivee de l'arc.
	 */
	public Noeud getDestination() {
		return this.dest;
	}

	/**
	 * retourne le noeud d'origine
	 * 
	 * @return origine
	 */
	public Noeud getOrigine() {
		return this.origine;
	}

}
