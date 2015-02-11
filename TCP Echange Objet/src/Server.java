/**
 * Sockets et Flux
 * Flux de chaine de caracteres
 * Flux d'objets
 * @author Ersagun YALCINTEPE
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Server {

	/**
	 * La classe main qui execute le programme. Il a pour l'objectif de Envoyer un objet avec l'entier donné en parametre
	 * @param args Premier valeur est un entier qui represente le port a se connecter pour acceder a localhost.
	 */
	public static void main(String args[]) {
		if (args.length == 1) {
			try {
				int port = Integer.parseInt(args[0]);
				ServerSocket socket = new ServerSocket(port);
				Socket s = socket.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				String okok = "";
				while (br.ready()) {
					okok = br.readLine();
					System.out.println(okok);
				}
				OutputStream out = s.getOutputStream();
				PrintWriter pw = new PrintWriter(out);
				pw.write("<HTML>HELLO WORLD !</HTML>");
				pw.flush();

				out.close();
				br.close();
				socket.close();
				s.close();

			} catch (Exception e) {
				System.out.println("Erreur de Connexion ! ");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Il faut 1 parametre: port,");
		}
	}
}
