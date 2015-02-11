import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

public class clientTCP{
	public static void main(String[] args){

		if(args.length==3){

			try{

				String ip=args[0];
				int port= Integer.parseInt(args[1]);
				String nomFich=args[2];
				Socket socket=new Socket(ip,port);

				FileInputStream objet= new FileInputStream(nomFich);
				byte[] contenu=new byte[objet.available()];
				System.out.println(objet.available());
				objet.read(contenu);


				socket.getOutputStream().write(contenu);
				objet.close();
				socket.close();
			}catch(Exception f){
				System.out.println("Erreur de Connexion ! ");
				System.out.println(f.getMessage());
				f.printStackTrace();
			}

		}else{
			System.out.println("Il faut 3 parametre: ip,port,file");
		}


	}
}