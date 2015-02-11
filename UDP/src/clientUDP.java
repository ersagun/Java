import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class clientUDP{
	public static void main(String[] args){

		if(args.length==3){

			try{

				String ip=args[0];
				int port= Integer.parseInt(args[1]);
				String message=args[2];

				byte[] tab=new byte[message.length()];
				InetAddress a=InetAddress.getByName(ip);
				DatagramPacket p=new DatagramPacket(tab,message.length(),a,port);
				tab=message.getBytes();
				p.setData(tab);
				DatagramSocket s=new DatagramSocket();
				s.send(p);

				byte[] recu=new byte[50];
				DatagramPacket pp=new DatagramPacket(recu,50);
				s.receive(pp);
				byte[]res=new byte[50];
				res=pp.getData();

				String phrase=new String(res);
				System.out.println(phrase);
				s.close();

			}catch(Exception f){
				System.out.println("Erreur de Connexion ! ");
				System.out.println(f.getMessage());
				f.printStackTrace();
			}

		}else{
			System.out.println("Il faut 3 parametre: ip,port,message");
		}
	}
}