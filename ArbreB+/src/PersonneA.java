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

public class PersonneA extends Personne implements Comparable
{

    /**
     * 
     * @param a
     * @param s 
     */
    
    public PersonneA(int a, String s) 
    {
        super(a, s);
    }

    @Override
    public int compareTo(Object o) 
    {
        try
        {
            Personne other = (Personne)o;
            
            if (this.age > other.age)
                return 1;
            else if (this.age == other.age)
                return 0;
            else return -1;
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
	
	public static PersonneA lire(String adresse)
        {
		int agePersonne;
		String personneNom;
		int iterator=0;
		PersonneA personne=null;
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
						personne=new PersonneA(agePersonne,personneNom);
						
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
        
        public static PersonneA[] lireTous(String adresse)
        {
            PersonneA[] p = new PersonneA[50];
            
            for (int i =0; i < 50; i++)
                p[i] = PersonneA.lire(adresse + "\\F" + (i  + 1) + ".txt");
            
            return p;
        }
}