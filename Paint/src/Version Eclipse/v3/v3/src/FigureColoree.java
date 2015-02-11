import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class FigureColoree implements Serializable{
	

	protected UnPoint tab_mem[];
	protected Color couleur;
	private boolean selectionner;
	protected static boolean etat=false;
	

	public void changeCouleur(Color c){
		couleur=c;
	}
	
	public void selectionner(){
		selectionner=true;
	}
	
	public void deselectionner(){
		selectionner=false;
	}
	
	public void translater(int dx, int dy){
		for(int i=0;i<tab_mem.length;i++){
			tab_mem[i].translation(dx,dy);
		}
	}
	
	public abstract void modifierPoint(UnPoint tab_p[]);
	
	public void afficheFigure(Graphics g){
		if(selectionner){
			if(etat==false){
				for(int i=0;i<tab_mem.length;i++){
					g.fillRect(tab_mem[i].getAbscisse()-2,tab_mem[i].getOrdonnee()-2, 4,4);
				}
			}
				else{
					for(int i=0;i<tab_mem.length;i++){
						g.drawRect(tab_mem[i].getAbscisse()-2,tab_mem[i].getOrdonnee()-2, 4,4);
					}
				}
		}
	}
	
	public void effacer(){
		for (int i =0;i<tab_mem.length;i++){
			tab_mem[i]=null;
		}
		
		
	}
	
	public abstract void affiche(Graphics g);
	
	public abstract int nbPoints();
	
	public boolean estContenuDans(int x, int y) 
	{
    	boolean Dedans = false;
    	Polygon p = null;
			if (this instanceof Polygone){
				int[] tX = new int[tab_mem.length] ;
				int[] tY = new int[tab_mem.length] ;
				
				for (int i = 0; i < tab_mem.length; i ++) 
				{
					tX[i] = tab_mem[i].getAbscisse();
					tY[i] = tab_mem[i].getOrdonnee();
				}
				p = new Polygon(tX, tY, tab_mem.length);
				Dedans = p.contains(x,y); 
				}
			if(this instanceof ConiqueCentre){
				Dedans = (tab_mem[0].distance(tab_mem[1]) >= tab_mem[0].distance(new UnPoint(x,y)));
				}
		return Dedans;
    }
	
	
}