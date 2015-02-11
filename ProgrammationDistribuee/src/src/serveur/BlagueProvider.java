package serveur;

import codebase.Blague;
import codebase.BlagueAbsenteException;
import codebase.BlagueProviderPairApair;
import codebase.InterfaceGraphique;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class BlagueProvider implements BlagueProviderPairApair {

	//Attributs
	
	private String nom;
	private Map<String, Blague> blagueCollection = new HashMap<String, Blague>();
	
	private Map<String, BlagueProviderPairApair> listeRef = new HashMap<String, BlagueProviderPairApair>();
	
	//Constructeur
	
	public BlagueProvider(String n){

        this.nom=n;

	}
	
	//Methodes
	
	public void ajoutBlague(Blague b){this.blagueCollection.put(b.getNom(), b);
    }
	public String getNom() throws RemoteException {return this.nom;}

	public String[] getAllNames(BlagueProviderPairApair bp) throws RemoteException {

		String[] tab=new String[bp.getCollection().keySet().size()];
		int i = 0;
		for (String mapKey : blagueCollection.keySet()) {
			tab[i] = mapKey;
			i++;
		}
		return tab;
	}

	
	public void telechargeBlague(BlagueProviderPairApair bp,String nom) {
        try {
            Blague retourne = bp.getBlague(nom);
            this.blagueCollection.put(retourne.getNom(), retourne);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public Blague getBlague(Object nom) throws RemoteException, BlagueAbsenteException {
		return this.blagueCollection.get(nom);
	}

    public Map getCollection(){
        return this.blagueCollection;
    }

    public Map getListeRef() { return this.listeRef; }


    public static void main(String[]args){

        try {
            if (args.length >= 2) {

                Registry r= LocateRegistry.getRegistry();

                BlagueProvider bp = new BlagueProvider(args[0]);

                //on ajoute des blagues a ce provider
                Blague b1 = new Blague(args[0], "C'est la blague de :"+args[0], "La blague de "+args[0]+" n'est pas marrant");
                bp.ajoutBlague(b1);



                BlagueProviderPairApair bpp = (BlagueProviderPairApair)UnicastRemoteObject.exportObject(bp,0);

                //enregistrer avec sa reference
                            r.rebind(args[0],bpp);

                Scanner sc=new Scanner(System.in);
                sc.nextLine();

                //on remplit la liste de reference de provider courant
                for(int i=1;i<args.length;i++){
                   BlagueProviderPairApair bpint = (BlagueProviderPairApair) r.lookup(args[i]);
                   bp.getListeRef().put(args[i], bpint);
                }

                //On affiche
                for (Iterator<BlagueProviderPairApair> i = bp.getListeRef().values().iterator() ; i.hasNext() ;) {
                    BlagueProviderPairApair blaguep = i.next();
                    System.out.println("Les blagues de : " + blaguep.getNom());

                    for (Object mapKey : blaguep.getCollection().keySet())
                        System.out.println("Le nom de la blague : " + mapKey + ", La blague : " + blaguep.getBlague(mapKey).getNom() + " ,la question : " +
                                blaguep.getBlague(mapKey).getQuestion() + ", la reponse : " + blaguep.getBlague(mapKey).getReponse());
                }
                InterfaceGraphique ig= new InterfaceGraphique(bp.getNom(),bp);


            } else {
                System.out.println("Il faut ecrire des blagues. Minimum 3 args.");
            }
        }catch(Exception e){
            System.out.println("Une erreur s'est produit :");
            e.printStackTrace();
        }
    }
}



