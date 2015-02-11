package carte;

/**
 * Exception levee si un noeud est absent du reseau.
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
@SuppressWarnings("serial")
public class NoeudAbsentException extends Exception {

	// Constructeur

	/**
	 * Cree une nouvelle instance de NoeudAbsentException.
	 * 
	 * @param message
	 *            Le message a  faire afficher.
	 */

	public NoeudAbsentException(String message) {
		super(message);
	}

}
