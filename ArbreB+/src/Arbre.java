/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 * Représemte un Arbre B+.
 * @author Verhoof Tom et Yalcintepe Ersagun L3Miage
 *
 * @param <T> Doit implémenter l'interface Comparable.
 */

public class Arbre<T extends Comparable> 
{	
	
    // Attributs

	
    private int idRacine;
    private int ordre;
    private ArrayList<Noeud<T>> feuilles;	

    
    // Propriétés
    
    
    /**
     * Donne acces a un Noeud en donnant son id.
     * @param id
     * @return 
     */

    
    public Noeud<T> getNoeud(int id) 
    { 
        if(id == -1)
            return null;
        else return this.feuilles.get(id); 
    }
	
    /**
     * 
     * @return La racine de cette arbre.
     */
	
    public Noeud<T> getRacine() { return this.feuilles.get(this.idRacine); }
    
    /**
     * 
     * @return Le nombre de feuilles de cet Arbre.
     */
    
    public int NbFeuilles() { return this.feuilles.size(); }
	
    /**
     * Modifie la racine de l'arbre.
     * @param racine
     */
	
    public void setRacine(Noeud<T> racine) { this.idRacine = racine.GetID(); }
	
    /**
     * 
     * @return L'ordre de cet Arbre.
     */
	
    public int getOrdre() { return this.ordre; }
	

    // Constructeur
	
	
    /**
     * Crée une nouvelle instance de la classe Arbre.
     * @param ordre L'ordre de l'arbre (le nombre de valeurs par bloc).
     */

    public Arbre(int ordre) 
    { 
        this.ordre = ordre;
	this.feuilles = new ArrayList<Noeud<T>>();
        this.feuilles.add(new Noeud(this, 0));
        this.idRacine = 0;
    }

    // Méthodes
	
    
    /**
     * Ajoute le Noeud donné en parametre dans cet Arbre (lui donne un id).
     * Mets également a jour l'idParent des enfants du Noeud inséré dans l'arbre.
     * @param n 
     */
    
    public void AjouterNoeud(Noeud<T> n)
    {
        this.feuilles.add(n);
        n.SetID(this.feuilles.size() - 1);
        
        // Mise a jour des idparents des enfants, s'il y en a
        
        if (n.GetPointeurs() != null)
        {
            for(int idx : n.GetPointeurs())
                this.getNoeud(idx).SetIDParent(n.GetID());
        }
    }
	
    /**
     * Insere un objet dans cet Arbre.
     * @param t
     */

    public void insertion(T t) 
    {
    	Noeud n_inserer = this.recherche(t);
    	n_inserer.insertion(t);
    }
	
    /**
     * Retourne le noeud recherché, ou le noeud se trouvant juste à gauche du
     * Noeud recherché si celui-ci n'est pas trouvé (null si cette arbre est vide).
     * @param n
     */

    public Noeud<T> recherche(T t) 
    {
        return this.getRacine().recherche(t);		
    }
	
    /**
     * Supprime, s'il est présent, un objet dans cet Arbre.
     * @param t
     */

    public void suppression(T t) 
    {
    	Noeud n_supprimer = this.recherche(t);
    	n_supprimer.suppression(t);
    }
    
    /**
     * 
     * @return Le taux de remplissage de cet Arbre, en pourcentage.
     */
    
    public double GetTauxRemplissage()
    {
        // taux = (nb_valeurs) / (nb_blocs * ordre)
        
        double nb_noeuds_dispo = 0;
        double nb_valeurs = 0;
        
        for (int i = 0; i < this.feuilles.size(); i++)
            if (this.feuilles.get(i).EstUtilise())
            {
                nb_noeuds_dispo += 1.0 * this.ordre;
                nb_valeurs += this.feuilles.get(i).Remplissage();
            }       
        
        return 100.0 * nb_valeurs / nb_noeuds_dispo;
    }
	
    /**
     * Retourne une représentation en chaines de
     * caractères de cet Arbre.
     * @return
     */
	
    @Override public String toString()
    {
    	return this.getRacine().toString();
    }
}