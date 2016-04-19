package serveur.modele.carte;

/**
 *Carte construction qui permettent de gagner de façon permanente deux routes au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Route implements Carte{

	private static final long serialVersionUID = 1L;
	
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/carte_progres_route.png";

    /**
     * Constructeur
     */
    public Route(){
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès construction.
     */
    @Override
    public void doAction() {
        /*
        currentPlayer.setNbRoute(currentPlayer.getNbRoute()+2);
         */
    }

    /**
     * Méthode de l'interface Carte qui permet de récupérer le chemin de la ressources image d'une carte.
     *
     * @return chemin de la ressource de l'image.
     */
    @Override
    public String getCheminImage() {
        return CHEMIN;
    }
}
