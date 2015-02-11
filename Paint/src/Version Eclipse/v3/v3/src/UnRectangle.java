@SuppressWarnings("serial")
public class UnRectangle extends Quadrilatere{
	
	public int nbPoints(){
		return 2;
	}
	
	public void modifierPoint(UnPoint tab_saisie[]){
		UnPoint p1;
		UnPoint p2;
		p1= new UnPoint(tab_saisie[1].getAbscisse(),tab_saisie[0].getOrdonnee());
		p2= new UnPoint(tab_saisie[0].getAbscisse(),tab_saisie[1].getOrdonnee());
		tab_mem = new UnPoint[] {tab_saisie[0],p1,tab_saisie[1],p2};
	}
}