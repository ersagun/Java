
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.SwingUtilities;



@SuppressWarnings("serial")
public class TraceurForme implements MouseListener, MouseMotionListener,Serializable{

	
	int lastx=-1, lasty=-1;
	Graphics g;
	Color c;
	DessinFigure DF;
	ArrayList <UnPoint> tabb =new ArrayList<UnPoint>();
	int compt=0;
	boolean selectionnerForme=false;
	
	
	public TraceurForme(Graphics gr,Color col,DessinFigure d){
		g=gr;
		c=col;
		DF=d;		
	}
	
	public void translaterForme(int dx, int dy){
		for(int i=0;i<tabb.size();i++){
			tabb.get(i).translation(dx,dy);
		}
	}
	
	public void selectionner(){
		selectionnerForme=true;
	}
	
	public void deselectionner(){
		selectionnerForme=false;
	}

	
		public void mousePressed (MouseEvent e){
			if(SwingUtilities.isLeftMouseButton(e)){
				DF.addMouseListener(this);
				DF.addMouseMotionListener(this);
				lastx=e.getX();
				lasty=e.getY();
			}else if (SwingUtilities.isRightMouseButton(e)){
				DF.removeMouseListener(this);
				DF.removeMouseMotionListener(this);
				DF.repaint();
			}
		}
		
		public void mouseReleased (MouseEvent e){};
		
		public void mouseClicked (MouseEvent e) {
			
		}
		
		public void mouseEntered(MouseEvent e){};
		
		public void mouseExited (MouseEvent e){};
		
		public void mouseDragged (MouseEvent e){
			if(SwingUtilities.isLeftMouseButton(e)){
				g.setColor(c);
				g.drawLine(lastx,lasty,e.getX(),e.getY());
				lastx=e.getX();
				lasty=e.getY();
				UnPoint p = new UnPoint(lastx,lasty);
				compt=compt+1;
				tabb.add(p);
			}
		}
		
		public void mouseMoved (MouseEvent e){
			
		}
}