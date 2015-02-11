import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;


@SuppressWarnings("serial")
public class Text implements MouseListener, MouseMotionListener, Serializable{

	int taille=12;
	String ecriture="Arial Narrow";
	String m;
	Graphics g;
	int x=0;
	int y=0;
	DessinFigure DF;
	Color c;
	
	/**
	 * 
	 * @param t taille de la chaine de caractere
	 * @param e style de la police de la chaine de caractere
	 * @param s chaine de caractere
	 * @param gra contexte graphique
	 * @param d DessinFigure
	 * @param col couleur de la chaine de caractere
	 */
	public Text(int t, String e, String s, Graphics gra, DessinFigure d, Color col){
		ecriture=e;
		taille=t;
		m=s;
		g=gra;
		DF=d;
		c=col;
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
			
	}

	public void mouseEntered(MouseEvent e) {	
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		//Prends l'absisce et l'ordonn�e du clique pour ensuite �crire le mot � cette enplacement
		if(SwingUtilities.isLeftMouseButton(e)){
			x=e.getX();
			y=e.getY();
			Font f=new Font(ecriture,Font.PLAIN, taille);
			g.setFont(f);
			g.setColor(c);
			g.drawString(m,x,y);
		}
		
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
}
