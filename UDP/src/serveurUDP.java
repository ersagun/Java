import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class serveurUDP{
	public static void main(String[] args){
		
		if(args.length==3){
			try{
				int port = Integer.parseInt(args[0]);
				String mes1=args[1];
				String mes2=args[2];

				byte[] tab=new byte[25];
				DatagramPacket p=new DatagramPacket(tab,25);
				DatagramSocket s=new DatagramSocket(port);

				s.receive(p);
				byte[] res=p.getData();

				InetAddress a=p.getAddress();
				int portt=p.getPort();

				String phrase=new String(res);
				int longueur=p.getLength();
				String phrase2=phrase.substring(0,longueur);
				String concatene=mes1+" "+phrase2+" "+mes2;

				byte[] retourne= concatene.getBytes();

				DatagramPacket pp=new DatagramPacket(retourne,concatene.length(),a,portt);
				pp.setData(retourne);
				s.send(pp);
				s.close();

			}catch(Exception e){
				System.out.println("Erreur de Connexion ! ");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			System.out.println("Il faut 3 parametre: port, invite, fin");
		}


	}
}