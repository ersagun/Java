import java.io.Serializable;

@SuppressWarnings("serial")
public class UnPoint implements Serializable{

private int abscisse,ordonnee;

	public UnPoint (int a, int o) {
		abscisse = a;
		ordonnee = o;
	}
	
	public	void translation(int dx,int dy) {
	abscisse+=dx;
	ordonnee+=dy;
    }
	
	public  double distance(UnPoint p2){
    int distanceX;
	int distanceY;
	distanceX = (abscisse - p2.abscisse);
	distanceY = (ordonnee - p2.ordonnee);
	distanceX *= distanceX;
	distanceY *= distanceY;
	return (Math.sqrt(distanceX + distanceY));
    }
	
	public int getAbscisse() {
    	return abscisse;
    }
	
	public int getOrdonnee() {
		return ordonnee;
	}	
	
	
}