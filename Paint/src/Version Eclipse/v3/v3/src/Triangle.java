import java.awt.*;

public class Triangle extends Polygone{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int nbPoints(){
		return 3;
	}
	public void affiche(Graphics g){
		int tab_abs[]=new int [3];
		int tab_ord[]=new int [3];
		for(int i=0;i<3;i++){
			tab_abs[i]=tab_mem[i].getAbscisse();
			tab_ord[i]=tab_mem[i].getOrdonnee();
		}
		g.setColor(couleur);
		if(etat==true){
			g.fillPolygon(tab_abs,tab_ord,3);
		}
		else{
			g.drawPolygon(tab_abs,tab_ord,3);
		}
	}
}