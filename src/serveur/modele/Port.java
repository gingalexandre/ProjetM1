package serveur.modele;

import java.rmi.RemoteException;

/**
*
* @author Arthur
*/
public class Port extends Ville {
	
	private static final long serialVersionUID = 1L;
	
	private Ressource ressourceEchangeable;

	public Port(Point emplacement) throws RemoteException {
		super(emplacement);
	}
	
	public void echangePort(){
		if(this.ressourceEchangeable == null){
			//Donner n'importe quelle ressource x3 contre une choisie
		}
		else{
			//Donner ressourceEchangeable x2 contre une choisie
		}
	}
	
}
