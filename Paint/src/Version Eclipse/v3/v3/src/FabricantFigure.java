import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;


@SuppressWarnings("serial")
public class FabricantFigure implements MouseListener , Serializable{
	
	private UnPoint tab[];
	private DessinFigure DF;
	private FigureColoree FC;
	private int i = 0;
	
	
	public FabricantFigure(DessinFigure d, FigureColoree fc){
		DF=d;
		FC=fc;
		tab =  new UnPoint [fc.nbPoints()];
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e)) 
		{
		if( i <= tab.length){
			UnPoint p = new UnPoint(e.getX(), e.getY());
			tab[i] = p;
			i++;
			}
			if ( i == tab.length ){
				FC.modifierPoint(tab);
				DF.ajoute(FC);
				DF.removeMouseListener(this);
				DF.repaint();
				}
			}
		}
		
	public void mouseReleased(MouseEvent e) {
		
	}
	
	}
