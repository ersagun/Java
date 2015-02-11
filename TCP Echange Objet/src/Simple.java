/**
 * Sockets et Flux
 * Flux de chaine de caracteres
 * Flux d'objets
 * @author Ersagun YALCINTEPE
 */

import java.io.Serializable;

public class Simple implements Serializable {
	int entier;

	/**
	 * Notre objet Serializable,la classe Simple et son constructeur
	 */
	public Simple(int i) {
		this.entier = i;
	}
}