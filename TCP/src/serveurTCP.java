import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class serveurTCP{
	public static void main(String[] args){

		if(args.length==2){
			try{
				int port = Integer.parseInt(args[0]);
				String nomEnregistrerFich=args[1];
				FileOutputStream enregistre= new FileOutputStream(nomEnregistrerFich);
				ServerSocket socket=new ServerSocket(port);
				Socket s=socket.accept();
				byte[] recu;
				while(s.getInputStream().available() == 0);
				int i=0;
				while(s.getInputStream().available() !=0){
					recu=new byte[s.getInputStream().available()];
					s.getInputStream().read(recu);
					enregistre.write(recu);
				}
	
				s.getInputStream().close();
				enregistre.close();
				socket.close();
				s.close();

			}catch(Exception e){
				System.out.println("Erreur de Connexion ! ");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			System.out.println("Il faut 2 parametre: port, file");
		}
	}
}