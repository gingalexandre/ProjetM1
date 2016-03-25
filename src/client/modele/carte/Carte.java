package client.modele.carte;
/**
 * SuperClasse des cartes.
 *
 * @author Yohann Hugo
*/
public class Carte {

	/**
	 * Attribut du nombre maximum de carte de ce type.
	 */
	private static int NOMBREMAX;

	/**
	 * Attribut du nombre de carte piochée.
	 */
	private static int PIOCHER=0;

    /**
     * Constructeurs
     */
    public Carte(){
        NOMBREMAX = 25;
    }

    public Carte(int nb){
        NOMBREMAX = nb;
    }


	/**
	 * Méthode de retour du nombre de carte de type Chevalier.
	 *
	 * @return le nombre maximum de carte de ce type.
	 */
	public int getNombreMax() {
		return NOMBREMAX;
	}


	/**
	 * Méthode qui increment le nombre de carte piocher.
	 */
	public boolean incrementeNumber() {
		if(PIOCHER<NOMBREMAX){
			PIOCHER++;
			return true;
		}
		return false;
	}

	/**
	 * Méthode qui retourne le nombre de carte disponible dans le paquet.
	 *
	 * @return nombre de carte disponible.
	 */
	public int getDisponible() {
		return (NOMBREMAX-PIOCHER);
	}

}
