/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Tom Verhoof et Ersagun Yalcintepe
 */

public class Personne 
{
	protected String nom;
	protected int age;
	
	public Personne(int a, String s)
        {
		this.age=a;
		this.nom=s;
	}
        
        /**
         * Lit une personne en donnant l'adresse du fichier .txt
         * @param adresse
         * @return 
         */
	
	public static Personne lire(String adresse)
        {
		int agePersonne;
		String personneNom;
		int iterator=0;
		Personne personne=null;
		//lecture du fichier texte	
		
				try{
					InputStream ips=new FileInputStream(adresse); 
					InputStreamReader ipsr=new InputStreamReader(ips, "UTF-8");
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						personneNom=ligne.substring(4);
						ligne=br.readLine();
						agePersonne=Integer.parseInt(ligne.substring(4));
						personne=new Personne(agePersonne,personneNom);
						
					}
					br.close(); 
					
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
		
				return personne;

	}
        
        /**
         * Lit toutes les personnes en donnant l'adresse du
         * fichier contenant tous .txt.
         * @param adresse
         * @return 
         */
        
        public static Personne[] lireTous(String adresse)
        {
            Personne[] p = new Personne[50];
            
            for (int i = 1; i < 50; i++)
                p[i] = Personne.lire(adresse + "\\F" + (i  + 1) + ".txt");
            
            return p;
        }
	
	public String toString(){
		return this.nom+", "+this.age;
	}
	
	public boolean equals(Personne p){
		boolean val=false;
		if((this.age==p.age)&&(this.nom.equals(p.nom))){
			val=true;
		}
		return val;		
	}
}