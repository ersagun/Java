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

public class PersonneN extends Personne implements Comparable
{
    /**
     * 
     * @param a
     * @param s 
     */
    
    public PersonneN(int a, String s) 
    {
        super(a, s);
    }
    
    /**
     * 
     * @param o
     * @return 
     */

    @Override
    public int compareTo(Object o) 
    {
        try
        {
            Personne other = (Personne)o;
            return this.compareTo(other.nom);
        }
        catch (Exception ex)
        {
            return 0;
        }
    }
    
    /**
         * Lit une personne en donnant l'adresse du fichier .txt
         * @param adresse
         * @return 
         */
	
	public static PersonneN lire(String adresse)
        {
		int agePersonne;
		String personneNom;
		int iterator=0;
		PersonneN personne=null;
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
						personne=new PersonneN(agePersonne,personneNom);
						
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
        
        public static PersonneN[] lireTous(String adresse)
        {
            PersonneN[] p = new PersonneN[50];
            
            for (int i = 0; i < 50; i++)
                p[i] = PersonneN.lire(adresse + "\\F" + (i  + 1) + ".txt");
            
            return p;
        }
}