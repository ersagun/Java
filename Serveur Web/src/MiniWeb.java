import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.io.PrintWriter;


public class MiniWeb{

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
				String fichier="";
				String fich="";
				while(s.getInputStream().available() == 0);
				while (br.ready()) {
					okok = br.readLine();
					System.out.println(okok);
					if(okok.contains("GET")){
						fichier = okok.substring(okok.lastIndexOf(" ",okok.length()));
						nom=okok.substring(index("/"),index(".html"))
					}
					System.out.println(fichier);
				}

				
					fich=MiniWeb.lireFichier("index.html");
				

				if(fichier=="MiniWeb.html"){
					fich=MiniWeb.lireFichier("MiniWeb.html");
				}
				if(fichier=="MiniWeb.html"){
					fich=MiniWeb.lireFichier("MiniWeb.html");
				}


				OutputStream out = s.getOutputStream();
				PrintWriter pw = new PrintWriter(out);
				pw.write(fich);
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
	public static String lireFichier(String f) throws Exception{
        //lire le fichier index.html
		String fichier="",ch;
		BufferedReader brf=new BufferedReader(new FileReader(f));
		while((ch=brf.readLine())!=null){
			fichier=fichier+ch;
		}

        return fichier;
    }
}