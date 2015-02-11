package codebase;

/**
 * Une Exception lanc�e quand la blague est absente.
 * @author Tom Verhoof et Ersagun Yalcintepe
 *
 */

public class BlagueAbsenteException extends Exception 
{
	
	// Constructeur
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Cree une nouvelle instance de la classe BlagueAbsenteException.
	 */	
	
	public BlagueAbsenteException()
	{
		super("Erreur : la blague est absente.");
	}
	
}
