/**
 * Sockets et Flux
 * Flux de chaine de caracteres
 * Flux d'objets
 * @author Ersagun YALCINTEPE
 */

import java.io.*;
import java.net.*;

public class ServerTcpObjet {
	/**
	 * Le main qui execute le programme. Il a pour l'objectif de Recuperer un objet Simple et afficher sa valeur.
	 * @param args Premier valeur est un entier.
	 */
	public static void main(String[] args) {
		//vérification de l'argmunts
		if (args.length == 1) {
			try {
				int port = Integer.parseInt(args[0]);
				ServerSocket socket = new ServerSocket(port);
				Socket s = socket.accept();
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Simple simple = (Simple) ois.readObject();
				System.out
						.println("La valeur l'attribut entier de la classe simple a de la valeur : "
								+ simple.entier);
				ois.close();
				s.close();
				socket.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erreur de Connexion ! ");
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Il faut 1 parametre: port,");
		}
	}
}