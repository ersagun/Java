import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

//Cette classe a pour l'objectif de realiser un serveur-client multicast.
public class Serv{
	public static void main(String[] args ){
		if(args.length==3){
			try{
				String adresseip=args[0];
				String nom=args[2];

				Scanner sc= new Scanner(System.in);
				MulticastSocket s = new MulticastSocket(Integer.parseInt(args[1]));
				InetAddress group = InetAddress.getByName(adresseip);
				s.joinGroup(group);
				Reception recu=new Reception(s);
				recu.start();

				while(true){
					System.out.print(": ");
					String msg= nom + " dit :" + sc.nextLine();
					byte[] bu=msg.getBytes();
					DatagramPacket hi = new DatagramPacket(bu, bu.length,
						group, Integer.parseInt(args[1]));
					s.send(hi);
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("Il faut 3 parametres, adresse, port, username ");
		}

	}
}
