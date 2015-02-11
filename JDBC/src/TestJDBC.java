/**
 * Created by Ers on 24/03/2014.
 * Cette classe a pour l'objectif de se connecter a une base de données avec JDBC 
 * La classe est un filtre, donc l'utilisateurs ne peuvent pas acceder aux resultats des requetes seulement avec une authentification.
 */

 //importation
import java.sql.*;
import java.util.Scanner;

public class TestJDBC {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws SQLException {
		// l'url qui donne la possibilité de se connecter a la base de données de l'iut
		String url = "jdbc:oracle:thin:@charlemagne:1521:infodb";
		//connection utilisé durant l'execution. Cet attribut est initialisé au debut.
		Connection con = null;
		//statement crée sur la connection. 
		Statement stmt = null;
		//Status de l'utilisateur. Elle prends les valeurs 1 si 
		//c'est un client 2 si c'est un vendeur 
		int status = 0;
		//nom de l'utilisateur
		String nomUt = null;
		//scanner utilisé durant l'execution
		Scanner sc = null;
		//recu par clavier 
		String clavier;
		//resultat de la requete, c'est la requete du client
		ResultSet result;
		//resultat de la requete, c'est la requete généré par le programme 
		//pour verifier si utilisateur existe bien dans la base de données ou non.
		ResultSet resultVerif;
		//c'est la requete sous forme de string, c'est la requete du client modifié 
		//juste avant d'etre envoyé a la base de données. 
		String req;
		//C'est la requete envoyé pour verifier Si l'utilisateur existe ou non.
		String reqVerif;
		//id de l'utilisateur
		int id = 0;
		//mot de passe de l'utilisateur
		String mp = "";
		//verifie si le vendeur est responsable ou non, 0 s'il n'est pas,1 s'il est le responsable.
		int responsable = -1;

		
		// telechargement des drivers pour ce connecter avec JDBC 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName("com.mysql.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException (try): ");
			System.err.println(e.getMessage());
		}
		
 
		try {
			//creation de la connection args0 est le username, args1 est le mot de passe fourni par la bd
			con = DriverManager.getConnection(url, args[0], args[1]);
			System.out
			.println("---------------- Authentification ---------------------");
			//creation de statement
			stmt = con.createStatement();
			//recuperation de status
			System.out.println("Si vous etes client tapez (1), si vous etes vendeur tapez (2)");
			sc = new Scanner(System.in);
			status = sc.nextInt();
			//recuperation de id et mot de passe
			System.out.println("Ecrivez votre id :");
			id = sc.nextInt();
			System.out.println("Ecrivez votre mot de passe :");
			sc = new Scanner(System.in);
			mp = sc.nextLine();
			System.out.println(" ");

			// CLIENT
			if (status == 1) {
				//creation de la requete qui cherche dans la table client s'il existe un client avec le id et mot de passe donné.
				reqVerif = "SELECT * FROM Client WHERE ID=" + id
						+ " AND PASSWORD='" + mp + "'";
				//recuperation de resultset
				resultVerif = stmt.executeQuery(reqVerif);
				//parcours de la resultat et verifier s'il y a un resultat et recuperer le nom de l'utilisateur
				int i = 0;
				while (resultVerif != null && resultVerif.next()) {
					nomUt = resultVerif.getString("Nom");
					i++;
				}
				//Si le client existe
				if (i == 1) {
					//recuperation de la requete du client
					System.out.println("Bonjour " + nomUt
							+ " ecrivez votre requete : ");
					sc = new Scanner(System.in);
					clavier = sc.nextLine();
					System.out.println("votre requete : " + clavier);
					//si la requete est tapé 
					if (clavier != null) {
						//et si elle commence par select
						//modification de la requete et le rendre comme : select IDCmd,Total,IDClient from commande
						if (clavier.startsWith("SELECT")
								|| clavier.startsWith("Select")
								|| clavier.startsWith("select")) {
							String avant = clavier.substring(0,
									clavier.indexOf('*'));
							String apres = clavier.substring(clavier
									.indexOf('*') + 1);
							req = avant + "IDCmd,Total,IDClient" + apres;

							// modification de la requete, pour cacher les commandes qui n'est pas faite par lui
							req = req + " WHERE IDCLIENT=" + id;

								// pour recuperer le resultat de sa requete et l'affichage
								result = stmt.executeQuery(req);
								System.out.println("-----------------------------------------");
								System.out.println("La table Commande:");
								System.out.println("");
								System.out.println("	IDCmd		Total		IDClient	");
								System.out
										.println("	----------	----------	----------");
								while (result != null && result.next()) {
									String idc = result.getString("IDCmd");
									String tot = result.getString("Total");
									String idcli = result.getString("IDClient");
									System.out.println("	" + idc + "		" + tot
											+ "		" + idcli);
								}

							//si elle ne commence pas par select il'a le droit de rien faire d'autre
						} else {
							System.out
									.println("vous n'avez pas le droit de faire une requete autre que select * from commande en etant un client. ");
						}
					} else {
						System.out
								.println("Ecrivez votre requete correctement ");
					}
				} else {
					System.out
							.println(nomUt
									+ "Vous n'etes pas un utilisateur. Mettez votre id et votre mot de passe correct. ");
				}
			}
			// VENDEUR	
			if (status == 2) {
				//creation de la requete qui cherche dans la table de vendeurs s'il existe un vendeur avec le id et mot de passe donné et verifier s'il est responsable ou non.
				reqVerif = "SELECT * FROM Vendeur WHERE IDVendeur=" + id
						+ " AND PASSWORD='" + mp + "'";

				resultVerif = stmt.executeQuery(reqVerif);
				int i = 0;
				while (resultVerif != null && resultVerif.next()) {
					nomUt = resultVerif.getString("Nom");
					responsable = resultVerif.getInt(4);
					i++;
				}
				// s'il existe un vendeur 
				if (i == 1) {

					// VENDEUR NON RESPONSABLE
					if (responsable == 0) {
					//recuperation de la requete du vendeur
											System.out.println("Bonjour " + nomUt
								+ " ecrivez votre requete : ");
						sc = new Scanner(System.in);
						clavier = sc.nextLine();
						System.out.println("Votre requete : " + clavier);
						//si la requete est tapé 
						if (clavier != null) {
						//et si elle commence par select
						//modification de la requete et le rendre comme : select IDCmd,Total,IDClient,IDVendeur from commande
							if (clavier.startsWith("SELECT")
									|| clavier.startsWith("Select")
									|| clavier.startsWith("select")) {

								// modification de la requete pour cacher les autres vente qui est faite.
								String avant = clavier.substring(0,
										clavier.indexOf('*'));
								String apres = clavier.substring(clavier
										.indexOf('*') + 1);
								String fin = avant
										+ "IDCmd,Total,IDClient,IDVendeur"
										+ apres;
								req = fin + " WHERE IDVENDEUR=" + id;

								// pour recuperer le resultat de sa requete et l'affichage
								result = stmt.executeQuery(req);
								System.out.println("-----------------------------------------");
								System.out.println("La table Commande:");
								System.out.println("");
								System.out
										.println("	IDCmd		Total		IDClient	IDVendeur		");
								System.out
										.println("	----------	----------	----------	----------");
								while (result != null && result.next()) {
									String idc = result.getString("IDCmd");
									String tot = result.getString("Total");
									String idcli = result.getString("IDClient");
									String idvend = result
											.getString("IDVendeur");
									System.out.println("	" + idc + "		" + tot
											+ "		" + idcli + "		" + idvend);
								}

							}
						//Si elle commence par update
						//modification de la requete et le rendre comme : update set commande total=x where idcmad=y and idvendeur=id du vendeur
						//le but est de empecher les modification sur les autres commmandes
							if (clavier.startsWith("UPDATE")
									|| clavier.startsWith("Update")
									|| clavier.startsWith("update")) {
								//adjonction de la condition qui permettra de ne pas inserer si cest pas le vendeur de la commande
								req = clavier + " AND IDVENDEUR=" + id;

								// insertion
								result = stmt.executeQuery(req);
								System.out.println("Votre requete : " + clavier
										+ " a eu succes !");

							}
						} else {
							System.out
									.println("Tapez correctement la requete. ");
						}

						// VENDEUR RESPONSABLE
					} else {
						//recuperation de la requete du vendeur
						System.out.println("Bonjour " + nomUt
								+ " vous etes responsable, ecrivez votre requete : ");
						sc = new Scanner(System.in);
						clavier = sc.nextLine();
						System.out.println("Votre requete : " + clavier);
						//si la requete est tapé 
						if (clavier != null) {
						//et si elle commence par select
						//modification de la requete on fait rien. Dans cette situation dans le sujet 
						//il n'etait pas trop claire s'il fallait l'authoriser a faire toute sorte de requete ou non. Moi j'ai directement recupéré sa requete select comme il a le droit de voir tout.
							if (clavier.startsWith("SELECT")
									|| clavier.startsWith("Select")
									|| clavier.startsWith("select")) {

								result = stmt.executeQuery(clavier);
								System.out.println("-----------------------------------------");
								System.out.println("La table Commande:");
								System.out.println("");
								System.out
										.println("	IDCmd		Total		IDClient	IDVendeur		");
								System.out
										.println("	----------	----------	----------	----------");
								// pour recuperer le resultat de sa requete et l'affichage
								while (result != null && result.next()) {
									String idc = result.getString("IDCmd");
									String tot = result.getString("Total");
									String idcli = result.getString("IDClient");
									String idvend = result
											.getString("IDVendeur");
									System.out.println("	" + idc + "		" + tot
											+ "		" + idcli + "		" + idvend);
								}

							}
							//Si elle commence par update
							//rien a changer
							if (clavier.startsWith("UPDATE")
									|| clavier.startsWith("Update")
									|| clavier.startsWith("update")) {

								// inserer
								result = stmt.executeQuery(clavier);
								System.out.println("Votre requete : " + clavier
										+ " a eu succes !");
							}

						} else {
							System.out
									.println("Tapez correctement la requete. ");
						}

					}

					
				} else {
					System.out
							.println(nomUt
									+ "Vous n'etes pas un utilisateur. Mettez votre id,votre mot de passe et votre status correct. ");
				}
			}

			if (status > 3 && status < 1) {
				System.out.println("Erreur choisissez 1 ou 2 pour type d'utilisateur.");
			}

			stmt.close();
			con.close();
			sc.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	}
}