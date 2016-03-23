package client.modele;
/**
*
* @author Arthur
*/
public class CarteSpeciale implements Carte {

	private static int nbCarteSpeciale = 6;
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean encoreAssez(){
		return this.nbCarteSpeciale>0;
	}
	
}
