import java.awt.*;

@SuppressWarnings("serial")
public abstract class Polygone extends FigureColoree{
	
	
	public void affiche(Graphics g){
		int tab_abs[]=new int [4];
		int tab_ord[]=new int [4];
		for(int i=0;i<4;i++){
			tab_abs[i]=tab_mem[i].getAbscisse();
			tab_ord[i]=tab_mem[i].getOrdonnee();
		}
		g.setColor(couleur);
		
		if(etat==true){
			g.fillPolygon(tab_abs,tab_ord,4);
		}
		else{
			g.drawPolygon(tab_abs,tab_ord,4);
		}
	}
	
	public void modifierPoint(UnPoint tab_saisie[]){
		tab_mem=new UnPoint[tab_saisie.length];
		for(int i=0;i<tab_saisie.length;i++){
			tab_mem[i]=tab_saisie[i];
		}
	}
	
	public abstract int nbPoints();
}
	