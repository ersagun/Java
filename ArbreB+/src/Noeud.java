/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 * Représente une feuille d'un Arbre.
 * @author Verhoof Tom et Yalcintepe Ersagun L3Miage
 *
 * @param <T> Doit implémenter l'interface Comparable.
 */

public class Noeud<T extends Comparable> 
{
	
    // Attributs
	
	
    public Arbre<T> arbre;    
    private int id;
    private int idParent;
    private ArrayList<Integer> pointeurs;
    private boolean utilise;
    private ArrayList<T> valeurs;
	
	
    // Propriétés
	
	
    /**
     * 
     * @return Vrai si ce Noeud est la racine de l'Arbre dans lequel ce Noeud se trouve, faux sinon.
     */
	
    public boolean EstRacine() { return this.id == this.arbre.getRacine().id; }
    
    /**
     * 
     * @return Vrai si ce Noeud est une feuille externe, faux sinon.
     */
	
    public boolean EstUneFeuille() { return this.pointeurs == null; }
    
    /**
     * 
     * @return Vrai si le Noeud sert a quelque chose dans l'Arbre, faux sinon.
     */
    
    public boolean EstUtilise() { return this.utilise; }
    
    /**
     * 
     * @return l'ID de ce Noeud.
     */
    
    public int GetID() { return this.id; }
    
    /**
     * 
     * @return la liste des pointeurs de ce Noeud.
     */
    
    public ArrayList<Integer> GetPointeurs() { return this.pointeurs; }
    
    /**
     * 
     * @return Le Noeud parent de ce Noeud, le cas échéant (null sinon).
     */
    
    public Noeud<T> NoeudParent() 
    { 
        Noeud<T> n =  this.arbre.getNoeud(this.idParent);
        return n;
    }
	
    /**
     * 
     * @return Le nombre de valeurs dans ce Noeud.
     */	
	
    public int Remplissage() { return this.valeurs.size(); }
    
    /**
     * Modifie l'identifiant de ce Noeud.
     * @param nv_id 
     */
    
    public void SetID(int nv_id) { this.id = nv_id; }
    
    /**
     * Modifie l'identifiant du parent de ce Noeud.
     * @param nv_id 
     */
    
    public void SetIDParent(int nv_id) { this.idParent = nv_id; }
		
	
    // Constructeur
	
	
    /**
     * Crée une nouvelle instance de la classe Noeud.
     * @param arbre
     * @param id Non utilisé, mettre 0.
     */
	
    public Noeud(Arbre arbre, int id) 
    { 
        this.arbre = arbre;
	this.valeurs = new ArrayList<T>(arbre.getOrdre());
        this.idParent = -1;
        this.utilise = true;
    }
	
	
    // Méthodes
	
	
    /**
     * Fusionne un Noeud spécifié dans ce Noeud.
     * @param n2 Le Noeud spécifié.
     */
	
    private void fusion(Noeud<T> n2)
    {
        // Ordronnement des nouvelles valeurs dans le Noeud. (fusion par la gauche ou par la droite)
        
        if ((this.valeurs.isEmpty()) || (this.valeurs.get(0).compareTo(n2.valeurs.get(0)) == 1))
        {
            // Fusion par la gauche
            
            this.valeurs.addAll(0, n2.valeurs);
            n2.utilise = false;
            
            if (this.pointeurs != null)                
            {
                this.pointeurs.addAll(0, n2.pointeurs);
            }
            
            Noeud<T> n = n2.NoeudParent();
            
            // Suppression de la valeur.
            
            for (int i = 0; i < this.NoeudParent().pointeurs.size(); i++)
                if (this.id == this.NoeudParent().pointeurs.get(i))
                {        
                    T asupprimer = this.NoeudParent().valeurs.get(i - 1);
                    
                    // Suppression du pointeur
                    
                    n.pointeurs.remove(n.pointeurs.indexOf(n2.id));
                    
                    // Suppression de la valeur.
                    this.NoeudParent().suppression(asupprimer);
                    break;
                }
        }
        else
        {
            // Fusion par la droite.
            
            this.valeurs.addAll(n2.valeurs);
            n2.utilise = false;
            Noeud<T> n = this.NoeudParent();
            
            T asupprimer = this.NoeudParent().valeurs.get(0);
            
            // Suppression du pointeur
            
            n.pointeurs.remove(n.pointeurs.indexOf(n2.id));
            
            // Fusionner par la droite ==> on est tout a gauche du noeud fils, on peut donc supprimer la premiere valeur.
            
            this.NoeudParent().suppression(asupprimer);            
        }
	
        // Spliter le Noeud qui vient d'être fusionné ?
        
	if ((this.Remplissage()) > this.arbre.getOrdre())
            this.split(true);
    }
    
    /**
     * Récupere le Noeud ou bloc juste a gauche de ce Noeud, s'il existe.
     * On ne remonte pas au-dela du premier parent.
     * @return 
     */
    
    public Noeud getNoeudaGauche()
    {
        Noeud<T> n;
        int indiceparent = 0;
        
        if (this.NoeudParent() == null)
            return null;
        
        for (int i = 0; i < this.NoeudParent().pointeurs.size(); i++)
        {
            if (this.id == this.NoeudParent().pointeurs.get(i))
            {
                // On a trouve le pointeur vers ce Noeud dans la liste des pointeurs du Noeud Parent.
                indiceparent = i;
                break;
            }
        }
        
        // On prend le pointeur juste a gauche du precedant.
        
        if (indiceparent > 0)
            n = this.arbre.getNoeud(this.NoeudParent().pointeurs.get(indiceparent - 1));
        else return null;
        
        return n;
    }
    
    /**
     * Récupere le Noeud ou bloc juste a droite de ce Noeud, s'il existe.
     * @return 
     */
    
    public Noeud getNoeudaDroite()
    {
        Noeud<T> n;
        int indiceparent = 0;
        
        if (this.NoeudParent() == null)
            return null;
        
        for (int i = 0; i < this.NoeudParent().pointeurs.size(); i++)
        {
            if (this.id == this.NoeudParent().pointeurs.get(i))
            {
                // On a trouve le pointeur vers ce Noeud dans la liste des pointeurs du Noeud Parent.
                indiceparent = i;
                break;
            }
        }
        
        // On prend le pointeur juste a droite du precedant.
        
        if (indiceparent != this.NoeudParent().pointeurs.size() - 1)
            n = this.arbre.getNoeud(this.NoeudParent().pointeurs.get(indiceparent + 1));
        else return null;
        
        return n;
    }
	
    /**
     * Insere une nouvelle valeur dans ce Noeud.
     * @param t
     */
	
    public void insertion(T t)
    {
    	boolean insere = false;
        
        // Boucle qui compare avec les autres valeurs du Noeud, jusqu'a ce 
        // que l'on trouve la place du Noeud que l'on souhaite inserer.
		
    	for (int i = 0; (i < this.Remplissage()) && (!insere); i++)
            if (t.compareTo(this.valeurs.get(i)) == -1)
            {
                this.valeurs.add(i, t);
		insere = true;
            }
		
	if (!insere)
            this.valeurs.add(t);
		
            // Gestion du split
		
            if (this.Remplissage() > this.arbre.getOrdre())
                this.split(true);
    }
	
    /**
     * Retourne le noeud recherché, ou le noeud se trouvant juste à gauche du
     * Noeud recherché si celui-ci n'est pas trouvé (null si cette arbre est vide).
     * @param t
     */
	
    public Noeud<T> recherche(T t)
    {		
    	for (int i = 0; (i < this.Remplissage()); i++)
    	{
            if (t.compareTo(this.valeurs.get(i)) == 0)
            {
                // On retourne ce Noeud, ou un de ces descendants, si ce Noeud n'est pas externe.
                
                if (!this.EstUneFeuille())
                    return this.arbre.getNoeud(this.pointeurs.get(i)).recherche(t);                
                else if (t.equals(this.valeurs.get(i)))
                    return this;
            }
			
            if (t.compareTo(this.valeurs.get(i)) == -1)
            {
                // On sait maintenant ou descendre.
                
                if (!this.EstUneFeuille())
                    return this.arbre.getNoeud(this.pointeurs.get(i)).recherche(t); 
		else return this;
            }
        }
		
	if ((!this.EstUneFeuille()) && (this.pointeurs != null))
            return this.arbre.getNoeud(this.pointeurs.get(this.pointeurs.size() - 1)).recherche(t); 
	else return this;
    }
	
    /**
     * 
     * @param externe Indique si le split a lieu sur une feuille externe.
     */
	
    public void split(boolean externe)
    {
    	if (this.valeurs.size() <= this.arbre.getOrdre())
            return;
        
        // Creation des 2 nouveaux Noeuds.
		
	Noeud<T> n1 = new Noeud<T>(this.arbre, -1);
        Noeud<T> n2 = new Noeud<T>(this.arbre, -1);
	int i = 0;
		
	if (this.pointeurs != null)
	{
            n1.pointeurs = new ArrayList<Integer>();
            n2.pointeurs = new ArrayList<Integer>();
	}
        
        // Partage des pointeurs et des valeurs selon les Noeuds.
		
	for (; i < (this.arbre.getOrdre()) / 2; i++)
	{
            n1.valeurs.add(this.valeurs.get(i));

            if ((this.pointeurs != null) && (this.pointeurs.get(i) != null))
                n1.pointeurs.add(this.pointeurs.get(i));
	}
        
        if (externe)
            n1.valeurs.add(this.valeurs.get(i));
		
	if ((this.pointeurs != null) && (this.pointeurs.get(i) != null))
            n1.pointeurs.add(this.pointeurs.get(i));
        
	T aremonter = this.valeurs.get(i);
        i++;
		
	for (; i < this.valeurs.size(); i++)
	{
            n2.valeurs.add(this.valeurs.get(i));
			
            if ((this.pointeurs != null) && (this.pointeurs.get(i) != null))
                n2.pointeurs.add(this.pointeurs.get(i));
	}
        
        if ((this.pointeurs != null) && (this.pointeurs.get(i) != null))
            n2.pointeurs.add(this.pointeurs.get(i));
        
        // Ajout des Noeuds nouvellements crees dans l'arbre.
        
        this.arbre.AjouterNoeud(n1);
        this.arbre.AjouterNoeud(n2);
        this.utilise = false;
		
	// Pas de noeud parent ?
		
	if (this.NoeudParent() == null)
	{
            // Creation du Noeud parent, gestion des pointeurs et des references vers ce nouveau Noeuds.
            
            Noeud<T> nparent = new Noeud<T>(this.arbre, -1);
            nparent.valeurs.add(aremonter);
            nparent.pointeurs = new ArrayList<Integer>(this.arbre.getOrdre()+ 1);
            this.arbre.AjouterNoeud(nparent);
            
            nparent = this.arbre.getNoeud(this.arbre.NbFeuilles() - 1);
            this.arbre.setRacine(nparent);
            n1.idParent = nparent.id;
            n2.idParent = nparent.id;            
            nparent.pointeurs.add(n1.id);
            nparent.pointeurs.add(n2.id);
            return;
	}        
       	
        // Gestion du Noeud parent au split.
        
	n1.idParent = this.idParent;
	n2.idParent = this.idParent;
        boolean insere = false;
        
        // Ajout de aremonter dans le Noeud parent.
        
        for (int j = 0; j < (this.NoeudParent().Remplissage()) && (!insere); j++)
        {
            if (this.NoeudParent().valeurs.get(j).compareTo(aremonter) == 1)
            {
                this.NoeudParent().valeurs.add(j, aremonter);
                insere = true;
            }
        }
        
        if(!insere)
            this.NoeudParent().valeurs.add(aremonter);
        
        // Mise a jour des pointeurs dans le Noeud parent.
            
        int index = this.NoeudParent().pointeurs.indexOf(this.id);
        this.NoeudParent().pointeurs.remove(index);
        this.NoeudParent().pointeurs.add(index, n2.id);
        this.NoeudParent().pointeurs.add(index, n1.id);  
		
	// Split du noeud parent ?
		
	if (this.NoeudParent().Remplissage() > this.arbre.getOrdre())
            this.NoeudParent().split(false);
    }
	
    /**
     * Supprime un objet s'il est present dans ce Noeud.
     * @param t
     */
	
    public void suppression(T t)
    {
        // Recherche du Noeud a supprimer.
        
        for (int i = 0; i < this.Remplissage(); i++)
            if (this.valeurs.get(i).equals(t))                
            {
		this.valeurs.remove(i);
                
                // Retirer les références au Noeud supprime supérieures dans l'Arbre 
                
                if ((i == this.valeurs.size()) && (i != 0)) // Si on a retiré la derniere valeur du Noeud
                {
                    Noeud<T> n = this.NoeudParent();
                    boolean continuer = true;
                    
                    while((continuer) && (n != null))
                    {
                        for (int j = 0; j < n.valeurs.size(); j++)
                        {
                            if (t.compareTo(n.valeurs.get(j)) == 0)
                            {
                                n.valeurs.remove(j);
                                n.valeurs.add(j, this.valeurs.get(this.valeurs.size() - 1));
                                continuer = false;
                                break;
                            }
                        }
                        
                        n = n.NoeudParent();
                    }
                }
                else if ((i == this.valeurs.size()) && (i == 0) && (this.id != this.arbre.getRacine().id)) // Cas rencontré uniquement si ordre == 2.
                {
                    this.NoeudParent().suppression(t);
                    return;
                }
                break;
            }
		
	// Gestion des violations du minumum
        
        // Pas de fusion sur la racine
        
        if (this.id == this.arbre.getRacine().id)
            return;
		
	if (this.Remplissage() < ((this.arbre.getOrdre() + 1) / 2))
	{
            Noeud<T> n_gauche = this.getNoeudaGauche();
            Noeud<T> n_droite = null;
            
            if (n_gauche == null)
            {
                n_droite = this.getNoeudaDroite();
                
                // Pas de noeud a gauche, ni a droite => racine de l'arbre, donc rien a faire
                
                if (n_droite != null)
                    this.fusion(n_droite);
            }
            else this.fusion(n_gauche);
	}
    }
	
    /**
     * Retourne une représentation en chaines de
     * caractères de cet Arbre.
     * @return
     */
	
    @Override public String toString()
    {
    	String str = "[";
	
	for (int i = 0; i < this.valeurs.size() + 1; i++)
            {			
		if ((this.pointeurs != null) && (i < this.pointeurs.size()))
                    str += ".->" + this.arbre.getNoeud(pointeurs.get(i)).toString() + ", ";
			
		if ((i < this.valeurs.size()) && (this.valeurs.get(i) != null))
                    str += this.valeurs.get(i).toString() + ", ";
            }
        
	return str + "]";
    }
}