import java.awt.*;

@SuppressWarnings("serial")
public abstract class ConiqueCentre extends FigureColoree{
	
	public abstract void modifierPoint(UnPoint tab_p[]);
	
	public abstract void affiche (Graphics g);
	
	public abstract int nbPoints();
	
	public UnPoint rendreCentre(){
		return tab_mem[0];
	}
}
		