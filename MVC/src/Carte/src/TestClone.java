
import carte.Affiche;
import carte.NoeudAbsentException;
import carte.Reseau;

/**
 * 
 * @author Ersagun YALCINTEPE, Tom VERHOOF S3B.
 * 
 */
public class TestClone {

	/**
	 * Methode main a pour l'objectif de tester si le clonage d'un reseau a bien
	 * ete effectue.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Reseau r = null;
		Reseau r2 = null;
		Reseau r3 = null;

		try {
			r = Affiche.getReseau();
		} catch (NoeudAbsentException naex) {
			System.out.println(naex.getMessage());
		}

		try {
			r2 = (Reseau) r.clone();
		} catch (CloneNotSupportedException cnsex) {
			System.out.println(cnsex.getMessage());
		}

		r3 = new Reseau(r);

		Affiche.Afficher(r2);
		Affiche.Afficher(r3);

	}
}
