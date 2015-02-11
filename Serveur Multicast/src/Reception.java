	
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

//La classe a pour l'objectif de recuperer les 
//messages recu par socket multicast, classe est un 
//thread donc recupere les conversations en pararlele
	public class Reception extends Thread{
		private MulticastSocket mc;

		public Reception(MulticastSocket e){
			this.mc=e;
		}

		public void run(){

			try{
				while(true){
					byte[] buf = new byte[250];
					DatagramPacket recv = new DatagramPacket(buf, buf.length);
					this.mc.receive(recv);
					buf=recv.getData();

					String recu= new String(buf);

				int longueur=recv.getLength();
				String phrase2=recu.substring(0,longueur);
				
					System.out.println(phrase2);
					
					System.out.print(": ");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}