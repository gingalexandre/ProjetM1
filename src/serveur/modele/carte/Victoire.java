package serveur.modele.carte;

/**
 * Carte victoire qui permettent de gagner de façon permanente deux points de victoire au joueur jouant la classe.
 *
 * @author Yohann Hugo
 */
public class Victoire implements Carte{

	private String nomCarte = "Carte Victoire";
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/armee_puissante.png";

    /**
     * Constructeur
     */
    public Victoire(){
    }

    /**
     * Action provoquer lorsqu'un joueur utilise la carte de type de progrès victoire.
     */
    @Override
    public void doAction() {
        /*
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
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

	@Override
	public String getNom() {
		return this.getNom();
	}
}
